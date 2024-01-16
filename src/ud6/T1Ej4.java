package ud6;

import java.net.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class T1Ej4 {
    public static void main(String[] args) {

        try{
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                NetworkInterface networkInterface = interfaces.nextElement();
            List<InterfaceAddress> interfaceAddresses= networkInterface.getInterfaceAddresses();
            for (InterfaceAddress i : interfaceAddresses){
                System.out.println(i.getBroadcast());
                System.out.println(i.getAddress());
                System.out.println(i.getNetworkPrefixLength());
                }
            }
        } catch (SocketException e) {
            System.out.println(e.getMessage());        }

    }
}
