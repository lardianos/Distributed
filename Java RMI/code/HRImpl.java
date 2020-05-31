import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
//Ilopihsi ton methodon ston (Server)
public class HRImpl extends UnicastRemoteObject implements HRInterface, Runnable {
    // Room types
    private int Room_type_A; //Single rooms
    private int Room_type_A_cost; //Evro;
    private int Room_type_B; //Double rooms
    private int Room_type_B_cost; //Evro;
    private int Room_type_C; //Twin rooms
    private int Room_type_C_cost; //Evro;
    private int Room_type_D; //Triple rooms
    private int Room_type_D_cost; //Evro;
    private int Room_type_E; //Quad rooms
    private int Room_type_E_cost; //Evro;

    private List<HRListener> Room_type_A_listeners = new ArrayList<>();
    private List<HRListener> Room_type_B_listeners = new ArrayList<>();
    private List<HRListener> Room_type_C_listeners = new ArrayList<>();
    private List<HRListener> Room_type_D_listeners = new ArrayList<>();
    private List<HRListener> Room_type_E_listeners = new ArrayList<>();

    // Variables
    private int key_counter;
    private int Reservations_num;

    // Reservations Hashtable
    Hashtable<Integer, String[]> Reservations;

    // Constractor
    public HRImpl() throws RemoteException{
        super(0);
        key_counter = 0;
        Reservations_num=0;
        Reservations = new Hashtable<Integer, String[]>();
        Room_type_A = 30;
        Room_type_B = 45;
        Room_type_C = 25;
        Room_type_D = 10;
        Room_type_E = 5;
        Room_type_A_cost = 50;
        Room_type_B_cost = 70;
        Room_type_C_cost = 80;
        Room_type_D_cost = 120;
        Room_type_E_cost = 150;
    }

