package Rooms;
import java.util.ArrayList;

public class SingleRoom extends Room {
    private double reservationCost = 150;

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
        this.ratePerDay = 100;
    }

    public void setRatePerDay(double rate) {super.ratePerDay = rate;}
    public double getRatePerDay() {return this.ratePerDay;}

    public double getReservationPrice() {return this.reservationCost;}
    public void setReservationPrice(double price) {this.reservationCost=price;}

}

