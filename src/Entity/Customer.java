package Entity;
import java.util.ArrayList;
import java.util.Calendar;

import Rooms.Date;
import Rooms.Reservation;
import Rooms.Room;

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
    public void addReservation(Room room, Date startDate, int duration) {
        reservations.add(new Reservation(room, startDate, duration));
        System.out.println("Reservation added successfully!");
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation added successfully!");
    }

    public ArrayList<Reservation> getReservations() {return reservations;}

}
