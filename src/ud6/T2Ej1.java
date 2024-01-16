package ud6;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class T2Ej1 {
    public static void main(String[] args) {
     try
    {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> inet = networkInterface.getInetAddresses();
            while (inet.hasMoreElements()) {
                InetAddress add = inet.nextElement();
                if (add.isSiteLocalAddress()){
                    List<InterfaceAddress> interfaceAddresses= networkInterface.getInterfaceAddresses();
                    for (InterfaceAddress i : interfaceAddresses){
                        System.out.println(i.getBroadcast());
                    }
                }
            }
        }
    } catch(SocketException e)

    {
        System.out.println(e.getMessage());
    }
}
}