    @Override
    public void run(){
        while(true){
            System.out.println(Room_type_A_listeners.isEmpty());
            if((!Room_type_A_listeners.isEmpty()) && (Room_type_A != 0)){
                notifyHRListeners("A");
            }else if ((!Room_type_B_listeners.isEmpty()) && (Room_type_B != 0)) {
                notifyHRListeners("B");
            }else if ((!Room_type_C_listeners.isEmpty()) && (Room_type_C != 0)) {
                notifyHRListeners("C");
            }else if ((!Room_type_D_listeners.isEmpty()) && (Room_type_D != 0)) {
                notifyHRListeners("D");
            }else if ((!Room_type_E_listeners.isEmpty()) && (Room_type_E != 0)) {
                notifyHRListeners("E");
            }
        }
    }
    // RMI methods
    @Override
    public String[] prit_list(){
        //System.out.println("tipoma listas");
        //System.out.println(Room_type_A+" Domatia Tipou A me "+Room_type_A_cost+" Evro ti Vradia");
        //System.out.println(Room_type_B+" Domatia Tipou A me "+Room_type_B_cost+" Evro ti Vradia");
        //System.out.println(Room_type_C+" Domatia Tipou A me "+Room_type_C_cost+" Evro ti Vradia");
        //System.out.println(Room_type_D+" Domatia Tipou A me "+Room_type_D_cost+" Evro ti Vradia");
        //System.out.println(Room_type_E+" Domatia Tipou A me "+Room_type_E_cost+" Evro ti Vradia");
        String[] list = new String[5];
        list[0] = Integer.toString(Room_type_A)+" Domatia Tipou A me "+Integer.toString(Room_type_A_cost)+" Evro ti Vradia";
        list[1] = Integer.toString(Room_type_B)+" Domatia Tipou B me "+Integer.toString(Room_type_B_cost)+" Evro ti Vradia";
        list[2] = Integer.toString(Room_type_C)+" Domatia Tipou C me "+Integer.toString(Room_type_C_cost)+" Evro ti Vradia";
        list[3] = Integer.toString(Room_type_D)+" Domatia Tipou D me "+Integer.toString(Room_type_D_cost)+" Evro ti Vradia";
        list[4] = Integer.toString(Room_type_E)+" Domatia Tipou E me "+Integer.toString(Room_type_E_cost)+" Evro ti Vradia";
        //System.out.println(list[0]);
        return list;
    }
    public String[] book_reservations(String Type,String num, String Name ){
        String res[] = new String[4];
        String booked[] = new String[2];
        res[0]=Type;
        res[1]=num;
        res[2]=Name;
        int converted_num = Integer.parseInt(num);
        //System.out.println(Integer.parseInt(num));
        int cost = 0;
        if(Type.equals("A")){
            if (Room_type_A >= converted_num ) {
                Room_type_A -= converted_num;
                cost = Room_type_A_cost * converted_num;
                res[3]= Integer.toString(cost);
                Reservations.put(key_counter,res);
                Reservations_num++;
                key_counter++;

                System.out.println("kratisi gia A");
            }else {
                System.out.println("Mikroteros Arithmos Domation Sto A");
                booked[0]=Integer.toString(Room_type_A);
                return booked;
            }
        } else if (Type.equals("B")) {
            if(Room_type_B >= converted_num){
                Room_type_B -= converted_num;
                cost = Room_type_A_cost * converted_num;
                res[3]= Integer.toString(cost);
                Reservations.put(key_counter,res);
                Reservations_num++;
                key_counter++;
                System.out.println("kratisi gia B");
            }else {
                System.out.println("Mikroteros Arithmos Domation Sto B");
                booked[0]=Integer.toString(Room_type_B);
                return booked;
            }
        } else if (Type.equals("C")) {
            if(Room_type_C >= converted_num){
                Room_type_C -= converted_num;
                cost = Room_type_A_cost * converted_num;
                res[3]= Integer.toString(cost);
                Reservations.put(key_counter,res);
                Reservations_num++;
                key_counter++;
                System.out.println("kratisi gia C");
            }else {
                System.out.println("Mikroteros Arithmos Domation Sto C");
                booked[0]=Integer.toString(Room_type_C);
                return booked;
            }
        } else if (Type.equals("D")) {
            if(Room_type_D >= converted_num){
                Room_type_D -= converted_num;
                cost = Room_type_A_cost * converted_num;
                res[3]= Integer.toString(cost);
                Reservations.put(key_counter,res);
                Reservations_num++;
                key_counter++;
                System.out.println("kratisi gia D");
            }else {
                System.out.println("Mikroteros Arithmos Domation Sto D");
                booked[0]=Integer.toString(Room_type_D);
                return booked;
            }
        } else if (Type.equals("E")) {
            if(Room_type_E >= converted_num){
                Room_type_E -= converted_num;
                cost = Room_type_A_cost * converted_num;
                res[3]= Integer.toString(cost);
                Reservations.put(key_counter,res);
                Reservations_num++;
                key_counter++;
                System.out.println("kratisi gia E");
            }else {
                System.out.println("Mikroteros Arithmos Domation Sto E");
                booked[0]=Integer.toString(Room_type_E);
                return booked;
            }
        }
        String ptr[];
        System.out.println("display values:");
        System.out.println("============================");
        for (int i =0;i< Reservations.size(); i++ ) {
            ptr=(String[])Reservations.get(i);
            System.out.println(ptr[0]+ptr[1]+ptr[2]);
        }
        System.out.println("============================");
        booked[0]="booked";
        booked[1]= res[3];
        return booked;
    }
    public Hashtable<Integer, String[]> print_guests_list(){
        Enumeration e = Reservations.elements();
        System.out.println("display values:");
        String ptr[];
        while (e.hasMoreElements()) {
            ptr=(String[])e.nextElement();
            System.out.println(ptr[0]+ptr[1]+ptr[2]+ptr[3]);
        }
        System.out.println("============================");
        return Reservations;
    }

