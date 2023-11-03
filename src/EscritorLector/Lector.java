package EscritorLector;

import java.util.Random;

/*
Variables
objeto message
construcot

pon un random y luego en un bucle voy leyendo con el metodo
read y mostrano lo que devuelves
 */
public class Lector implements Runnable{
   private Message lm;
   private String color;

   public Lector(Message lm, String color){
       this.lm = lm;
       this.color = color;
   }
    @Override
    public void run() {
        Random r = new Random();
        String mensajeleido;
        while (!(mensajeleido = lm.read()).equals("EOF")){
            System.out.println(color + mensajeleido);
            try {
                Thread.sleep(r.nextInt(2000));
            } catch (InterruptedException e) {
            }
        }
        }
    }

