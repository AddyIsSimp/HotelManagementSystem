package Foods;

import Rooms.Room;

public class Orders {
    private Menu menuOrder;
    private Room room;
    private double bills;

    Orders(Menu menuOrder, Room room, double bills) {
        this.menuOrder = menuOrder;
        this.room = room;
        this.bills = bills;
    }
}
