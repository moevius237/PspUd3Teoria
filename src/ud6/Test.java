package ud6;

import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            Iterator<NetworkInterface> it = interfaces.asIterator();
            while (it.hasNext()){
                NetworkInterface networkInterface = it.next();
                Iterator<InetAddress>addressIterator= networkInterface.getInetAddresses().asIterator();
                while (addressIterator.hasNext()){
                    InetAddress inetAddress = addressIterator.next();
                    if (inetAddress instanceof Inet4Address){
                        System.out.println(Arrays.toString(inetAddress.getAddress()));
                    }
                }
                List<InterfaceAddress> interfaceAddresses= networkInterface.getInterfaceAddresses();
                for (InterfaceAddress i : interfaceAddresses){
                    System.out.println(i.getBroadcast());
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        try(Stream<NetworkInterface> networkInterface = NetworkInterface.networkInterfaces()) {

       } catch (SocketException e) {
           System.out.println(e.getMessage());
       }
    }
}
