package Transaction;


import Entity.Customer;
import Foods.Menu;
import Transaction.Transact;

import java.util.ArrayList;

public class Order {
    Customer customer;
    ArrayList<Transact> transacts;
    ArrayList<Menu> menus;
}
