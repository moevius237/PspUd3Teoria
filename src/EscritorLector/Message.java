package EscritorLector;

/*
Nuevos metodos para solucionar bloqueo muerto
wait , notify y notifyall
wait el hilo se suspende y libera el bloqueo
notify notificar o emitir una notificacion de que algo a sucedido

Siempre que uso el wait lo hago en un bucle

Se usa notifyall en la mayoria de casos

se usa el notify cuando tengo un unico hilo y cuando todos los hilos hacen la misma tarea
 */

public class Message {
    private String message = "";
    private Boolean emprty;

    public synchronized String read(){
        while(emprty){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        emprty = true;
        notifyAll();
        return message;

    }
    public synchronized void write(String message){
        while (!emprty){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        emprty = false;
        this.message = message;
        notifyAll();

    }
}
