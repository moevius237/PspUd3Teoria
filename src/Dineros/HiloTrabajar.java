package Dineros;

public class HiloTrabajar implements Runnable{
    private String id;
    private Dinero cont;

    public HiloTrabajar(String id, Dinero d) {
        this.id = id;
        this.cont = d;
    }
    @Override
    public void run() {
        Boolean br = true;
        while(br) {
            synchronized (cont) {
                while (this.cont.getDinero() > 2000) {
                    try {
                        this.cont.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    this.cont.trabajar();
                this.cont.notify();
                System.out.println("Hilo " + id + " incrementa, valor contador " + cont.getDinero());
            }
        }
    }
}
