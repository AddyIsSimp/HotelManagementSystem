package Transaction;

import Amenity.Amenity;
import Entity.Customer;
import Entity.Staff;
import Rooms.Date;
import Rooms.Reservation;
import Rooms.Room;

public class ReserveTransact extends Transact {
    private String transactType = "Reservation";        //Reservation, room, orders
    private Reservation reservation;
    boolean rsrvEnded = false;                          //Determines if the reservation have ended

    public ReserveTransact() {
    }

    public ReserveTransact(Date transactionDate, Customer customer, Date startDate, double bills, Reservation reservation) {
        super(transactionDate, customer, startDate, bills);
        this.reservation = reservation;
        calculateEndDate(reservation);
    }

    public void setRsrvEnded(boolean b) {this.rsrvEnded=b;}
    public boolean getRsrvEnded() {return this.rsrvEnded;}

    public void setReservation(Reservation  reservation) {this.reservation = reservation;}
    public Reservation getReservation() {return reservation;}

    public void setDateOfTrans(Date currentDate) {this.dateOfTrans = currentDate;}
    public Date getDateOfTrans() {return this.dateOfTrans;}

    public void setCustomer(Customer customer) {super.customer = customer;}
    public Customer getCustomer(){return this.customer;}

    public void setStaff(Staff staff) {this.staff = staff;}
    public Staff getStaff() {return this.staff;}

    public void setStartDate(Date date) {this.startDate = date;}
    public Date getStartDate() {return startDate;}

    public void calculateEndDate(Reservation reservation) {
        this.endDate = reservation.getEndDate();
    }

    public void setTransactType(String string) {this.transactType = transactType;}
    public String getTransactType() {return this.transactType;}
}
