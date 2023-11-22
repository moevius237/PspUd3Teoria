package ud5;

import ConsumerProducer.ThreadColor;

import java.util.concurrent.*;

class ColorThreadFactory implements ThreadFactory{

    private String nameThread;

    public ColorThreadFactory(String nameThread) {
        this.nameThread = nameThread;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(this.nameThread);
        return t;
    }
}
public class MainExecutor {

    public static void countDown(){
        for (int i = 20; i >= 0; i--) {
            System.out.println(Thread.currentThread().getName() +"Hilo " + i);
        }
    }
    public static int sum(int star, int end , int increment,String color){
        int sum =0;
        for(int i = star; i < end; i += increment){
            sum+=i;
        }
        System.out.println(color +Thread.currentThread().getName()+ " "+sum);
      return sum;
    }
    /*
    execute: void execure(Runnable r)
    submit: Future<?> submit(Runnable r)
            Future<?> submit(Callable<T> c)
     */
    public static void main(String[] args) {
      /*  ExecutorService blue = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blue.execute(MainExecutor::countDown);
        blue.shutdown();
       */
         /* try {
            boolean acabado = blue.awaitTermination(500, TimeUnit.MILLISECONDS);
            if (acabado){
                ExecutorService yellow = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_GREEN));
                yellow.execute(MainExecutor::countDown);
                yellow.shutdown();// no signidicada nada contiunua el hilo se apaga cunado este acabe

                ExecutorService red = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_RED));
                red.execute(MainExecutor::countDown);
                red.shutdown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        */
        /*
        Single thread que hace toda la tarea
        threadpool numero fijo de hilos que reparten tareas
        threadcatch el numero de hilos aumenta o disminuye segun la tarea
        schedulethreadpool: tipo especial de caced con mecanismos para programar
        workstealingThreadpool ; divide las tareas en subtareas
        ejecuta las subtareas en paralelo
        forkjoinpool: tipo del anterior que permite mas control en planificacion de las tareas


        int count = 3;
        ExecutorService multiExe = Executors.newFixedThreadPool(count);
        try {
            for (int i = 0; i < 3; i++) {
                multiExe.execute(MainExecutor::countDown);
            }
        }finally {
            multiExe.shutdown();
        }
        */
        ExecutorService co = Executors.newCachedThreadPool();
        try {
           // co.submit(new)
            Future<Integer> f = co.submit(()-> sum(1,10,1,ThreadColor.ANSI_RED));

            co.submit(()-> sum(10,100,10,ThreadColor.ANSI_BLUE));
            co.submit(()-> sum(2,20,2,ThreadColor.ANSI_GREEN));



        }finally {
            co.shutdown();
        }
    }
}
