package Transaction;

import Entity.Customer;
import Entity.Staff;
import Foods.Menu;
import Rooms.Date;
import Rooms.Room;
import Amenity.*;

import java.util.ArrayList;

public class HotelTransact extends Transact{
    protected Room room;
    protected Amenity amenity;
    ArrayList<Menu> menuOrdered = new ArrayList<>();
    private String transactType = "Hotel";        //Nag-occupy na ug room ug amenity

    public HotelTransact() {

    }

    public HotelTransact(Customer customer, Room room, Date dateOfTrans, double amount) {
        super(customer, dateOfTrans, amount);
        this.room = room;
    }

    public HotelTransact(Customer customer, Amenity amenity, Date dateOfTrans, double amount) {
        super(customer, dateOfTrans, amount);
        this.amenity = amenity;
    }

    public void setCustomer(Customer customer) {super.customer = customer;}
    public Customer getCustomer(){return this.customer;}

    public void setRoom(Room room) {this.room = room;}
    public Room getRoom() {return room;}

    public void setAmenity(Amenity amenity) {this.amenity = amenity;}
    public Amenity getAmenity() {return amenity;}

    public void setStaff(Staff staff) {this.staff = staff;}
    public Staff getStaff() {return this.staff;}

    public void setDate(Date date) {this.date = date;}
    public Date getDate() {return date;}

    public void setTransactType(String string) {this.transactType = transactType;}
    public String getTransactType() {return this.transactType;}

}
