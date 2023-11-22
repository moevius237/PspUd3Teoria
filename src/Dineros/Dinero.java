package Dineros;

import java.util.Random;

public class Dinero {
    private int dinero = 0;
    private int conta= 0;
    private Random rr = new Random();

    public Dinero(int dinero) {
        this.dinero = dinero;
    }

    public int getDinero() {
        return dinero;
    }

    public synchronized int trabajar(){
        conta =conta +  rr.nextInt(31)+10;
      this.dinero= conta;
        return conta;
    }
    public synchronized int robar(){
        conta = conta-rr.nextInt(30);

        this.dinero = conta;
        return conta ;
    }

}
