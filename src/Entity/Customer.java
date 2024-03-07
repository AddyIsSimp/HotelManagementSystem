package Entity;
import java.util.ArrayList;
public class Customer extends Person {
    ArrayList<Transact> transactions = new ArrayList<>();

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
}
