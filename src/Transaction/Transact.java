package Transaction;
import Entity.Customer;
import Entity.Staff;
import Rooms.Date;
import Rooms.Room;

import java.util.ArrayList;
import java.util.Calendar;
public class Transact {
    protected Customer customer;
    protected Staff staff;                //If there is now a staff name means the cash is accepted by the staff
    protected Date date;
    protected ArrayList<Transact> transacts = new ArrayList<>();
    private String transactType;        //Reservation, room, orders
    private double amount;

    public Transact() {

    }

    public Transact(Customer customer, Date dateOfTrans, double amount) {
        this.customer = customer;
        this.date = dateOfTrans;
        this.amount = amount;
    }

    public void displayTransact() {
        System.out.print("Customer: " + customer + " || Type: " + transactType);
    }

    public void setCustomer(Customer customer) {this.customer = customer;}
    public Customer getCustomer(){return this.customer;}

    public void setStaff(Staff staff) {this.staff = staff;}
    public Staff getStaff() {return this.staff;}

    public void setDate(Date date) {this.date = date;}
    public Date getDate() {return date;}

    public void setTransactType(String string) {this.transactType = transactType;}
    public String getTransactType() {return this.transactType;}

    public void setAmount(double amount) {this.amount = amount;}
    public double getAmount() {return this.amount;}

}
