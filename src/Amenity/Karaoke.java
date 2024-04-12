package Amenity;

public class Karaoke extends Amenity{
    private String amenityType = "Karaoke";
    private double reservationCost = 1000;

    public Karaoke() {
        super();
    }

    public Karaoke(Amenity kon) {
        this.setAmenityCode(kon.getAmenityCode());
        this.setAmenityType("Karaoke");
        this.setReservationCost(kon.getReservationCost());
    }

    public Karaoke(String amenityCode){
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
