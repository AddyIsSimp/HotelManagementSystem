package Main;

import Entity.Customer;
import Foods.Food;
import Foods.Menu;
import Rooms.Date;
import Rooms.Reservation;
import Rooms.Room;
import Amenity.*;
import Transaction.HotelTransact;
import Transaction.ReserveTransact;
import Transaction.Transact;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerPage {

    public static Customer customerAcct = null;

    private Scanner sc = new Scanner(System.in);
    private Methods method = new Methods();
    private String choice = null;
    private int reserveDurationLimit = 10;

    //Dapat ma-update ang balance if ever naay order c customer

    //=============================CUSTOMERS================================
    //REGISTER CUSTOMER
    public Customer register(ArrayList<Customer> customersList) {
        Customer newUser = new Customer();
        boolean main = true;
        boolean isCreate = false;       //indicates if successfully created

        String name = null;
        String pw = null;
        String email;

        while(main) {
            System.out.println("\n=====REGISTER=====");   //Gather credentials
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
            System.out.println("\n=====LOGIN=====");
            System.out.print("Username: ");
            un = sc.nextLine();
            System.out.print("Password: ");
            pw = sc.nextLine();

            //Checks the list of Staff if there is same name and password
            for(int i = 0; i<customersList.size(); i++) {
                Customer temp = customersList.get(i);
                if(temp.getName().equals(un) && temp.getPassword().equals(pw)) {
                    customerAcct = temp;
                    isLogin = true;
                    System.out.println("\nLogin successfully");
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
            System.out.println("\n=====ACCOUNTS=====");
            System.out.println("[1] Account details");
            System.out.println("[2] Transaction history");
            System.out.println("[3] Change password");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                        System.out.println("\n=====ACCOUNT======");
                        System.out.println("Name : " + customerAcct.getName());
                        System.out.println("Number of Visit: ");
                        System.out.println("Contact: " + customerAcct.getEmail());
                        method.isGoBack();

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
            boolean isSelect = true;
            Room selectRoom = null;
            int duration = 0;

            System.out.println("\n=====SELECT-ROOMS=====");
            System.out.println("[1] Select room");
            System.out.println("[2] View category");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    while(isSelect==true) {
                        method.displayAllRoom(rooms);
                        selectRoom = method.selectRoom(rooms);

                        if(selectRoom==null) {      //If do not found a room with the roomNum
                            boolean isCont = method.stateError("Room is not found with the room number!");
                            if(isCont==true) continue;
                            else break;
                        }

                        while(true) {
                            System.out.println("\n=====SET-DURATION=====");
                            System.out.print("Enter duration(day): ");
                            duration = method.inputInt();

                            if (duration > Main.durationLimit) {
                                System.out.println("INVALID: The duration should not exceed " + Main.durationLimit);
                                continue;
                            }
                            break;
                        }

                        HotelTransact transact = method.paymentProcess(selectRoom, duration);
                        Main.roomTransacts.add(transact);
                        customerAcct.addTransact(transact);
                        selectRoom.setCustomerOccupy(customerAcct);
                        System.out.print("\nYour room is " + selectRoom.getRoomType() + " room with room number " + selectRoom.getRoomNum());

                        break;
                    }
                    break;
                case 2:
                    while(isSelect==true) {
                        selectRoom = method.displaySelectRoomCategory(rooms);

                        if(selectRoom==null) {      //If do not found a room with the roomNum
                            boolean isCont = method.stateError("Room is not found with the room number!");
                            if(isCont==true) continue;
                            else break;
                        }

                        while(true) {
                            System.out.println("\n=====SET-DURATION=====");
                            System.out.print("Enter duration(day): ");
                            duration = method.inputInt();

                            if (duration > Main.durationLimit) {
                                System.out.println("INVALID: The duration should not exceed " + Main.durationLimit);
                                continue;
                            }
                            break;
                        }

                        HotelTransact transact = method.paymentProcess(selectRoom, duration);
                        Main.roomTransacts.add(transact);
                        customerAcct.addTransact(transact);
                        selectRoom.setCustomerOccupy(customerAcct);     //Set the customerOccupy in room to the customer account
                        System.out.print("\nYour room is " + selectRoom.getRoomType() + " room with room number " + selectRoom.getRoomNum());


                        break;
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

    //================================BOOK-RESERVATIONS
    public void goBookReservation(ArrayList<Room> rooms,
                                  ArrayList<Amenity> amenities) {

        ArrayList<ReserveTransact> customerReservations = method.getReservationTransact(customerAcct.getTransact());
        ArrayList<Reservation> reservations = customerAcct.getReservations();
        boolean isManage = true;
        while(isManage==true) {

            System.out.println("\n=====BOOK-RESERVATION=====");
            System.out.println("[1] Rooms");
            System.out.println("[2] Amenities");
            System.out.println("[3] Reservations");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //ROOMS
                    boolean isRoom = true;
                    while(isRoom==true) {
                        System.out.println("\n=====ROOM-LIST=====");
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

                                    while(true) {   //Select room loop
                                        System.out.println("\n===SELECT-ROOM===");
                                        room = method.selectRoom(rooms);
                                        if(room==null) {    //if there is no room selected/invalid
                                            boolean isCont = method.isContinue();
                                            if(isCont==false) { //if not continue
                                                displayAll=false;
                                            }
                                        }
                                        break;
                                    }
                                    if(displayAll==false) break;    //If no room selected
                                    start = method.inputDate();     //Set the date

                                    while(true) {
                                        System.out.println("\n===SET-DURATION===");
                                        System.out.print("Enter duration of reservation(days) : ");
                                        duration = method.inputInt();
                                        if(duration>Main.durationLimit) {       //Should not exceed the limit
                                            System.out.println("INVALID: Use indicated number only!");
                                            continue;
                                        }else break;
                                    }

                                    Reservation reservation = new Reservation(customerAcct, room, start, duration);
                                    ArrayList<Date> durationDay = reservation.getDuration(start, duration);

                                    boolean isUnique = method.checkReservation(reservations, reservation);
                                    if(isUnique==false) {
                                        boolean isCont = method.stateError("The room " + room.getRoomNum()
                                                + " is already reserved");
                                        if(isCont==true) continue;
                                        else break;
                                    }

                                    Transact transact = method.paymentProcess(reservation);
                                    reservations.add(reservation);
                                    customerAcct.addTransact(transact);
                                    System.out.println("You have successfully reserved room " + room.getRoomType() + " #" + room.getRoomNum());

                                    break;
                                }
                                break;
                            case 2:
                                boolean displayCategory = true;
                                while(displayCategory==true) {
                                    Date start = null;
                                    Room room = null;
                                    int duration = 0;

                                    method.displayRoomCategory(rooms);

                                    System.out.println("\n===SELECT-ROOM===");
                                    room = method.selectRoom(rooms);
                                    start = method.inputDate();     //Set the date

                                    while(true) {
                                        System.out.println("\n===SET-DURATION===");
                                        System.out.print("Enter duration of reservation(days) : ");
                                        duration = method.inputInt();
                                        if(duration>Main.durationLimit) {       //Should not exceed the limit
                                            System.out.println("INVALID: Use indicated number only!");
                                        }else break;
                                    }

                                    Reservation reservation = new Reservation(customerAcct, room, start, duration);
                                    ArrayList<Date> durationDay = reservation.getDuration(start, duration);

                                    boolean isUnique = method.checkReservation(reservations, reservation);
                                    if(isUnique==false) {
                                        boolean isCont = method.stateError("The room " + room.getRoomNum()
                                                + " is already reserved");
                                        if(isCont==true) continue;
                                        else break;
                                    }

                                    Transact transact = method.paymentProcess(reservation);
                                    reservations.add(reservation);
                                    customerAcct.addTransact(transact);
                                    System.out.println("You have successfully reserved room " + room.getRoomType() + " #" + room.getRoomNum());
                                    break;

                                }
                                break;
                            case 0:
                                isRoom=false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated numbers only");
                        }
                    }
                    break;
                case 2:     //AMENITIES-RESERVATIONS
                    boolean amenityRsrv = true;
                    while(amenityRsrv==true) {
                        Amenity amenity = null;
                        Date start = null;
                        int duration = 0;

                        method.displayAmenities(amenities);

                        System.out.println("\n===SELECT-AMENITY===");
                        amenity = method.selectAmenity(amenities);
                        if(amenity==null) break;    //Exit
                        start = method.inputDate();     //Set the date
                        System.out.println("\n===SET-DURATION===");
                        System.out.print("Enter duration of reservation(days) : ");
                        duration = method.inputInt();

                        Reservation reservation = new Reservation(customerAcct, amenity, start, duration);
                        ArrayList<Date> durationDay = reservation.getDuration(start, duration);

                        boolean isUnique = method.checkReservation(reservations, reservation);
                        if(isUnique==false) {
                            boolean isCont = method.stateError("The amenity " + amenity.getAmenityCode()
                                    + " is already reserved");
                            if(isCont==true) continue;
                            else break;
                        }



                        Transact transact = method.paymentProcess(reservation);
                        reservations.add(reservation);
                        customerAcct.addTransact(transact);
                        break;

                    }
                    break;
                case 3:     //RESERVATIONS
                    if(customerReservations.size()!=0) {     //If there is reservation in maincustomer
                        boolean displayRsrv = true;
                        while(displayRsrv==true) {
                            ArrayList<Reservation> roomList = new ArrayList<>();
                            ArrayList<Reservation> amenityList = new ArrayList<>();

                            if(reservations.size()==0) {    //No reservations found
                                System.out.println("\nThere is no reservations");
                                break;
                            }

                            for(int i = 0; i< reservations.size(); i++) {    //Filter the reservation either room or amenity
                                Reservation reservation = reservations.get(i);
                                if(reservation.getRoom()!=null) {
                                    roomList.add(reservation);
                                }else if(reservation.getAmenity()!=null) {
                                    amenityList.add(reservation);
                                }
                            }

                            if(roomList.size()!=0) {    //There is a room reservation
                                System.out.println("=====ROOM-RESERVED=====");
                            }else {
                                System.out.println("There is no room reserved");
                            }

                            for (int i = 0; i < customerReservations.size(); i++) {
                                Reservation reservationTransact = reservations.get(i);
                                Room room = reservationTransact.getRoom();
                                Date start = reservationTransact.getStartDate();

                                System.out.print((i + 1) + " " + room.getRoomNum() + " " +
                                        room.getRoomType());
                                System.out.print(" || Start: ");
                                start.displayDate();
                                System.out.print(" || Duration: ");
                            }
                        }//End of displayRsrv loop
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
    public void goHotelOrders(ArrayList<Food> foodInventory) {
        boolean isManage = true;
        HotelTransact inHotelOrder = null;
        ArrayList<Transact> transacts = customerAcct.getTransact();
        HotelTransact hotelTransact = method.getHotelTransact(customerAcct.getTransact());

        for(int i = 0; i< transacts.size(); i++) {
            Transact transact = transacts.get(i);
            if(transact.getClass()== HotelTransact.class) {
                inHotelOrder = (HotelTransact) transact;
                break;
            }
        }

        while(isManage==true) {
            System.out.println("\n=====IN-HOTEL-ORDERS=====");
            if(inHotelOrder==null) {        //Not occupy room or amenity
                System.out.println("Hotel orders is not available when not occupying a room/amenity");
                method.isGoBack();
                break;
            }
            System.out.println("[1] Room/Amenity Occupied");
            System.out.println("[2] Food Order");
            System.out.println("[3] Check-out");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //ROOM/AMENITY OCCUPIED
                    System.out.println("=====OCCUPIED=====");
                    if(inHotelOrder.getRoom()!=null) {
                        Room room = inHotelOrder.getRoom();
                        System.out.println("Room Number: " + room.getRoomType() + " #" + room.getRoomNum());
                    }else if(inHotelOrder.getAmenity()!=null) {
                        Amenity amenity = inHotelOrder.getAmenity();
                        System.out.println("Amenity: " + amenity.getAmenityType() + " #" + amenity.getAmenityCode());
                    }
                    method.isGoBack();
                    break;
                case 2:     //FOOD ORDER
                    boolean isOrder = true;
                    while(isOrder==true) {
                        System.out.println("\n=====FOOD-ORDER=====");
                        System.out.println("[1] Show food order");
                        System.out.println("[2] Create an order");
                        System.out.println("[0] Back");
                        System.out.print("Enter choice: ");
                        choice = method.inputInt();

                        switch(choice) {
                            case 1:
                                ArrayList<Menu> menuOrdered = hotelTransact.getMenuOrdered();
                                if(menuOrdered.size()==0) {
                                    System.out.println("\nFood order is not available when not occupying a room/amenity");
                                    break;
                                } else {
                                    System.out.println("=====ORDERS=====");
                                    for(int i = 0; i<menuOrdered.size(); i++) {
                                        Menu menu = menuOrdered.get(i);
                                        System.out.print((i + 1) + " Name: " + menu.getMenuName()
                                                + " || Food: " + menu.getMainDish().getFoodName());
                                        if (menu.getSideDish() != null) System.out.print(" , " + menu.getSideDish().getFoodName());
                                        if (menu.getDrinks() != null) System.out.print(" , " + menu.getDrinks().getFoodName());
                                        if (menu.getDessert() != null) System.out.print(" , " + menu.getDessert().getFoodName());
                                        System.out.print(" || Price: " + menu.getTotalPrice());
                                    }
                                }
                                break;
                            case 2:
                                while(true) {
                                    Menu menuSelect = method.selectMenu(Main.menus);

                                    if (menuSelect == null) {
                                        break;
                                    }

                                    double bill = menuSelect.getTotalPrice();   //Get the bill
                                    Food foodConflict = method.createMenuOrder(foodInventory, menuSelect);
                                    if(foodConflict!=null) {
                                        System.out.println("INVALID: The food '"+ foodConflict + "' stock is depleted");
                                        boolean isCont = method.isContinue();
                                        if(isCont==true){
                                            break;
                                        }
                                    }

                                    hotelTransact.addMenuOrder(menuSelect);
                                    System.out.println("");
                                    //Get order transact if there is

                                }
                                break;
                            case 0:
                                isOrder = false;
                                break;
                            default:
                        }
                    }
                    break;
                case 3:     //CHECK-OUT
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
