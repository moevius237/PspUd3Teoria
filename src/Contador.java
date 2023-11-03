
public class Contador {

    private int cuenta = 0;

    public Contador(int valorInicial) {
        this.cuenta = valorInicial;
    }

    public synchronized int getCuenta() {
        return cuenta;
    }

    public synchronized int incrementa() {
        this.cuenta++;
        return cuenta;
    }

    public synchronized int decrementa() {
        this.cuenta--;
        return cuenta;
    }
}
class HiloIncr implements Runnable {

    private final String id;
    private final Contador cont;

    public HiloIncr(String id, Contador c) {
        this.id = id;
        this.cont = c;
    }

    @Override
    public void run() {
        boolean terminar = true;
        while(terminar) {
            synchronized (this.cont) {
                this.cont.incrementa();
                this.cont.notify();
                System.out.println("Hilo " + id + " incrementa, valor contador " + cont.getCuenta());
            }
            if (cont.getCuenta() > 100){
                terminar = false;
                System.out.println("Se termino");
            }
        }
    }
}

class HiloDecr implements Runnable {

    private final String id;
    private final Contador cont;

    public HiloDecr(String id, Contador c) {
        this.id = id;
        this.cont = c;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (cont) {
                while (this.cont.getCuenta() < 1) {
                    System.out.println("Hilo " + id + " no puede decrementar, valor contador " + cont.getCuenta());
                    try {
                        this.cont.wait();
                    } catch (InterruptedException e) {
                    }
                }
                this.cont.decrementa();
                System.out.println("Hilo " + id + " decrementa, valor contador " + cont.getCuenta());
            }
        }
    }
}

class MainHilosIncDec {
    private static final int NUM_HILOS_INC = 5;
    private static final int NUM_HILOS_DEC = 5;

    public static void main(String[] args) {
        Contador c = new Contador(0);
        Thread[] hilos = new Thread[NUM_HILOS_INC];
        for (int i = 0; i < hilos.length; i++) {
            Thread t = new Thread(new HiloIncr("Trabajo " + i, c));
            hilos[i] = t;
        }

        Thread[] hilosD = new Thread[NUM_HILOS_DEC];
        for (int i = 0; i < hilosD.length; i++) {
            Thread t = new Thread(new HiloDecr("Ro " + i, c));
            hilosD[i] = t;
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }

        for (int i = 0; i < hilosD.length; i++) {
            hilosD[i].start();
        }
    }
}