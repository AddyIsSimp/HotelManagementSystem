package Rooms;

public class FamilyRoom extends Room {
    private double reservationCost = 350;

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
        this.ratePerDay = 350;
    }

    public void setRatePerDay(double rate) {super.ratePerDay = rate;}
    public double getRatePerDay() {return this.ratePerDay;}

    public double getReservationPrice() {return this.reservationCost;}
    public void setReservationPrice(double price) {this.reservationCost=price;}

}
