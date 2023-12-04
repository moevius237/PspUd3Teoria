package pruebasExamen;

import ConsumerProducer.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class TryFinallyLock implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;
    public TryFinallyLock(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }
    @Override
    public void run() {
        Random r = new Random();
        String[] numbers = {"1", "2", "3", "4", "5"};
        for(String num: numbers) {
            try {
                System.out.println(color + "Adding... " + num);
                bufferLock.lock();
                try {
                    buffer.add(num);
                } finally {
                    bufferLock.unlock();
                }
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(color + "Producer was interrupted");
            }
        }
        System.out.println(color + "Final de fichero y saliendo...");
        bufferLock.lock();//Dondee se pone el bufferLock deentro del try o fueera
        try {
            buffer.add("EOF");
        } finally {
            bufferLock.unlock();
        }
    }
}
class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;
    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }
    @Override
    public void run() {
        int counter = 0;//vamos a ver cuántas veces intenta obtener el bloqueo
        while(true) {
            bufferLock.lock();

            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                System.out.println(color + " The counter is " + counter);
                counter = 0;
                if (buffer.get(0).equals("EOF")) {
                    System.out.println(color + " End of file.");
                    break;
                } else {
                    System.out.println(color + " Read and remove " + buffer.remove(0));
                }
            } finally {
                bufferLock.unlock();
            }

        }
    }
}

class MainRLock {
    public static final String EOF = "EOF";
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        TryFinallyLock producer = new TryFinallyLock(buffer, ThreadColor.ANSI_RED, bufferLock);
        MyConsumer consumer = new MyConsumer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();
}
}
