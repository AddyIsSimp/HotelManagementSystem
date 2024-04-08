package Entity;
import java.util.ArrayList;

import Foods.Menu;
import Transaction.Order;
import Rooms.Date;
import Rooms.Reservation;
import Rooms.Room;
import Transaction.Transact;

public class Customer extends Person {
    ArrayList<Transact> transHistory = new ArrayList<>();
    ArrayList<Transact> transact = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private double bills = 0;
    private double refunds = 0;

    public Customer() {
        this.name = null;
        setPassword(null);
        setEmail(null);
    }

    public Customer(String name, String password) {
        this.name = name;
        setPassword(password);
        setEmail(null);
    }

    public Customer(String name, String password, String email) {
        this.name = name;
        setPassword(password);
        setEmail(email);
    }

    //Need to modify
    public void addReservation(Room room, Date startDate, int duration) {
        reservations.add(new Reservation(room, startDate, duration));
        System.out.println("Reservation added successfully!");
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation added successfully!");
    }

    public ArrayList<Reservation> getReservations() {return reservations;}

    public void addTransact(Transact transact) {this.transact.add(transact);}
    public ArrayList<Transact> getTransact() {return this.transact;}

}
