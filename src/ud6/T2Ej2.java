package ud6;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.List;

public class T2Ej2 {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = Inet4Address.getByAddress(new byte[]{(byte)255, (byte)255,(byte)255,(byte) 0});

            Boolean bole = inetAddress.isReachable(100);
            if (bole){
                System.out.println(inetAddress.getAddress());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

