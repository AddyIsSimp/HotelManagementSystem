package Transaction;


import Entity.Customer;
import Foods.Menu;
import Transaction.Transact;

import java.util.ArrayList;

public class Order {
    private Customer customer;
    private ArrayList<Transact> transacts;      //Save here the transact including the room occupied and reservation
    private ArrayList<Menu> menus;
    private boolean hasReservation = false;


    public Order(Customer customer, boolean hasReserve) {

    }

}
