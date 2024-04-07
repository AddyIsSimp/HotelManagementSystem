package Rooms;

public class VIPRoom extends Room{
    private double reservationCost = 850;

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
        this.ratePerDay = 800;
    }

    public void setRatePerDay(double rate) {super.ratePerDay = rate;}
    public double getRatePerDay() {return this.ratePerDay;}

    public double getReservationPrice() {return this.reservationCost;}
    public void setReservationPrice(double price) {this.reservationCost=price;}

}
