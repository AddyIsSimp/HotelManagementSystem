package Amenity;

public class GameRoom extends Amenity{
    private String amenityType = "GameRoom";
    private double reservationCost = 2000;

    public GameRoom() {
        super();
        this.setAmenityType("GameRoom");
        this.setReservationCost(2000);
    }

    public GameRoom(GameRoom game) {
        this.setAmenityCode(game.getAmenityCode());
        this.setAmenityType("GameRoom");
        this.setReservationCost(2000);
    }
    public GameRoom(String amenityCode){
        super(amenityCode);
        this.setAmenityType("GameRoom");
        this.setReservationCost(2000);
    }

    public void setReservationCost(double cost) {this.reservationCost=cost;}
    public double getReservationCost() {return reservationCost;}

    public void setAmenityType(String amenityType) {super.amenityType=amenityType;};
    public String getAmenityType() {return amenityType;}

    public void setAmenityCode(String code) {this.amenityCode = code;}
    public String getAmenityCode() {return amenityCode;}

    public void setReserved(boolean reserve) {this.isReserved = reserve;}
    public boolean getReserved() {return isReserved;}

}
