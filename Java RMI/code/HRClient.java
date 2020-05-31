import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class HRClient extends UnicastRemoteObject implements HRListener {

    public HRClient() throws RemoteException{
        super();
    }
    public static void main(String[] args) {
        String inputs[] = new String[3];
        if (args.length == 0) {
            print_menu();
            return ;
        }
        System.out.println(args[0]);
        try {
            //HRInterface h = (HRInterface)Naming.lookup("rmi://"+args[1]+"/Hotel");
            Remote remote1 = Naming.lookup("rmi://"+args[1]+"/Hotel");
            HRInterface h = (HRInterface) remote1;
            if (args[0].compareTo("list") == 0) {
                    System.out.println("tipoma listas");
                    String str[];
                    str = h.prit_list();
                    for (int i=0;i<str.length; i++) {
                        System.out.println(str[i]);
                    }
            }else if (args[0].compareTo("book") == 0) {
                System.out.println("kratisi");
                String str[];
                String read_text;
                inputs[0] = args[2];
                inputs[1] = args[3];
                inputs[2] = args[4];
                Scanner scan= new Scanner(System.in);
                while (true) {
                    str = h.book_reservations(inputs[0],inputs[1],inputs[2]);
                    if (str[0].equals("booked")) {
                        System.out.println("Epitixis kratisi");
                        System.out.println("Kostos = "+str[1]);
                        break;
                    }else if (Integer.parseInt(str[0]) == 0) {
                        System.out.println("Ta domatia Tipou "+args[2]+" Eksadlithikan!");
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("Thelete na graftite stis listes anamonis tou ksenodoxiou?");
                        System.out.println("-Epiloges");
                        System.out.println("-(yes) OR Press (Enter) To Exit");
                        System.out.print("> ");
                        System.out.println("---------------------------------------------------------------------");
                        read_text = scan.nextLine();
                        if (read_text.equals("yes")) {
                            HRClient clobj = new HRClient();
                            h.add_book_listener(clobj, args[2]);
                            break;
                        }else{
                            break;
                        }

                    }else {
                        System.out.println("Yparxoun mono "+str[0]+" Domatia Tipou "+args[2]);
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("Thelete na kanete kratisi gia afta?");
                        System.out.println("-Epiloges");
                        System.out.println("-(yes) OR Press (Enter) To Exit");
                        System.out.print("> ");
                        System.out.println("---------------------------------------------------------------------");
                        read_text = scan.nextLine();
                        if (read_text.equals("yes")) {
                            inputs[1] = str[0];
                            continue;
                        }else{
                            break;
                        }
                    }
                }
            }else if (args[0].compareTo("guest") == 0) {
                Hashtable<Integer, String[]> R;
                System.out.println("Tipoma Kratiseon");
                R = h.print_guests_list();
                Enumeration e = R.elements();
                String ptr[];
                while (e.hasMoreElements()) {
                    ptr=(String[])e.nextElement();
                    System.out.println("Tipos ("+ptr[0]+") Aritmos("+ptr[1]+") Onoma ("+ptr[2]+") Kostos("+ptr[3]+")");
                    //System.out.println("Key is "e.keys());
                }
            }else if (args[0].compareTo("cancel") == 0) {
                inputs[0] = args[2];
                inputs[1] = args[3];
                inputs[2] = args[4];
                Hashtable<Integer, String[]> R;
                System.out.println("akirosi kratisis");
                R=h.cancel_book(inputs[0],inputs[1],inputs[2]);
                Enumeration e = R.elements();
                Enumeration k = R.keys();
                String ptr[];
                int n;
                while (e.hasMoreElements() && k.hasMoreElements()) {
                    ptr=(String[])e.nextElement();
                    n= (int)k.nextElement();
                    if(n == -1){
                        System.out.println(ptr[0]);
                    }else {
                        System.out.println("Tipos ("+ptr[0]+") Aritmos("+ptr[1]+") Onoma ("+ptr[2]+")");
                    }
                }

            }
        } catch(RemoteException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
        }
    }
    public static void print_menu(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-(1)-(Print Room list)");
        System.out.println("-java HRClient list <hostname>");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-(2)-(Room Book)");
        System.out.println("-java HRClient book <hostname> <type> <number> <name> ");
        System.out.println("-<type> is (A),(B),(C),(D),(E)");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-(3)-(Print Reservations list)");
        System.out.println("-java HRClient guest <hostname>");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-(4)-(Print Room list)");
        System.out.println("-java HRClient cancel <hostname> <type> <number> <name>");
        System.out.println("-<type> is (A),(B),(C),(D),(E)");
        System.out.println("---------------------------------------------------------------------");
    }
    public void book_released() throws RemoteException{
        System.out.println("Iparxoun diathesima domatia");
    }
}
