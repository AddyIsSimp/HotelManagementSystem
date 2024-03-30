package Rooms;

public class FamilyRoom extends Room {

    public FamilyRoom() {
        super();
        setRoomType();
    }

    public FamilyRoom(Room ob) {
        super(ob);
        setRoomType();
    }

    public FamilyRoom(int roomNumber) {
        super(roomNumber);
        setRoomType();
    }

    public void setRoomType() {
        this.roomType = "Family";
        this.ratePerDay = 300;
    }

}
