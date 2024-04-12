package Amenity;

public class ReceptionHall extends Amenity{
    private String amenityType = "ReceptionHall";
    private double reservationCost = 8000;

    public ReceptionHall() {
        super();
    }

    public ReceptionHall(Amenity hall) {
        this.setAmenityCode(hall.getAmenityCode());
        this.setAmenityType("ReceptionHall");
        this.setReservationCost(hall.getReservationCost());
    }

    public ReceptionHall(String amenityCode){
        super(amenityCode);
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
