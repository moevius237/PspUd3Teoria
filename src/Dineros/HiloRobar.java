package Dineros;

import java.util.Random;

public class HiloRobar implements Runnable {

    private String id;
    private Dinero cont;
    private Random r = new Random();

    public HiloRobar(String id, Dinero d) {
        this.id = id;
        this.cont = d;
    }

    @Override
    public void run() {
        Boolean bra = true;
        while (bra) {
            synchronized (cont) {
                while (this.cont.getDinero() < 1) {
                    System.out.println("Hilo " + id + " no puede decrementar, valor contador " + cont.getDinero());
                    try {
                        this.cont.wait();
                    } catch (InterruptedException e) {
                    }
                }
                this.cont.robar();
                this.cont.notify();
                try {
                    this.cont.wait(r.nextInt(300)+20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hilo " + id + " decrementa, valor contador " + cont.getDinero());
            }
            }

        }
}