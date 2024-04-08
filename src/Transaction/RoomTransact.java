package Transaction;

import Entity.Customer;
import Entity.Staff;
import Rooms.Date;
import Rooms.Room;

public class RoomTransact extends Transact{
    protected Room room;
    private String transactType = "Room";        //Reservation, room, orders

    public RoomTransact() {

    }

    public RoomTransact(Customer customer, Room room, Date dateOfTrans, double amount) {
        super(customer, dateOfTrans, amount);
        this.room = room;
    }

    public void setCustomer(Customer customer) {super.customer = customer;}
    public Customer getCustomer(){return this.customer;}

    public void setRoom(Room room) {this.room = room;}
    public Room getRoom() {return room;}

    public void setStaff(Staff staff) {this.staff = staff;}
    public Staff getStaff() {return this.staff;}

    public void setDate(Date date) {this.date = date;}
    public Date getDate() {return date;}

    public void setTransactType(String string) {this.transactType = transactType;}
    public String getTransactType() {return this.transactType;}

}
