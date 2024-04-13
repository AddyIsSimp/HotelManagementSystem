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
    protected Date startDate;
    protected Date endDate;
    protected ArrayList<Transact> transacts = new ArrayList<>();
    private String transactType;        //Reservation, room, orders
    protected double bills;
    protected boolean isPaid;
    protected Date dateOfTrans;

    public Transact() {

    }

    public Transact(Transact transact) {
        this.setDateOfTrans(transact.getDateOfTrans());
        this.setCustomer(transact.getCustomer());
        this.setStartDate(transact.getStartDate());
        this.setBills(transact.getBills());
    }

    public Transact(Date transactionDate, Customer customer, Date startDate, double amount) {
        this.dateOfTrans = transactionDate;
        this.customer = customer;
        this.startDate = dateOfTrans;
        this.bills = amount;
    }

    public Transact(Date transactionDate, Customer customer, Date startDate, double amount, String transactType) {
        this.dateOfTrans = transactionDate;
        this.customer = customer;
        this.startDate = dateOfTrans;
        this.bills = amount;
    }

    public void displayTransact() {
        System.out.print("Customer: " + customer + " || Type: " + transactType);
    }

    public void setDateOfTrans(Date currentDate) {this.dateOfTrans = currentDate;}
    public Date getDateOfTrans() {return this.dateOfTrans;}

    public void setCustomer(Customer customer) {this.customer = customer;}
    public Customer getCustomer(){return this.customer;}

    public void setIsPaid(boolean isPaid) {this.isPaid = isPaid;}
    public boolean getIsPaid() {return isPaid;}

    public void setStaff(Staff staff) {this.staff = staff;}
    public Staff getStaff() {return this.staff;}

    public void setStartDate(Date date) {this.startDate = date;}
    public Date getStartDate() {return startDate;}

    public void setTransactType(String string) {this.transactType = transactType;}
    public String getTransactType() {return this.transactType;}

    public void setBills(double amount) {this.bills = amount;}
    public double getBills() {return this.bills;}

}
