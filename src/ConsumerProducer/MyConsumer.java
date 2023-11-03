package ConsumerProducer;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MyConsumer implements Runnable{

    private String color;
    private List<String> bufer;
    private ReentrantLock bufferLock;

    public MyConsumer( List<String> bufer,String color, ReentrantLock bufferLock) {
        this.bufer = bufer;
        this.color = color;
        this.bufferLock = bufferLock;
    }
    @Override
    public void run() {
        while(true){
            if  (bufferLock.tryLock()) {
                try {
                    if (bufer.isEmpty()) {
                        continue;
                    }
                    if (bufer.get(0).equals("EOF")) {
                        System.out.println(color + "Final de fichero");
                        break;
                    } else {
                        System.out.println(color + "leo y elimnio" + bufer.remove(0));
                    }
                } finally {
                    bufferLock.unlock();
                }
            }
        }
    }
}
