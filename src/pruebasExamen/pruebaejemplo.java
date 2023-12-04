package pruebasExamen;

import ConsumerProducer.ThreadColor;

class UnHilo extends Thread {
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_BLUE +
                currentThread().getName());
        try {
            //Ponemos el hilo a dormir 5 segundos
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(ThreadColor.ANSI_BLUE + " otro hilo me ha interrumpido");
            return;
        }
        System.out.println(ThreadColor.ANSI_BLUE + "Three seconds have passed y estoy despierto");
    }
}
public class pruebaejemplo {
    public static void main(String[] args) {
        //Creo un hilo heredando de la clase Thread
        Thread hilo = new UnHilo();
        hilo.setName("UN HILO");
        hilo.start();
//Creo un segundo hilo con clases anónimas
        Thread runable2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_RED + "runable anonima");
            }
        });
        runable2.start();
//aquí tenemos la referencia al hilo que queremos interrumpir de su

//sueño o suspensión
        hilo.interrupt();//con esto hacemos que UnHilo se interrumpa y sale a
        System.out.println(ThreadColor.ANSI_RESET + "Hola soy main thread");
        Thread runnable2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnablee anonima");
                try{
                    hilo.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
