//Arxikopihsi ton methodon (Client View)
import java.util.*;

public interface HRInterface extends java.rmi.Remote {
    public String[] prit_list()
        throws java.rmi.RemoteException;

    public String[] book_reservations(String Type,String num, String Name)
        throws java.rmi.RemoteException;

    public Hashtable<Integer, String[]> print_guests_list()
        throws java.rmi.RemoteException;

    public Hashtable<Integer, String[]> cancel_book(String Type,String num, String Name)
        throws java.rmi.RemoteException;

    // Method to add a new listener for temperature changes
    public void add_book_listener(HRListener hrlistener, String Type)
        throws java.rmi.RemoteException;
        
}
