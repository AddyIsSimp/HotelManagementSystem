package Rooms;

public class CoupleRoom extends Room{

    public CoupleRoom() {
        super();
        setRoomType();
    }

    public CoupleRoom(Room ob) {
        super(ob);
        setRoomType();
    }

    public CoupleRoom(int roomNumber) {
        super(roomNumber);
        setRoomType();
    }

    public void setRoomType() {
        this.roomType = "Couple";
        this.ratePerDay = 100;
    }

    public void setRate() {

    }

}
