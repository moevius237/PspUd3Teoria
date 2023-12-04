package pruebasExamen;

import ConsumerProducer.ThreadColor;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new HiloSecundarioR());
        Thread t2 = new Thread(
                () -> System.out.println("soy el hilo 3 anonimo"));
        Thread t3 = new HiloSecundario();

        Runnable r = () -> System.out.println("Runnable anonima lambda" + ThreadColor.ANSI_CYAN);
        Thread tt = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        tt.start();

        Thread tA = new Thread(() -> {
                System.out.println("Soy hilo4");
        });

    }

}
