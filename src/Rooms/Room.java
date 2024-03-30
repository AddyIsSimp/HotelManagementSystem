package Rooms;

import java.util.ArrayList;

public class Room {

    protected String roomType;
    private int roomNum;
    protected double ratePerDay;
    private boolean isOccupied;
    private boolean isDisabled;

    protected Room() {
        roomNum = -1;
    }

    protected Room(Room ob) {
        this.roomNum = ob.roomNum;
        this.isOccupied = ob.isOccupied;
    }

    protected Room(int roomNumber) {
        this.roomNum = roomNumber;
    }

    protected Room(int roomNumber, boolean isOccupied) {
        this.roomNum = roomNumber;
        this.isOccupied = isOccupied;
    }



    //SETTER & GETTER
    public String getRoomType() {return roomType;}
    public void setRoomType() {this.roomType = null;}

    public int getRoomNum() {return roomNum;}
    public void setRoomNum(int roomNum) {this.roomNum = roomNum;}

    public boolean getIsOccupied() {return isOccupied;}
    public void setIsOccupied(boolean b) {this.isOccupied = b;}

    public boolean getIsDisabled() {return isDisabled;}
    public void setIsDisabled(boolean b) {this.isDisabled=b;}

    public double getRatePerDay() {return ratePerDay;}
    public void setRatePerDay(double rate) {this.ratePerDay = rate;}

}
