package pairProgramming;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

record Order(int id, String shoeType, int quantity){}

class ShoeWarehouse{
    private LinkedList<Order> orderList = new LinkedList<>();
    public LinkedList<Order> getOrderList() {
        return orderList;
    }

    public boolean receiveOrder(Order order){
        if (orderList.size() < 10){
            orderList.add(order);
            return true;
        }else {
            return false;
        }
    }
    public Order fullfillOrder(){
        if (!orderList.isEmpty()){
            Order order = orderList.get(0);
            orderList.remove(0);
            return order;
        }
        return new Order(-1,"",0);
    }
}

class Consumer implements Runnable{
    private ShoeWarehouse shoeWarehouse;

    public Consumer(ShoeWarehouse shoeWarehouse) {
        this.shoeWarehouse = shoeWarehouse;
    }

    @Override
    public void run() {
        while (true){
            synchronized (shoeWarehouse){
                while (shoeWarehouse.getOrderList().isEmpty()){
                    try {
                        shoeWarehouse.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int i = 0; i < 5; i++) {
                    Order order = shoeWarehouse.fullfillOrder();
                    if (order.id() != -1){
                        System.out.println("Order " + order + " fulfilled.");
                        shoeWarehouse.notifyAll();
                    }else {
                        System.out.println("No order could be fulfilled as the warehouse is empty.");
                        try {
                            shoeWarehouse.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Actual order list state:");
                    shoeWarehouse.getOrderList().forEach(System.out::println);
                    System.out.println("");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
class Producer implements Runnable{
    private Random random = new Random();
    private final ShoeWarehouse shoeWarehouse;
    private static final List<String> shoeTypes = List.of("snickers","boots","sandals");

    public Producer(ShoeWarehouse shoeWarehouse) {
        this.shoeWarehouse = shoeWarehouse;
    }

    @Override
    public void run() {
        while (true){
            synchronized (shoeWarehouse){
                while (shoeWarehouse.getOrderList().size() > 10){
                    try {
                        shoeWarehouse.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int i = 0; i < 10; i++){
                    Order order = new Order(random.nextInt(100), shoeTypes.get(random.nextInt(3)),
                            random.nextInt(100));
                    if (shoeWarehouse.receiveOrder(order)){
                        System.out.println("Order " + order + " sent to the warehouse.");
                        shoeWarehouse.notifyAll();
                    }else {
                        System.out.println("Order could not be received as the warehouse is full.");
                        try {
                            shoeWarehouse.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Actual order list state:");
                    shoeWarehouse.getOrderList().forEach(System.out::println);
                    System.out.println();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        Thread p1 = new Thread(new Producer(shoeWarehouse));
        Thread c2 = new Thread(new Consumer(shoeWarehouse));
        Thread c3 = new Thread(new Consumer(shoeWarehouse));
        c2.start();
        c3.start();
        p1.start();
    }
}