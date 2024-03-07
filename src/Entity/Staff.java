package Entity;
import java.util.ArrayList;
public class Staff extends Person{
    private ArrayList<Transact> sales= new ArrayList<>();

    public Staff() {

    }

    public Staff(String name, String password) {
        this.name = name;
        setPassword(password);
    }

    public Staff(String name, String password, String email) {
        this.name = name;
        setPassword(password);
        setEmail(email);
    }

}
