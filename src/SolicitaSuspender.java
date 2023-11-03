
public class SolicitaSuspender {
    private boolean suspended;

    public boolean isSuspended() {
        return suspended;
    }

    public synchronized void setSuspender(boolean b) {
        suspended = b;
        notify();
    }

    public synchronized void esperando() {
        while (suspended) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }
}
