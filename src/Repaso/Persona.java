package Repaso;

import java.util.Random;

//Persona hilo , el tiempo de llegada de las personas es aleatorio
//entre 1 y 30 segunods para atrevesart el puente entre 10 y 50 segundos
//las personas tendran un peso entre 40 y 120kg

//id persona , peso , tmin paso , tmax paso constructor con todo
//imprimo un mensaje de quien soy cuanto peso y lo que hay en el puente actualmente
//bucle() mientras no este autorizado vere si estoy autorizado = metodo
//si no estoy autorizado muestro un mensaje y espero
public class Persona implements Runnable{
    private static  int peso = 200;
    private static  int personas = 3;

    public static int getPeso() {
        return peso;
    }

    public static int getPersonas() {
        return personas;
    }

    public Persona(int peso) {
        this.peso = peso;
    }


    @Override
    public void run() {
        Random r = new Random();
        peso = r.nextInt(100);
    }
}
