package ConsumerProducer;
/*
Creamos una clase MyProducer , esta clase sera
 un hilo , implementado Runnable
 Tendremos una variable de instancia
 -una lista de string - buffer
 -string color
 -constructor sobrecargado
 run()
 voy a crear un objeto random
 creo un array[] de strings on "1" , "2"  ... "5"
 luego iterare sobre el array  , lo muestro con el color del hilo y los añado al buffer
 despues duermo el hilo 1 sec random.nextInt(1000)
 cuando acabo el bucle al final del metodo imprimo final de fichero
 y añado al buffer "-1" o "EOF"

Creo una clase MyConsumer implementa Runnable
mismas variables que la anterior

contructor sobrecargado

Metodo run;
Creamos un bucle infinito. Y dentro si el buffer esta vacio
continua continue
si no esta vacio , compruebo que  lo primero que hay en el
buffer sea EOF , si lo es , imprimo end of file
y salgo del bucle
si no es EOF , los muestro y los elimino de la lista
todo lo que imprima en el color del hilo

Main
Creo un arraylist vacio llamado buffer
Elegir color diferente para cada hilo
Me creo un hilo producer y dos hilos de consumer
MyProducer p = new MyProducer();

creo 3 hilos y los inicio
new Thread(p).start

Estudiamos el resultado e intento corregirlo con synchronized
 */
/*
Inconvenientes de sycnhronized
-Tengo hilos bloqueados , esperando y no se pueden interrumpir
-Es muy ineficiente
-No hay informacion  si el objeto esta disponible o quien tiene el bloqueo

 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        List<String> bufer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        MyProducer p = new MyProducer(bufer,ThreadColor.ANSI_BLUE, bufferLock);
        MyConsumer p1 = new MyConsumer(bufer,ThreadColor.ANSI_GREEN, bufferLock);
        MyConsumer p2 = new MyConsumer(bufer,ThreadColor.ANSI_RED, bufferLock);

        /*
        new Thread(p).start();
        new Thread(p1).start();
        new Thread(p2).start();


         */
        executorService.execute(p);
        executorService.execute(p1);
        executorService.execute(p2);

        //Espera a que finalicen los hilos
        executorService.shutdown();
    }
}