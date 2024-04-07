package Amenity;

public class Amenity {
    protected String amenityType;
    protected String amenityCode;
    protected boolean isReserved = false;
    private double reservationCost;

    public Amenity() {
        this.amenityCode = null;
    }

    public Amenity(String amenityCode){
        this.amenityCode = amenityCode;
    }

    public void setReservationCost(double cost) {this.reservationCost=cost;}
    public double getReservationCost() {return reservationCost;}

    public void setAmenityType(String amenityType) {this.amenityType=amenityType;};
    public String getAmenityType() {return amenityType;}

    public void setAmenityCode(String code) {this.amenityCode = code;}
    public String getAmenityCode() {return amenityCode;}

    public void setReserved(boolean reserve) {this.isReserved = reserve;}
    public boolean getReserved() {return isReserved;}

}
