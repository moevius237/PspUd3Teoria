package pruebasExamen;

import ConsumerProducer.ThreadColor;
import com.sun.jdi.ThreadReference;

import java.util.TreeMap;

public class Contador {
    private int i;
    public void contarAtras(){
        String color;
        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_BLUE;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_RED;
                break;
            default:
                color = ThreadColor.ANSI_RESET;
                break;
        }
        synchronized (this){
        for ( i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + " i =" + i);
        }
        }
    }
}

class HiloContador extends Thread{
    private Contador contador;

    public HiloContador(Contador contador){
        this.contador = contador;
    }

    @Override
    public void run() {
        contador.contarAtras();

    }


}
//Preguntar como aplicar Join En este caso
class MainC{
    public static void main(String[] args) {
        Contador contador = new Contador();
        HiloContador thread1 = new HiloContador(contador);
        thread1.setName("Thread 1");
        HiloContador thread2 = new HiloContador(contador);
        thread2.setName("Thread 2");

        thread1.start();

        thread2.start();
    }
}