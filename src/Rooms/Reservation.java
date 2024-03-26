package Rooms;
import java.util.Calendar;
import Rooms.Room;
import Amenity.*;

public class Reservation {

    //Assign the reservation in RoomNum not in the room instance

    private Amenity amenity;
    private Room room;
    private Calendar startDate;
    private Calendar endDate;

    public Reservation() {
        room.setRoomNum(-1);
        startDate = null;
        endDate = null;
    }

    public Reservation(int roomNum, Calendar startDate, Calendar endDate) {
        this.room.setRoomNum(roomNum);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation(String amenityCode, Calendar startDate, Calendar endDate) {
        this.amenity.setAmenityCode(amenityCode);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setstartDate(Calendar startDate) {this.startDate = startDate;}
    public Calendar getStartDate() {return startDate;}

    public void setendDate(Calendar endDate) {this.endDate = endDate;}
    public Calendar getEndDate() {return endDate;}

    public void setRoom(Room room) {this.room = room;}
    public Room getRoom() {return room;}

}
