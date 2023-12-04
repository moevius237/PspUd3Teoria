package pruebasExamen;

import ConsumerProducer.ThreadColor;

import java.util.Random;

public class ConsumerProducerExamen {
    private String message;
    private boolean empty = true;

    public synchronized String read(){
        while (empty){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        empty = true;
        notifyAll();
        return message;
    }
    public synchronized void write(String message){
        while (!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}
class Escritor implements Runnable{
private ConsumerProducerExamen message;
private String color;

    public Escritor(ConsumerProducerExamen message, String color) {
        this.message = message;
        this.color = color;
    }

    @Override
    public void run() {
        String messages[] = {
          "Programacion de servicios",
          "Acceso a datos",
          "Dispositivos moviles",
          "Desarrollo de interfaces",
          "Sistema de gestion empresarial"
        };
        Random random = new Random();
        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            System.out.println(color + "Escribo mensaje" + i);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        message.write("EOF");
    }
}
class Lector implements Runnable{
    private String color;
    private ConsumerProducerExamen message;

    public Lector(String color, ConsumerProducerExamen message) {
        this.color = color;
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        String ultimoMensaje;
        while (!(ultimoMensaje = message.read()).endsWith("EOF")){
            System.out.println(color + ultimoMensaje );
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class MessageMAin{
    public static void main(String[] args) {
        ConsumerProducerExamen message = new ConsumerProducerExamen();
        new Thread(new Escritor(message, ThreadColor.ANSI_GREEN)).start();
        (new Thread(new Lector(ThreadColor.ANSI_BLUE,message))).start();
    }
}