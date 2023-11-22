package Dineros;

import java.util.Random;

//
public class MainDineros {
    private static final int NUM_HILOS_TRABAJO = 1;
    private static final int NUM_HILOS_LADRONES = 3;
    public static void main(String[] args) {
        Random r = new Random();
        Dinero d = new Dinero(0);
        Thread[] hilos = new Thread[NUM_HILOS_TRABAJO];
        for (int i = 0; i < hilos.length; i++) {
            Thread t = new Thread(new HiloTrabajar("Trabajo " + i, d));
            hilos[i] = t;
        }
        Thread[] hilosD = new Thread[NUM_HILOS_LADRONES];
        for (int i = 0; i < hilosD.length; i++) {
            Thread t = new Thread(new HiloRobar("Robo " + i, d));
            hilosD[i] = t;
            try {
                Thread.sleep(r.nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
        for (int i = 0; i < hilosD.length; i++) {
            hilosD[i].start();
        }
    }
}
