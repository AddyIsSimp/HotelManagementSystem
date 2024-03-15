package Rooms;
import java.util.ArrayList;

public class SingleRoom extends Room {
    protected String bedType = "Single";

    SingleRoom() {
        super();
        addDefaultAppliance();
    }

    SingleRoom(Room ob) {
        super(ob);
        addDefaultAppliance();
    }

    SingleRoom(int roomNumber) {
        super(roomNumber);
        addDefaultAppliance();
    }

    void addDefaultAppliance() {
        this.appliances.add("Aircon");
        this.appliances.add("TV");
        this.appliances.add("Mini-refrigerator");
    }

}

