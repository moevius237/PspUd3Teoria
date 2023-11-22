package ud5;

import ConsumerProducer.ThreadColor;

import java.util.function.Predicate;

public class MainSinEx {

    public static void countDown(){
        for (int i = 20; i >= 0; i--) {
            System.out.println(Thread.currentThread().getName() +"Hilo " + i);

        }
    }

    public static void main(String[] args) {


        Thread blue = new Thread(MainSinEx::countDown, ThreadColor.ANSI_BLUE);
        Thread yellow = new Thread(MainSinEx::countDown, ThreadColor.ANSI_GREEN);
        Thread red = new Thread(MainSinEx::countDown, ThreadColor.ANSI_RED);

        blue.start();
        try {
            blue.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        red.start();
        try {
            red.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        yellow.start();
        try {
            yellow.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fin");
    }
}
