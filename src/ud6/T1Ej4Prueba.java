package ud6;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;

public class T1Ej4Prueba {
    public static void main(String[] args) {

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface inter = interfaces.nextElement();
                System.out.println(inter.getDisplayName());
                System.out.println(inter.getInetAddresses());

            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
