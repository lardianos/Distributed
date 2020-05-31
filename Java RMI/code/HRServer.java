import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class HRServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Java RMI registry Created");
        } catch(RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }
        HRImpl hotel;
        try {
            hotel = new HRImpl();
            Naming.rebind("rmi://localhost/Hotel", hotel);
            System.out.println("Server runing");
            Thread Thread1 = new Thread(hotel);
            Thread1.start();
        } catch(RemoteException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
        }

    }
}
