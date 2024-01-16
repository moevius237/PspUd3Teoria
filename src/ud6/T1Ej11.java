package ud6;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class T1Ej11 {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String s = ia.getHostName();
            String sa = ia.getHostAddress();
            System.out.println("Name: " + s + "Address: " + sa);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
