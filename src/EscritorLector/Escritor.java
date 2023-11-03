package EscritorLector;

import java.util.Random;

public class Escritor implements Runnable{

   private Message m;
   private String color;

   public Escritor(Message m, String color){
       this.m= m;
       this.color=color;
   }

    @Override
    public void run() {
        String[] messages={
                "Hola que tal",
                "Quien eres?",
                "como te encuentras?",
                "Donde estas?",
                "Esto en clase"
        };
        Random r = new Random();
        for (int i = 0; i < messages.length; i++) {
            m.write(messages[i]);
            System.out.println(color + "Escribiendo "+ i);
            try {
                Thread.sleep(r.nextInt(2000));
            } catch (InterruptedException e) {
            }
        }
        m.write("EOF");
    }
}
