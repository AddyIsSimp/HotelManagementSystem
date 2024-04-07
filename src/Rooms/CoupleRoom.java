package Rooms;

public class CoupleRoom extends Room{
    private double reservationCost = 250;

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
        this.ratePerDay = 200;
    }

    public void setRatePerDay(double rate) {super.ratePerDay = rate;}
    public double getRatePerDay() {return this.ratePerDay;}

    public double getReservationPrice() {return this.reservationCost;}
    public void setReservationPrice(double price) {this.reservationCost=price;}

}
