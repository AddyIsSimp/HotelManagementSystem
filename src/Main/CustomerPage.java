package Main;

import Entity.Customer;
import Rooms.Reservation;
import Rooms.Room;
import Amenity.*;
import Menus.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerPage {

    private Scanner sc = new Scanner(System.in);
    private Methods method = new Methods();
    private String choice = null;

    //Dapat ma-update ang balance if ever naay order c customer

    //=============================CUSTOMERS================================
    //REGISTER CUSTOMER
    Customer register(ArrayList<Customer> customersList) {
        Customer newUser = new Customer();
        boolean main = true;
        boolean isCreate = false;       //indicates if successfully created

        String name = null;
        String pw = null;
        String email;

        while(main) {
            System.out.println("=====REGISTER=====");   //Gather credentials
            System.out.print("Enter Name: ");
            name = sc.nextLine();

            boolean dupl = method.checkDupCustomer(customersList, name);
            if(dupl==true) {
                System.out.println("INVALID: Duplicate name is prohibited!");
                boolean isCont = method.isContinue();
                if(isCont==true) {
                    continue;
                }else {
                    main=false;
                    break;
                }
            }

            System.out.print("Enter Password: ");
            pw = sc.nextLine();
            System.out.print("Enter Email: ");
            email = sc.nextLine();

            newUser = new Customer(name, pw, email);
            isCreate = true;
            System.out.println("Successfully created an account");
            break;
        }//register loop

        if(isCreate==true) return newUser;
        return null;
    }

    //LOGIN CUSTOMER
    boolean loginCustomer(ArrayList<Customer> customersList) {
        String un = null;
        String pw = null;
        boolean isLogin = false;

        while(isLogin==false) {
            System.out.println("=====CUSTOMER=====");
            System.out.print("Username: ");
            un = sc.nextLine();
            System.out.print("Password: ");
            pw = sc.nextLine();

            //Checks the list of Staff if there is same name and password
            for(int i = 0; i<customersList.size(); i++) {
                Customer temp = customersList.get(i);
                if(temp.getName().equals(un) && temp.getPassword().equals(pw)) {
                    Main.customerAcct = temp;
                    isLogin = true;
                    break;
                }
            }

            //No staff found w/ same un & pw
            if(isLogin==false) {
                System.out.println("No account found!");
                boolean isCont = method.isContinue();
                if(isCont==true) continue;
                else break;
            }
        }//End of isLogin whileloop
        return isLogin;
    }

    //========================================SUB-MENU============================================================
    //================================ACCOUNT
    public void goAccount(Customer customer) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("=====ACCOUNTS=====");
            System.out.println("[1] Account details");
            System.out.println("[2] Transaction history");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================SELECT-ROOMS
    public void goSelectRoom(ArrayList<Room> rooms) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("=====SELECT-ROOMS=====");
            System.out.println("[1] Select room number");
            System.out.println("[2] View category");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================BOOK-RESERVATIONS
    public void goBookReservation(ArrayList<Room> rooms,
                                  ArrayList<Amenity> amenities,
                                  ArrayList<Reservation> reservations) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("=====BOOK-RESERVATION=====");
            System.out.println("[1] Rooms");
            System.out.println("[2] Amenities");
            System.out.println("[3] Reservations");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================IN-HOTEL-ORDERS
    public void goHotelOrders(ArrayList<Order> orders) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("=====IN-HOTEL-ORDERS=====");
            System.out.println("[1] Room/Amenity Occupied");
            System.out.println("[2] Food Order");
            System.out.println("[2] Check-out");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

}
