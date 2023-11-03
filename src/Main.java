/*
Hilos compartian heap , no compartian thread stack
Variable


-Variable local que solo existe en el metodo creado,
se almacena en el stack
-Variables de instancia , se almacenan en el heap


Sychronized; Controlar cuando un hilo puede cambiar el valor en el heap


Bloqueo Intrinseco o intrinsinc lock o monitor
Todos los objetos tienen asociado un bloqueo intrinseco


 */
class CountDown{
    private int i;
    public void doCountDown(){

        String color;
        switch (Thread.currentThread().getName()){
            case "Hilo1 ":
               color = ThreadColor.ANSI_RED;
               break;
            case "Hilo2 ":
                color = ThreadColor.ANSI_BLUE;
                break;
            default:
                color = ThreadColor.ANSI_RESET;
        }
        synchronized (this){
        for (i = 10 ; i >0 ;i--){
            //Cuando no son variables de la clase solo se moficand en su propia clase
            //Esta compartiddas
            //Interferencia de ehilos o race condition
            //Thread-safe
            //No thread-safe los programadores los tenemos que convertir en thred-safe
            System.out.println(color +
                    Thread.currentThread().getName() + "i =" + i);
        }
        }
        }
    }

class ThreadCOunt extends Thread{
    private CountDown countDown;
    public ThreadCOunt(CountDown countDown){
        this.countDown = countDown;
    }
    @Override
    public void run(){
        countDown.doCountDown();
    }
}
public class Main {
    public static void main(String[] args) {
        CountDown c = new CountDown();
        CountDown c2 = new CountDown();
        ThreadCOunt t1 = new ThreadCOunt(c);
        t1.setName("Hilo1 ");

        ThreadCOunt t2 = new ThreadCOunt(c2);
        t2.setName("Hilo2 ");

        t1.start();
        t2.start();
    }
}
