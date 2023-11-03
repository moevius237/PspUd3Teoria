
public class HiloSolicita extends Thread {
    private SolicitaSuspender solicita = new SolicitaSuspender();
    private String color;

    public HiloSolicita(String name, String color) {
        super(name);
        this.color = color;
    }

    public void suspender() {
        solicita.setSuspender(true);
    }

    public void reanudar() {
        solicita.setSuspender(false);
    }

    public void run() {
        int contador = 0;

        while (true) {
            if (solicita.isSuspended()) {
                System.out.println(color + contador);
            }
            solicita.esperando();
            System.out.println(color + "Soy " + Thread.currentThread().getName() + " " + contador);
            contador++;
        }
    }
}
