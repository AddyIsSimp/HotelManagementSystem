package Main;

import Entity.Customer;
import Rooms.Date;
import Rooms.Reservation;
import Rooms.Room;
import Amenity.*;
import Menus.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CustomerPage {

    private Scanner sc = new Scanner(System.in);
    private Methods method = new Methods();
    private String choice = null;
    private int reserveDurationLimit = 10;

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
            System.out.println("[1] Select room");
            System.out.println("[2] View category");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    boolean isSelect = true;
                    while(isSelect==true) {
                        method.displayAllRoom(rooms);
                        System.out.println();
                    }
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

        ArrayList<Reservation> customerReservations = Main.customerAcct.getReservations();
        boolean isManage = true;
        while(isManage==true) {

            System.out.println("=====BOOK-RESERVATION=====");
            System.out.println("[1] Rooms");
            System.out.println("[2] Amenities");
            System.out.println("[3] Reservations");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //ROOMS
                    boolean isRoom = true;
                    while(isRoom==true) {
                        System.out.println("=====ROOM-LIST=====");
                        System.out.println("[1] Display room");
                        System.out.println("[2] Display by category");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch (choice) {
                            case 1: //Display all room
                                boolean displayAll = true;
                                while(displayAll==true) {
                                    Date start = null;
                                    Room room = null;
                                    int duration = 0;

                                    method.displayAllRoomForCustomer(rooms);

                                    System.out.println("===SELECT-ROOM===");
                                    room = method.selectRoom(rooms);
                                    start = method.inputDate();     //Set the date
                                    System.out.println("===SET-DURATION===");
                                    System.out.print("Enter duration of reservation(days) : ");
                                    duration = method.inputInt();

                                    Reservation reservation = new Reservation(Main.customerAcct, room, start, duration);
                                    ArrayList<Date> durationDay = reservation.getDuration(start, duration);

                                    boolean isUnique = method.checkReservation(reservations, reservation);
                                    if(isUnique==false) {
                                        boolean isCont = method.stateError("The room " + room.getRoomNum()
                                                + " is already reserved");
                                        if(isCont==true) continue;
                                        else break;
                                    }



                                    reservations.add(reservation);
                                    Main.customerAcct.addReservation(reservation);
                                    break;
                                }
                                break;
                            case 2:
                                method.displayRoomCategory(rooms);
                                break;
                            case 0:
                                isRoom=false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated numbers only");
                        }
                    }
                    break;
                case 2:     //AMENITIES
                    break;
                case 3:     //RESERVATIONS
                    if(customerReservations.size()!=0) {     //If there is reservation in maincustomer
                        for(int i = 0; i<customerReservations.size();i++) {
                            Reservation reservation = customerReservations.get(i);
                            Room room = reservation.getRoom();
                            Date start = reservation.getStartDate();
                            Date end = reservation.getEndDate();
                            System.out.println((i+1) + " " + room.getRoomNum() + " " +
                                    room.getRoomType());
                            System.out.print(" || Start: ");
                            start.displayDate();
                            System.out.print(" || End: ");
                            end.displayDate();
                        }
                    }else {     //Else no reservations
                        System.out.println("No reservations listed");
                    }
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
