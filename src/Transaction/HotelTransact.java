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
    private double roomCost;
    private int duration;

    public HotelTransact() {

    }

    public HotelTransact(HotelTransact transact, int duration) {
        super(transact.getDateOfTrans(), transact.getCustomer(), transact.getStartDate(), transact.getBills());
        this.roomCost = transact.getRoomCost();
        this.room = transact.getRoom();
        this.duration = duration;
        this.endDate = transact.calculateEndDate(transact.getStartDate(), duration);
    }

    public HotelTransact(Date transactionDate, Customer customer, Room room, Date startDate, double bills, int duration, double roomCost) {
        super(transactionDate, customer, startDate, bills);
        this.roomCost = roomCost;
        this.room = room;
        this.duration = duration;
        this.endDate = calculateEndDate(startDate, duration);
    }

    public HotelTransact(Date transactionDate, Customer customer, Amenity amenity, Date startDate, double bills, int duration, double roomCost) {
        super(transactionDate, customer, startDate, bills);
        this.roomCost = roomCost;
        this.amenity = amenity;
        this.duration = duration;
        this.endDate = calculateEndDate(startDate, duration);
    }

    public void setDateOfTrans(Date currentDate) {this.dateOfTrans = currentDate;}
    public Date getDateOfTrans() {return this.dateOfTrans;}

    public void setCustomer(Customer customer) {super.customer = customer;}
    public Customer getCustomer(){return this.customer;}

    public void setRoom(Room room) {this.room = room;}
    public Room getRoom() {return room;}

    public void addMenuOrder(Menu menu) {
        menuOrdered.add(menu);
        this.bills += menu.getTotalPrice();
    }
    public ArrayList<Menu> getMenuOrdered() {return menuOrdered;}

    public void setAmenity(Amenity amenity) {this.amenity = amenity;}
    public Amenity getAmenity() {return amenity;}

    public void setRoomCost(double cost) {this.roomCost = cost;}
    public double getRoomCost() {return roomCost;}

    public void setStaff(Staff staff) {this.staff = staff;}
    public Staff getStaff() {return this.staff;}

    public void setStartDate(Date date) {this.startDate = date;}
    public Date getStartDate() {return startDate;}

    public Date calculateEndDate(Date dateOfTrans, int duration) {
        Date date = dateOfTrans;

        for(int i = 0; i<duration; i++) {
            if(date.getDate()<=31 ) {   //Not lapse in 31 days
                date.setDate(date.incrementDate());
            }else {     //if lapse in 31 days
                if(date.getMonth()<=12) {   //Not lapse in 12 months
                    date.setMonth(date.incrementMonth());
                    date.setDate(1);
                }else {   //lapse in 12 months
                    date.setYear(date.incrementYear());
                    date.setMonth(1);
                    date.setDate(1);
                }
            }
        }

        this.endDate = date;
        return date;
    }
    public Date getEndDate() {return this.endDate;}

    public void setTransactType(String string) {this.transactType = transactType;}
    public String getTransactType() {return this.transactType;}

    public int getDuration() {return this.duration;}
    public void setDuration(int duration) {this.duration = duration;}
}
