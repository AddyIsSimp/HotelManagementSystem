package Amenity;

public class Amenity {
    private String amenityCode;
    private int maxPerson;

    public Amenity() {
        this.amenityCode = null;
        this.maxPerson = -1;
    }

    public Amenity(String amenityCode, int maxPerson){
        this.amenityCode = amenityCode;
        this.maxPerson = maxPerson;
    }

    public void setAmenityCode(String code) {this.amenityCode = code;}
    public String getAmenityCode() {return amenityCode;}

    public void setMaxPerson(int qty) {this.maxPerson = qty;}
    public int getMaxPerson() {return maxPerson;}

}
