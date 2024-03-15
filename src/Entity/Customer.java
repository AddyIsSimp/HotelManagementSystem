package Entity;
import java.util.ArrayList;
import Rooms.Reservation;

public class Customer extends Person {
    ArrayList<Transact> transactions = new ArrayList<>();
    private Reservation reservation;

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
    public void setReservation() {

    }

}
