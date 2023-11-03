
public class MainSolicita {

    public static void main(String[] args) {
        HiloSolicita hilo = new HiloSolicita("hilo1", ThreadColor.ANSI_BLUE);
        HiloSolicita hilo2 = new HiloSolicita("hilo2", ThreadColor.ANSI_RED);

        hilo.start();
        hilo2.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hilo.suspender();
        hilo2.suspender();

        hilo.reanudar();
        hilo2.reanudar();

    }
}
