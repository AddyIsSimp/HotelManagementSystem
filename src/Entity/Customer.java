package Entity;
import java.util.ArrayList;

import Rooms.Date;
import Rooms.Reservation;
import Rooms.Room;
import Transaction.Transact;

public class Customer extends Person {
    ArrayList<Transact> transHistory = new ArrayList<>();
    ArrayList<Transact> transact = new ArrayList<>();                       //For hotel transaction
    ArrayList<Reservation> reservations = new ArrayList<>();
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

    public void addReservation(Room room, Date startDate, int duration) {
        reservations.add(new Reservation(room, startDate, duration));
    }

    public void addRefund(double refund) {this.refunds+=refund;}
    public double getRefunds() {
        return refunds;
    }
    public void sendRefunds() {
        this.refunds = 0;
    }

    public void addTransHistory(Transact transact) {this.transHistory.add(transact);}
    public ArrayList<Transact> getTransHistory() {return transHistory;}

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public ArrayList<Reservation> getReservations() {return reservations;}

    public void addTransact(Transact transact) {this.transact.add(transact);}
    public ArrayList<Transact> getTransact() {return this.transact;}

}