    public Hashtable<Integer, String[]> cancel_book(String Type,String num, String Name){
        boolean flag_registration = false;
        Hashtable<Integer, String[]> Person_Reservations = new Hashtable<Integer, String[]>();
        Enumeration en_key = Reservations.keys();
        Enumeration en_element = Reservations.elements();
        int key_val = 0;
        String element_val[] = new String[4];
        int tmp;
        while(en_key.hasMoreElements() && en_element.hasMoreElements()){
            element_val = (String[])en_element.nextElement();
            key_val = (int)en_key.nextElement();
            if (element_val[2].equals(Name)) {
                Person_Reservations.put(key_val,element_val);
                //System.out.println("aa");
            }
        }
        en_key = Person_Reservations.keys();
        en_element = Person_Reservations.elements();
        while (en_key.hasMoreElements() && en_element.hasMoreElements()) {
            element_val = (String[])en_element.nextElement();
            key_val = (int)en_key.nextElement();
            if(Type.equals(element_val[0])){
                flag_registration = true;
                break;
            }
        }
        if(flag_registration==true){
            if(Integer.parseInt(num) <= Integer.parseInt(element_val[1])){
                tmp = Integer.parseInt(element_val[1]) - Integer.parseInt(num);
                if(Type.equals("A")){
                    Room_type_A += Integer.parseInt(num);
                }else if (Type.equals("B")) {
                    Room_type_B += Integer.parseInt(num);
                }else if (Type.equals("C")) {
                    Room_type_C += Integer.parseInt(num);
                }else if (Type.equals("D")) {
                    Room_type_D += Integer.parseInt(num);
                }else if (Type.equals("E")) {
                    Room_type_E += Integer.parseInt(num);
                }
                if(tmp == 0){
                    Reservations.remove(key_val);
                    Person_Reservations.remove(key_val);
                }else{
                    element_val[1] = Integer.toString(tmp);
                    Reservations.replace(key_val,element_val);
                    Person_Reservations.replace(key_val,element_val);
                }
            }else{
                Person_Reservations.clear();
                element_val[0]="Mi egiros aritmos domation";
                Person_Reservations.put(-1,element_val);
                return Person_Reservations;
            }
        }else {
            Person_Reservations.clear();
            element_val[0]="Den vrethike antistixi kratisi";
            Person_Reservations.put(-1,element_val);
            return Person_Reservations;
        }
        return Person_Reservations;
    }

    @Override
    public synchronized void add_book_listener(HRListener hrlistener, String Type) throws java.rmi.RemoteException{
        if(Type.equals("A")){
            System.out.println(Room_type_A_listeners.isEmpty());
            Room_type_A_listeners.add(hrlistener);
            System.out.println("added");
            System.out.println(Room_type_A_listeners.isEmpty());
        } else if (Type.equals("B")) {
            Room_type_B_listeners.add(hrlistener);
        } else if (Type.equals("C")) {
            Room_type_C_listeners.add(hrlistener);
        } else if (Type.equals("D")) {
            Room_type_D_listeners.add(hrlistener);
        } else if (Type.equals("E")) {
            Room_type_E_listeners.add(hrlistener);
        }
        System.out.println("Added new listener");
    }

    private synchronized void notifyHRListeners(String Type){
        if(Type.equals("A")){
            for(HRListener listener1 : Room_type_A_listeners){
                try {
                    listener1.book_released();
                } catch (RemoteException aInE) {
                    System.out.println("client disconected");
                }
            }
            Room_type_A_listeners.clear();
        } else if (Type.equals("B")) {
            for(HRListener listener1 : Room_type_B_listeners){
                try {
                    listener1.book_released();
                } catch (RemoteException aInE) {
                    System.out.println("client disconected");
                }
            }
            Room_type_B_listeners.clear();
        } else if (Type.equals("C")) {
            for(HRListener listener1 : Room_type_C_listeners){
                try {
                    listener1.book_released();
                } catch (RemoteException aInE) {
                    System.out.println("client disconected");
                }
            }
            Room_type_C_listeners.clear();
        } else if (Type.equals("D")) {
            for(HRListener listener1 : Room_type_D_listeners){
                try {
                    listener1.book_released();
                } catch (RemoteException aInE) {
                    System.out.println("client disconected");
                }
            }
            Room_type_D_listeners.clear();
        } else if (Type.equals("E")) {
            for(HRListener listener1 : Room_type_E_listeners){
                try {
                    listener1.book_released();
                } catch (RemoteException aInE) {
                    System.out.println("client disconected");
                }
            }
            Room_type_E_listeners.clear();
        }
    }
}
