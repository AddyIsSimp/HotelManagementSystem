package Rooms;
import java.util.ArrayList;

public class SingleRoom extends Room {

    public SingleRoom() {
        super();
        setRoomType();
    }

    public SingleRoom(Room ob) {
        super(ob);
        setRoomType();
    }

    public SingleRoom(int roomNumber) {
        super(roomNumber);
        setRoomType();
    }

    public void setRoomType() {
        this.roomType = "Single";
        this.ratePer = 100;
    }


}

