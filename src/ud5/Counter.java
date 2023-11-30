package ud5;

import ConsumerProducer.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
Ud3:
-Creacion de hilos extendio THread, implements Runnable ,hilo anonimo
-Join , interrupted
Ud4:

 */
public class Counter implements Runnable{
    //ejemplos de como se hace
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
            //    while (this.isInterrupted()){

              //  }
            }
        };
        Runnable r = new Runnable(){
            @Override
            public void run() {
                while (Thread.currentThread().isAlive()){
                    System.out.println("vivo");
                }
            }
        };

        Thread t2 = new Thread(r);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // t.run(); //Este no se usa nunca
        //t2.interrupt();

    }

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(Thread.currentThread().getName()+ "hilo " + i);
        }
    }
}

/*
-SingleThreadExecutor()
-FixedThreadPool(int numHilos)
-CachedTreadPool()

--Metodos:
-execute(Runnable r)
-submit(Runnable r)
-submit(Callable <T>c)
-List<Future> invokedAll(List<Callable>)
-T invokedaAny(List<Callable>)
 */
class MainExcutor{
    public static int sum(int star, int end , int increment,String color){
        int sum =0;
        for(int i = star; i < end; i += increment){
            sum+=i;
        }
        System.out.println(color +Thread.currentThread().getName()+ " "+sum);

   return sum;
    }
    public static void main(String[] args) {
      /*  ExecutorService executor =
                Executors.newSingleThreadExecutor();
        Runnable task = new Counter();
        //executor.execute(new Counter());Es lo mismo que abajo
        executor.execute(task);
        executor.execute(task);
        executor.shutdown();


       */

        /*
        ExecutorService executor =
                Executors.newFixedThreadPool(1);
        Runnable task = new Counter();
        for (int i = 0; i < 6; i++) {
            executor.execute(task);
        }
        executor.shutdown();

         */

        /*
        ExecutorService executor =
                Executors.newCachedThreadPool();
        Runnable task = new Counter();
        for (int i = 0; i < 6; i++) {
            executor.execute(()-> MainExcutor.sum(1,100,1
                    ,ThreadColor.ANSI_BLUE));
        }
        executor.shutdown();

         */
        /*
        ExecutorService executor =
                Executors.newCachedThreadPool();
        try {


        Callable<Integer> c = () ->
            MainExcutor.sum(1,100,1,ThreadColor.ANSI_BLUE);
            List<Callable<Integer>> tasklist = new ArrayList<>();
            tasklist.add(() -> sum(1,100,2,ThreadColor.ANSI_RED));
            tasklist.add(() -> sum(2,10,1,ThreadColor.ANSI_GREEN));
            tasklist.add(() -> sum(5,1010,5,ThreadColor.ANSI_CYAN));

            List<Callable<Integer>> tkasList = List.of(
                    ()-> sum(1,100,2,ThreadColor.ANSI_PURPLE),
                    () -> sum(1,100,2,ThreadColor.ANSI_BLACK);
  //      for (int i = 0; i < 6; i++) {
     // }
            try {
                List<Future<Integer>> resultList = executor.invokeAll(tasklist);
                Future<Integer> resultado = executor.submit(c);
           System.out.println(resultado.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fin");
    }finally {
            executor.shutdown();
        }

         */

        /*
        ExecutorService executor =
                Executors.newCachedThreadPool();
        try {


            Callable<Integer> c = () ->
                    MainExcutor.sum(1,100,1,ThreadColor.ANSI_BLUE);
            List<Callable<Integer>> tasklist = new ArrayList<>();
            tasklist.add(() -> sum(1,100,2,ThreadColor.ANSI_RED));
            tasklist.add(() -> sum(2,10,1,ThreadColor.ANSI_GREEN));
            tasklist.add(() -> sum(5,1010,5,ThreadColor.ANSI_CYAN));
            //Lo mismo que arriba de otra manera
            List<Callable<Integer>> tkasList = List.of(
                    ()-> sum(1,100,2,ThreadColor.ANSI_PURPLE),
                    () -> sum(1,100,2,ThreadColor.ANSI_BLACK));
            //      for (int i = 0; i < 6; i++) {
            // }
            try {
                List<Future<Integer>> resultList = executor.invokeAll(tasklist);
                for (Future<Integer> future:resultList){
                    System.out.println(future.get());
                }
                } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
        }finally {
            executor.shutdown();
        }

         */

        ExecutorService executor =
                Executors.newCachedThreadPool();
        try {


            Callable<Integer> c = () ->
                    MainExcutor.sum(1,100,1,ThreadColor.ANSI_BLUE);
            List<Callable<Integer>> tasklist = new ArrayList<>();
            tasklist.add(() -> sum(1,100,2,ThreadColor.ANSI_RED));
            tasklist.add(() -> sum(2,10,1,ThreadColor.ANSI_GREEN));
            tasklist.add(() -> sum(5,1010,5,ThreadColor.ANSI_CYAN));
            //Lo mismo que arriba de otra manera
            List<Callable<Integer>> tkasList = List.of(
                    ()-> sum(1,100,2,ThreadColor.ANSI_PURPLE),
                    () -> sum(1,100,2,ThreadColor.ANSI_BLACK));
            //      for (int i = 0; i < 6; i++) {
            // }
            try {
                executor.invokeAll(tkasList);
                //Esto y las tres lineas de abajo son lo mismo
                List<Future<Integer>> futures = new ArrayList<>();
                for (int i = 0; i < tkasList.size(); i++) {
                    executor.submit(tasklist.get(i));

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }finally {
            executor.shutdown();
        }
    }
}