package pruebasExamen;

import ConsumerProducer.MyProducer;
import ConsumerProducer.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

class MyProducrExamen implements Runnable{
    private List<String> buffer;
    private String color;

    public MyProducrExamen(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random r = new Random();
        String[] numbeers = {"1","2","3","4","5"};
        for (String num : numbeers){
            System.out.println(color + "Adding... " + num);
            synchronized (buffer){
                buffer.add(num);
            }
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(color + "Final de fichero y saliendo");
    synchronized (buffer){
        buffer.add("EOF");
    }
    }
}
class MyConsumerExamen implements Runnable{
    private List<String> buffer;
    private String color;

    public MyConsumerExamen(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        System.out.println(color + "Bloqueo de run en consumer");
        while (true){
            synchronized (buffer){
                if (buffer.isEmpty()){
                    continue;
                }
                if (buffer.get(0).equals("EOF")) {
                    System.out.println(color + "END of file");
                    break;
                }else {
                    System.out.println(color + "Leido y borrado" + buffer.remove(0));
                }
            }
        }
    }
}
public class MainCP{

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        MyProducrExamen producer = new MyProducrExamen(buffer, ThreadColor.ANSI_GREEN);
        MyConsumerExamen consumer = new MyConsumerExamen(buffer,ThreadColor.ANSI_BLUE);
        MyConsumerExamen consumer2 = new MyConsumerExamen(buffer,ThreadColor.ANSI_RED);

        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();

    }
}
