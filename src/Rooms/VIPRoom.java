package Rooms;

public class VIPRoom extends Room{
    public VIPRoom() {
        super();
        setRoomType();
    }

    public VIPRoom(Room ob) {
        super(ob);
        setRoomType();
    }

    public VIPRoom(int roomNumber) {
        super(roomNumber);
        setRoomType();
    }

    public void setRoomType() {
        this.roomType = "VIP";
        this.ratePer = 1000;
    }


}
