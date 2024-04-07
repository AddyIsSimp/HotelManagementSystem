package Amenity;

public class Pool extends Amenity {
    private String amenityType = "Pool";
    private double reservationCost = 5000;

    public Pool() {
        super();
    }

    public Pool(Pool pool) {
        this.setAmenityCode(pool.getAmenityCode());
    }

    public Pool(String amenityCode){
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
