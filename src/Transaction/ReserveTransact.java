package Transaction;

import Entity.Customer;
import Entity.Staff;
import Rooms.Date;
import Rooms.Reservation;

public class ReserveTransact extends Transact {
    private String transactType = "Reservation";        //Reservation, room, orders
    private Reservation reservation;

    public ReserveTransact() {
    }

    public ReserveTransact(Customer customer, Date dateOfTrans, double amount, Reservation reservation) {
        super(customer, dateOfTrans, amount);
        this.reservation = reservation;
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
