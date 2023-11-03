package ConsumerProducer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducer implements Runnable {
   private String color;
   private List<String> bufer;
    private ReentrantLock bufferLock;


    public MyProducer( List<String> bufer,String color ,ReentrantLock bufferLock) {
        this.bufer = bufer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    //Regostrps <--contador
    //Regostrp inc
    //Registro --> contador
    @Override
    public void run() {
        Random random = new Random();
        String [] array = {"1","2","3","4","5"};
        for (String s : array) {
            try {
            System.out.println(color +"Añadiendo.."+ s);

            bufferLock.lock();
            try {
                bufer.add(s);
            }finally {
                bufferLock.unlock();
            }
            Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(color + "Se interrumpio el hilo");
            }
        }
        System.out.println(color +"Final del fichero");
        bufferLock.lock();
        try {

            bufer.add("EOF");
        }finally {
            bufferLock.unlock();

        }
    }
}
