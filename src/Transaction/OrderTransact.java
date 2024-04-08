package Transaction;

import Entity.Customer;
import Entity.Staff;
import Foods.Menu;
import Rooms.Date;

import java.util.ArrayList;

public class OrderTransact extends Transact {
    ArrayList<Menu> menuOrdered = new ArrayList<>();
    private String transactType = "Order";

    public OrderTransact() {

    }

    public OrderTransact(Customer customer, Date dateOfTrans, double amount) {
        super(customer, dateOfTrans, amount);
    }

    public void setCustomer(Customer customer) {super.customer = customer;}
    public Customer getCustomer(){return this.customer;}

    public void setStaff(Staff staff) {this.staff = staff;}
    public Staff getStaff() {return this.staff;}

    public void setDate(Date date) {this.date = date;}
    public Date getDate() {return date;}

    public void setTransactType(String string) {this.transactType = transactType;}
    public String getTransactType() {return this.transactType;}

}
