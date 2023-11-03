package Dineros;

import java.util.Random;

public class Dinero {
    private int dinero = 0;
    private int ceint= 0;
    private Random rr = new Random();

    public Dinero(int dinero) {
        this.dinero = dinero;
    }

    public int getDinero() {
        return dinero;
    }

    public synchronized int trabajar(){
        ceint =ceint +  rr.nextInt(31)+10;
      this.dinero= ceint;
        return ceint;
    }
    public synchronized int robar(){
        ceint = ceint-rr.nextInt(30);

        this.dinero = ceint;
        return ceint ;
    }

}
