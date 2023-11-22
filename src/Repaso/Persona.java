package Repaso;

import java.util.Random;

//Persona hilo , el tiempo de llegada de las personas es aleatorio
//entre 1 y 30 segunods para atrevesart el puente entre 10 y 50 segundos
//las personas tendran un peso entre 40 y 120kg

//id persona , peso , tmin paso , tmax paso constructor con todo
//imprimo un mensaje de quien soy cuanto peso y lo que hay en el puente actualmente
//bucle() mientras no este autorizado vere si estoy autorizado = metodo
//si no estoy autorizado muestro un mensaje y espero
//Calculo el timpo que voy a tardar en cruzar el puetne , simulo que cruzo el puente

//lamar a salir del puente informo que he salido del puente

//tendre que simular que personas estan pasando por el puente , bucle infinito
//calacula el timempo para que pase la siguiente persona
//crea el hilo y lanzo la persona al puente
class Persona implements Runnable{
    private static int peso;
    private final int personas;

    private final int tMinPaso, tMaxPso;

    private final Puente puente;

    public Persona(int peso, int personas,int tMinPaso, int tMaxPso, Puente puente) {
        this.peso = peso;
        this.personas = personas;
        this.tMinPaso = tMinPaso;
        this.tMaxPso = tMaxPso;
        this.puente = puente;
    }


    public static int getPeso() {
        return peso;
    }

    @Override
    public void run() {
        System.out.println("soy la persona " + personas + "Quiero cruzar con peso"
        + peso + "En el puente ahora hay" + puente);
        boolean autorizado = false;
        while (autorizado){
            synchronized (this.puente){
            if(!autorizado){
                System.out.println(personas + "debe esoerar");
                try {
                    this.puente.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            }
        }
        System.out.println(personas + "con peso" + peso + "puede cruzar el puente");

        Random r = new Random();
        int tiempopaso = r.nextInt(tMinPaso, tMaxPso);
        try {
            Thread.sleep(tiempopaso);
        } catch (InterruptedException e) {
        }
        synchronized (this.puente){
        }
    }
}
class PasoPorPuente{
    public static void main(String[] args) {
        final Puente puente = new Puente();
        Random random = new Random();
        int paso = 10;
        int maxpaso = 50;
        int idpesona = 1;

        while (true){

            int tLlegadaPuente = random.nextInt(1000,3001);
            try {
                Thread.sleep(tLlegadaPuente);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int peso = random.nextInt(40,121);

            //Thread th1 = new Persona(new Persona(idpesona,peso,paso,maxpaso));
            idpesona++;
        }
    }
}