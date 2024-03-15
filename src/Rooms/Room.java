package Rooms;

import java.util.ArrayList;

public class Room {

    protected int roomNum;
    protected boolean isOccupied;
    private boolean isDisabled;
    protected ArrayList<String> appliances = new ArrayList<String>();

    Room() {
        roomNum = -1;
    }

    Room(Room ob) {
        this.roomNum = ob.roomNum;
        this.isOccupied = ob.isOccupied;
    }

    Room(int roomNumber) {
        this.roomNum = roomNumber;
    }

    Room(int roomNumber, boolean isOccupied) {
        this.roomNum = roomNumber;
        this.isOccupied = isOccupied;
    }

    void addDefaultAppliance() {    //Method to define the appliance within the type of room
    }

    boolean getIsDisabled() {return isDisabled;}
    void setIsDisabled(boolean b) {this.isDisabled=b;}

}
