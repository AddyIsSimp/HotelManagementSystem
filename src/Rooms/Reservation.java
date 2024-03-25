package Rooms;
import java.util.Calendar;
import Rooms.Room;

public class Reservation {

    //Assign the reservation in RoomNum not in the room instance

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

    public void setstartDate(Calendar startDate) {this.startDate = startDate;}
    public void setendDate(Calendar endDate) {this.endDate = endDate;}

}
