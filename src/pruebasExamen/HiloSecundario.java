package pruebasExamen;

import ConsumerProducer.ThreadColor;

import java.util.concurrent.ThreadPoolExecutor;

public class HiloSecundario extends Thread {
    @Override
    public void run() {
        System.out.println("Soy el hilo uno" + ThreadColor.ANSI_GREEN);
    }
}
class HiloSecundarioR implements Runnable{
    @Override
    public void run() {
        System.out.println("Soy el hilo 2" + ThreadColor.ANSI_BLUE);
    }
}
