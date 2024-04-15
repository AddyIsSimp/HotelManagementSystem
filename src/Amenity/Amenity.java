package Amenity;

public class Amenity {
    protected String amenityType;
    protected String amenityCode;
    protected boolean isReserved = false;
    private double reservationCost;

    public Amenity() {
        this.amenityCode = null;
    }

    public Amenity(Amenity amenity) {
        this.amenityCode = amenity.getAmenityCode();
        this.amenityType = amenity.getAmenityType();
        this.reservationCost = amenity.getReservationCost();
        this.isReserved = amenity.getIsReserved();
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

    public void setIsReserved(boolean reserve) {this.isReserved = reserve;}
    public boolean getIsReserved() {return isReserved;}

}
