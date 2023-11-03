package EscritorLector;

import ConsumerProducer.MyProducer;
import ConsumerProducer.ThreadColor;

/*
Clase Message
variables de instancia
String message de tipo texto
Boolean empty
Metodos
-Read no recibe nada , devuelve el mensaje
leo el mansaje entocez empty a true

-write recibe un mensaje no devuelve nada
almaceno el mensaje , y ya no esta vacio el mensaje


CLase MyWritter
variables
objeto message

constructorsobecargado

run(Runnablle);
crea un array [] de strings e inserto 5 o 6 mensajitos

crao un objeto random para dormir el hilo hasta 2 s

bucle llamo a write para escribir los mensajes,
justo despues de escribir un mensje lo duermo

al final escribo "EOF", "Fin"


 */
public class Main {
    public static void main(String[] args) {
        Message m = new Message();
        new Thread(new Escritor(m,ThreadColor.ANSI_BLUE)).start();
        new Thread(new Lector(m,ThreadColor.ANSI_RED)).start();


    }

}
