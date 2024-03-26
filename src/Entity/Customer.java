package Entity;
import java.util.ArrayList;
import java.util.Calendar;

import Rooms.Reservation;

public class Customer extends Person {
    ArrayList<Transact> transactions = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

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
    public void addReservation(int roomNum, Calendar startDate, Calendar endDate) {
        reservations.add(new Reservation(roomNum, startDate, endDate));
        System.out.println("Reservation added successfully!");
    }

    public ArrayList<Reservation> getReservations() {return reservations;}

}
