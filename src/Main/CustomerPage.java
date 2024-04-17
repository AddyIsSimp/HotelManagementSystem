package Main;

import Entity.Customer;
import Foods.Food;
import Foods.Menu;
import Rooms.*;
import Amenity.*;
import Transaction.HotelTransact;
import Transaction.ReserveTransact;
import Transaction.Transact;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerPage {

    //TO-DO
    //     There should be a follow up response when the global date is set very advance (Look-out for current bill, reservations, check-out if occupying)
    //

    //MENU TO-DO
    //Accounts-TransactionHistory
    //Accounts-ChangePassword

    protected static Customer customerAcct = null;

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
            name = method.inputString();

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
            pw = method.inputString();
            System.out.print("Enter Email: ");
            email = method.inputString();

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
            un = method.inputString();
            System.out.print("Password: ");
            pw = method.inputString();

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
        HotelTransact inHotelOrder = method.getHotelTransact(customerAcct.getTransact());
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("\n=====ACCOUNTS=====");

            if(inHotelOrder!=null) {        //If do not occupy a room or amenity
                System.out.print("Date: ");
                Main.globalDate.displayDate2();
                System.out.println("\t\tBill: " + inHotelOrder.getBills());
            }else {
                System.out.print("Date: ");
                Main.globalDate.displayDate();
            }

            System.out.println("[1] Account details");
            System.out.println("[2] Transaction history");
            System.out.println("[3] Change password");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                        System.out.println("\n=====ACCOUNT======");
                        System.out.println("Name : " + customerAcct.getName());

                        System.out.print("Number of Visit: ");
                        int visits = 0;
                        ArrayList<Transact> transacts = customerAcct.getTransHistory();
                        for(Transact transact: transacts) {
                            if(transact.getClass()==HotelTransact.class) visits++;
                        }
                        System.out.println(visits);

                        System.out.println("Contact: " + customerAcct.getEmail());
                        method.isGoBack();

                    break;
                case 2:
                    ArrayList<Transact> transactHistory = customerAcct.getTransHistory();
                    if(transactHistory.size()!=0) {
                        System.out.println("\n=====TRANSACTION-HISTORY=====");
                        System.out.println("\tDate\t\tType\t\t\tRoom/Amenity\t\tBills");
                        for(int i = 0; i<transactHistory.size(); i++) {
                            Transact transact = transactHistory.get(i);
                            System.out.print((i+1) + ". ");
                            transact.getDateOfTrans().displayDate2();                           //Date
                            System.out.print("\t\t" + transact.getTransactType());     //Type
                            if(transact.getClass()==HotelTransact.class) {  //IF ITS HOTEL TRANSACT
                                HotelTransact inHotel = (HotelTransact) transact;
                                if(inHotel.getRoom()!=null) {       //If the occupied is room
                                    Room room = inHotel.getRoom();
                                    System.out.print("\t\tRoom: " + room.getRoomType() + room.getRoomNum());
                                    System.out.println("\t\t" + (transact.getBills()+inHotel.getRoomCost()));
                                }
                                else {                             //if the occupied is amenity
                                    Amenity amenity = inHotel.getAmenity();
                                    System.out.print("\t\tAmenity: " + amenity.getAmenityCode());
                                    System.out.println("\t\t" + (transact.getBills()+inHotel.getRoomCost()));
                                }
                            }else {     //IF RESERVATION TRANSACT
                                ReserveTransact reserveTransact = (ReserveTransact) transact;   //Typecast the transact
                                Reservation reservation = reserveTransact.getReservation();     //Get the reservation
                                if(reservation.getRoom()!=null) {       //If the reserve is room
                                    Room room = reservation.getRoom();
                                    System.out.print("\tRoom: " + room.getRoomType() + room.getRoomNum());
                                }else {
                                    Amenity amenity = reservation.getAmenity();
                                    System.out.print("\tAmenity: " + amenity.getAmenityCode());
                                }
                                System.out.println("\t\t" + transact.getBills());
                            }
                        }
                        method.isGoBack();
                    }else {
                        System.out.println("\nThere are no past transactions");
                    }
                    break;
                case 3:
                    while(true) {
                        System.out.print("\nEnter current password: ");
                        String pw = method.inputString();
                        if(customerAcct.getPassword().equals(pw)) {     //Right password
                            System.out.print("Enter new password: ");
                            String newPw = method.inputString();
                            boolean isCont = method.isContinue("Continue change the password?");
                            if(isCont==false) {
                                System.out.println("\nChanging password is unsuccessful!");
                                break;
                            }else {
                                customerAcct.setPassword(newPw);
                                System.out.println("\nPassword is changed successfully!");
                                break;
                            }

                        }else {     //Wrong password
                            boolean isCont = method.stateError("Password is incorrect");
                            if(isCont==true) continue;
                            else break;
                        }
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

            if(choice==0) break;    //Exit clause
            //Restricts if the customer already occupy a room
            HotelTransact hotelTransact = method.getHotelTransact(customerAcct.getTransact());
            if(hotelTransact!=null) {
                if (hotelTransact.getRoom() != null) {      //If hotelTransact has room
                    System.out.println("\nINVALID: You cannot select another room if you already have room");
                    method.isGoBack();
                    continue;
                }
            }

            switch (choice) {
                case 1:     //SELECT ROOM ALL
                    while (isSelect == true) {
                        selectRoom = method.selectRoom(rooms);

                        if (selectRoom == null) {      //If do not found a room with the roomNum
                            boolean isCont = method.stateError("Room is not found with the room number!");
                            if (isCont == true) continue;
                            else break;
                        }

                        while (true) {
                            System.out.println("\n=====SET-DURATION=====");
                            System.out.print("Enter duration(day): ");
                            duration = method.inputInt();

                            if(duration==-1) {  //Invalid input
                                System.out.println("INVALID: Use real number only");
                                continue;
                            }

                            if(duration<1) {        //Should be 1 or more days
                                System.out.println("INVALID: Duration should be 1 to " + Main.durationLimit + " days");
                                continue;
                            }

                            if (duration > Main.durationLimit) {
                                System.out.println("INVALID: The duration should not exceed " + Main.durationLimit);
                                continue;
                            }
                            break;
                        }

                        HotelTransact transact = method.paymentProcess(selectRoom, duration);
                        Main.roomTransacts.add(transact);
                        customerAcct.addTransact(new HotelTransact(transact, duration));
                        selectRoom.setCustomerOccupy(customerAcct);
                        selectRoom.setIsOccupied(true);
                        System.out.println("\nYour room is " + selectRoom.getRoomType() + " room with room number " + selectRoom.getRoomNum());
                        break;
                    }
                    break;
                case 2:
                    while (isSelect == true) {
                        selectRoom = method.displaySelectRoomCategory(rooms);

                        if (selectRoom == null) {      //If do not found a room with the roomNum
                            break;
                        }

                        while (true) {
                            System.out.println("\n=====SET-DURATION=====");
                            System.out.print("Enter duration(day): ");
                            duration = method.inputInt();

                            if(duration==-1) {  //Invalid input
                                System.out.println("INVALID: Use real number only");
                                continue;
                            }

                            if(duration<1) {        //Should be 1 or more days
                                System.out.println("INVALID: Duration should be 1 to " + Main.durationLimit + " days");
                                continue;
                            }

                            if (duration > Main.durationLimit) {
                                System.out.println("INVALID: The duration should not exceed " + Main.durationLimit);
                                continue;
                            }
                            break;
                        }

                        HotelTransact transact = method.paymentProcess(selectRoom, duration);
                        Main.roomTransacts.add(transact);
                        customerAcct.addTransact(new HotelTransact(transact, duration));
                        selectRoom.setCustomerOccupy(customerAcct);     //Set the customerOccupy in room to the customer account
                        selectRoom.setIsOccupied(true);
                        System.out.println("\nYour room is " + selectRoom.getRoomType() + " room with room number " + selectRoom.getRoomNum());
                        break;
                    }
                    break;
                case 0:
                    isManage = false;
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
            System.out.println("[1] Add reservation");
            System.out.println("[2] View reservations");
            System.out.println("[3] Cancel reservation");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //ROOMS
                    boolean isAdd = true;
                    while(isAdd==true) {
                        System.out.println("\n=====ADD-RESERVATION=====");
                        System.out.println("[1] Room");
                        System.out.println("[2] Amenity");
                        System.out.println("[0] Back");
                        System.out.print("Enter choice: ");
                        choice = method.inputInt();

                        switch(choice) {
                            case 1:
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

                                                while(true) {   //Select room loop
                                                    System.out.print("\n===SELECT-ROOM===");
                                                    room = method.selectRoom(rooms);
                                                    if(room==null) {    //if there is no room selected/invalid
                                                        displayAll=false;
                                                    }
                                                    break;
                                                }
                                                if(displayAll==false) break;    //If no room selected
                                                start = method.inputDate();     //Set the date

                                                if(method.compareDate(start, Main.globalDate)==0) {     //Cannot reserve in current date
                                                    System.out.println("\nINVALID: You cannot reserve in current date");
                                                    boolean isCont = method.isContinue();
                                                    if(isCont==true) continue;
                                                    else break;
                                                }else if(method.compareDate(start, Main.globalDate)==-1) {  //Cannot reserve in previous date
                                                    System.out.println("\nINVALID: You cannot reserve in previous date!");
                                                    boolean isCont = method.isContinue();
                                                    if(isCont==true) continue;
                                                    else break;
                                                }

                                                if(start==null) break;          //Exit clause

                                                while(true) {
                                                    System.out.println("\n===SET-DURATION===");
                                                    System.out.print("Enter duration of reservation(days) : ");
                                                    duration = method.inputInt();

                                                    if(duration==-1) {  //Invalid input
                                                        System.out.println("INVALID: Use real number only");
                                                        continue;
                                                    }

                                                    if(duration<1) {
                                                        System.out.println("INVALID: Duration should be 1 to " + Main.durationLimit + " days");
                                                        continue;
                                                    }

                                                    if(duration>Main.durationLimit) {       //Should not exceed the limit
                                                        System.out.println("INVALID: Use indicated number only!");
                                                        continue;
                                                    }else break;
                                                }

                                                Room room1 = null;
                                                if(room.getClass()== SingleRoom.class) room1 = new SingleRoom(room);
                                                else if(room.getClass()== CoupleRoom.class) room1 = new CoupleRoom(room);
                                                else if(room.getClass()== FamilyRoom.class) room1 = new FamilyRoom(room);
                                                else if(room.getClass()== VIPRoom.class) room1 = new VIPRoom(room);

                                                Reservation reservation = new Reservation(customerAcct, room1, start, duration);
                                                ArrayList<Date> durationDay = reservation.getDuration(start, duration);

                                                boolean isUnique = method.checkReservation(reservations, reservation);
                                                if(isUnique==false) {
                                                    boolean isCont = method.stateError("The room " + room1.getRoomNum()
                                                            + " is already reserved");
                                                    if(isCont==true) continue;
                                                    else break;
                                                }

                                                Transact transact = method.paymentProcess(reservation);
                                                reservations.add(reservation);      //This is from customer account add reservation obj
                                                customerAcct.addTransact(transact);     //This if from customer acct add reservationTransact
                                                Main.reservations.add(reservation);     //Add in the main list of reservations
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

                                                room = method.displaySelectRoomCategory(rooms);
                                                if(room==null) {
                                                    System.out.println("You do not select a room");
                                                    boolean isCont = method.isContinue();
                                                    if(isCont ==true) continue;
                                                    else break;
                                                }

                                                start = method.inputDate();     //Set the date
                                                if(start==null) break;          //Exit clause

                                                if(method.compareDate(start, Main.globalDate)==0) {     //Cannot reserve in current date
                                                    System.out.println("\nINVALID: You cannot reserve in current date");
                                                    boolean isCont = method.isContinue();
                                                    if(isCont==true) continue;
                                                    else break;
                                                }else if(method.compareDate(start, Main.globalDate)==-1) {  //Cannot reserve in previous date
                                                    System.out.println("\nINVALID: You cannot reserve in previous date!");
                                                    boolean isCont = method.isContinue();
                                                    if(isCont==true) continue;
                                                    else break;
                                                }

                                                while(true) {
                                                    System.out.println("\n===SET-DURATION===");
                                                    System.out.print("Enter duration of reservation(days) : ");
                                                    duration = method.inputInt();

                                                    if(duration==-1) {  //Invalid input
                                                        System.out.println("INVALID: Use real number only");
                                                        continue;
                                                    }

                                                    if(duration<1) {
                                                        System.out.println("INVALID: Duration should be 1 to " + Main.durationLimit + " days");
                                                        continue;
                                                    }

                                                    if(duration>Main.durationLimit) {       //Should not exceed the limit
                                                        System.out.println("INVALID: Use indicated number only!");
                                                    }else break;
                                                }

                                                Room room1 = null;
                                                if(room.getClass()== SingleRoom.class) room1 = new SingleRoom(room);
                                                else if(room.getClass()== CoupleRoom.class) room1 = new CoupleRoom(room);
                                                else if(room.getClass()== FamilyRoom.class) room1 = new FamilyRoom(room);
                                                else if(room.getClass()== VIPRoom.class) room1 = new VIPRoom(room);

                                                Reservation reservation = new Reservation(customerAcct, room1, start, duration);
                                                ArrayList<Date> durationDay = reservation.getDuration(start, duration);

                                                boolean isUnique = method.checkReservation(reservations, reservation);
                                                if(isUnique==false) {
                                                    boolean isCont = method.stateError("The room " + room.getRoomNum()
                                                            + " is already reserved");
                                                    if(isCont==true) continue;
                                                    else break;
                                                }

                                                Transact transact = method.paymentProcess(reservation);
                                                reservations.add(reservation);      //This is from customer account
                                                customerAcct.addTransact(transact);
                                                Main.reservations.add(reservation);     //Add in the main list of reservations
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
                            case 2:     //ADD RESERVATION AMENITY
                                boolean amenityRsrv = true;
                                while(amenityRsrv==true) {
                                    Amenity amenity = null;
                                    Date start = null;
                                    int duration = 0;

                                    System.out.println("\n===SELECT-AMENITY===");
                                    amenity = method.selectAmenity(amenities);
                                    if(amenity==null) break;
                                    start = method.inputDate();     //Set the date
                                    if(start==null) break;          //Exit clause

                                    if(method.compareDate(start, Main.globalDate)==0) {     //Cannot reserve in current date
                                        System.out.println("\nINVALID: You cannot reserve in current date");
                                        boolean isCont = method.isContinue();
                                        if(isCont==true) continue;
                                        else break;
                                    }else if(method.compareDate(start, Main.globalDate)==-1) {  //Cannot reserve in previous date
                                        System.out.println("\nINVALID: You cannot reserve in previous date!");
                                        boolean isCont = method.isContinue();
                                        if(isCont==true) continue;
                                        else break;
                                    }

                                    while(true) {
                                        System.out.println("\n===SET-DURATION===");
                                        System.out.print("Enter duration of reservation(days) : ");
                                        duration = method.inputInt();

                                        if (duration == -1) {  //Invalid input
                                            System.out.println("INVALID: Use real number only");
                                            continue;
                                        }

                                        if(duration<1) {        //Should be 1 or more days
                                            System.out.println("INVALID: Duration should be 1 to " + Main.durationLimit + " days");
                                            continue;
                                        }

                                        if(duration>Main.durationLimit) {       //Should not exceed the limit
                                            System.out.println("INVALID: Use indicated number only!");
                                        }else break;
                                    }

                                    //Create another amenity object for saving current date
                                    Amenity amenity1 = null;
                                    if(amenity.getClass()== Karaoke.class) amenity1 = new Karaoke(amenity);
                                    else if(amenity.getClass()== GameRoom.class) amenity1 = new GameRoom(amenity);
                                    else if(amenity.getClass()== Pool.class) amenity1 = new Pool(amenity);
                                    else if(amenity.getClass()== ReceptionHall.class) amenity1 = new ReceptionHall(amenity);

                                    Reservation reservation = new Reservation(customerAcct, amenity1, start, duration);

                                    boolean isUnique = method.checkReservation(Main.reservations, reservation);
                                    if(isUnique==false) {
                                        boolean isCont = method.stateError("The amenity " + amenity1.getAmenityCode()
                                                + " is already reserved");
                                        if(isCont==true) continue;
                                        else break;
                                    }

                                    Transact transact = method.paymentProcess(reservation);
                                    reservations.add(reservation);      //This is from customer acct
                                    customerAcct.addTransact(transact);
                                    Main.reservations.add(reservation);     //Add in the main list of reservations
                                    System.out.println("You have successfully reserved amenity " + amenity.getAmenityType()
                                            + " #" + amenity.getAmenityCode());

                                    break;

                                }
                                break;
                            case 0:
                                isAdd = false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only!");
                        }
                        break;
                    }
                    break;

                case 2:     //VIEW RESERVATION
                    ArrayList<ReserveTransact> reserveTransacts1 = method.getReserveTransact(customerAcct.getTransact());
                    if(reserveTransacts1.size()!=0) {     //If there is reservation in main customer
                        method.displayReservations2(reserveTransacts1);
                        System.out.println("");
                        method.isGoBack();
                    }else {     //Else no reservations1
                        System.out.println("\nNo reservations listed");
                    }
                    break;

                case 3:     //CANCEL RESERVATIONS
                    boolean isCancel = true;
                    int reserveIndexToCancel = -1;

                    while(isCancel==true) {
                        Reservation reservationDel = null;     //Reservation to cancel

                        System.out.println("\n=====CANCEL-RESERVATION=====");
                        ArrayList<ReserveTransact> reserveTransacts = method.getReservationTransact(customerAcct.getTransact());
                        if(reserveTransacts.size()==0) {
                            System.out.println("There is no reservation in this account");
                            method.isGoBack();
                            break;
                        }

                        for(int i = 0; i<reserveTransacts.size(); i++) {        //Display reservations
                            ReserveTransact reserve = reserveTransacts.get(i);      //Get the transaction
                            Reservation reservation = reserve.getReservation();     //Get the reservation info
                            if(reservation.getRoom()!=null) {
                                Room room = reservation.getRoom();
                                Date start = reservation.getStartDate();
                                System.out.print((i + 1) + " Room: " + room.getRoomType() + " #" +
                                        room.getRoomNum());
                                System.out.print(" || Start: ");
                                start.displayDate2();
                                System.out.println(" || Duration: " + reservation.getDuration() + "day");
                            }else if(reservation.getAmenity()!=null) {
                                Amenity amenity = reservation.getAmenity();
                                Date start = reservation.getStartDate();

                                System.out.print((i + 1) + " Amenity: " + amenity.getAmenityType() + " " +
                                        amenity.getAmenityCode());
                                System.out.print(" || Start: ");
                                start.displayDate2();
                                System.out.println(" || Duration: " + reservation.getDuration() + "day");
                            }
                        }

                        System.out.print("Select reservation number to delete: ");
                        reserveIndexToCancel = method.inputInt();

                        if(reserveIndexToCancel>reserveTransacts.size() || reserveIndexToCancel<=0) {
                            System.out.println("INVALID: Reservation with the reservation number is not found!");
                            break;
                        }

                        //Ask if still continue cancel the reservations
                        boolean isCont = method.isContinue("\nDo you want to continue cancel the reservation! Only half will be repayed!");
                        if(isCont==false) {     //If not continue
                            System.out.println("Cancelling of reservation is unsuccessful!");
                            break;
                        }

                        ReserveTransact reserveTransact = reserveTransacts.get(reserveIndexToCancel-1);
                        Reservation reservation = reserveTransact.getReservation();
                        customerAcct.addRefund(reserveTransact.getBills()/2);
                        reserveTransact.setBills(reserveTransact.getBills()/2);                         //The sales is only halp of the paid bills
                        reserveTransact.setRsrvEnded(true);                                             //For it can be accepted by the staff
                        customerAcct.getTransact().remove(reserveTransact);                                       //Remove the reserveTransact in Customer account transaction
                        Main.reserveTransacts.remove(reserveTransact);                                  //Remove the reserveTransact in reserveTransacts
                        Main.reservations.remove(reservation);                                          //Remove the reservation in Main
                        System.out.println("\nReservation is cancelled successfully!");

                        while(true) {   //Refund accept loop
                            System.out.print("Press 1 to accept refund: " + customerAcct.getRefunds() + "\n-");
                            choice = method.inputInt();
                            if(choice==1) {
                                System.out.println("You received a " + customerAcct.getRefunds() + " for the reservation refund!");
                                customerAcct.sendRefunds();
                                break;
                            }
                            System.out.println("INVALID: Press number 1 only to accept the refund!");
                        }
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

    //================================IN-HOTEL-ORDERS
    public void goHotelOrders(ArrayList<Food> foodInventory) {
        boolean isManage = true;
        ArrayList<Transact> transacts = customerAcct.getTransact();

        while(isManage==true) {
            HotelTransact inHotelOrder = null;
            for(int i = 0; i< transacts.size(); i++) {
                Transact transact = transacts.get(i);
                if(transact.getClass()==HotelTransact.class) {
                    inHotelOrder = (HotelTransact) transact;
                    break;
                }
            }

            System.out.println("\n=====IN-HOTEL-ORDERS=====");
            if(inHotelOrder==null) {        //Not occupy room or amenity
                System.out.print("Date: ");
                Main.globalDate.displayDate3();
                System.out.println("\nHotel orders is not available when not occupying a room/amenity");
                method.isGoBack();
                break;
            }
            System.out.print("Date: ");
            Main.globalDate.displayDate2();
            System.out.println("\t\tBill: " + inHotelOrder.getBills());
            System.out.println("[1] Room/Amenity Occupied");
            System.out.println("[2] Food Order");
            System.out.println("[3] Check-out");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //ROOM/AMENITY OCCUPIED
                    System.out.println("\n=====OCCUPIED=====");
                    if(inHotelOrder.getRoom()!=null) {
                        Room room = inHotelOrder.getRoom();
                        System.out.println("Room: " + room.getRoomType() + " #" + room.getRoomNum());
                    }else if(inHotelOrder.getAmenity()!=null) {
                        Amenity amenity = inHotelOrder.getAmenity();
                        System.out.println("Amenity: " + amenity.getAmenityType() + " #" + amenity.getAmenityCode());
                    }

                    System.out.print("End date: ");
                    inHotelOrder.getEndDate().displayDate();
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
                                ArrayList<Menu> menuOrdered = inHotelOrder.getMenuOrdered();
                                if(menuOrdered.size()==0) {
                                    System.out.println("\nYou do not have ordered a food!");
                                    break;
                                } else {
                                    System.out.println("\n=====ORDERS=====");
                                    for(int i = 0; i<menuOrdered.size(); i++) {
                                        Menu menu = menuOrdered.get(i);
                                        System.out.print((i + 1) + " Name: " + menu.getMenuName()
                                                + " || Food: " + menu.getMainDish().getFoodName());
                                        if (menu.getSideDish() != null) System.out.print(" , " + menu.getSideDish().getFoodName());
                                        if (menu.getDrinks() != null) System.out.print(" , " + menu.getDrinks().getFoodName());
                                        if (menu.getDessert() != null) System.out.print(" , " + menu.getDessert().getFoodName());
                                        System.out.println(" || Price: " + menu.getTotalPrice());
                                    }
                                }
                                break;
                            case 2:
                                while(true) {
                                    Menu menu = method.selectMenu(Main.menus);
                                    Menu menuSelect = new Menu(menu);

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

                                    inHotelOrder.addMenuOrder(menuSelect);
                                    System.out.println("\nYou have successfully created an order for menu " + menuSelect.getMenuName());
                                    System.out.println("Your order will be delivered after a couple of minutes!");
                                    break;
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
                    boolean isOut = true;

                    if(inHotelOrder==null) {    //Restricts to check out if not occupying a room/amenity
                        System.out.println("\nCheck out is not possible! \nYou currently do not occupy a room/amenity.");
                        continue;
                    }

                    while(isOut==true) {    //Check out loop
                        if(inHotelOrder.getBills()==0) {        //There are no bills
                            System.out.println("\n=====CHECK-OUT=====");
                            ArrayList<Transact> transactions = customerAcct.getTransact();

                            //Set the room or amenity unoccupied
                            if(inHotelOrder.getRoom()!=null) {
                                Room room = inHotelOrder.getRoom();
                                room.setIsOccupied(false);
                            }else if(inHotelOrder.getAmenity()!=null) {
                                Amenity amenity = inHotelOrder.getAmenity();
                                amenity.setIsReserved(false);
                            }

                            customerAcct.addTransHistory(inHotelOrder);                     //save the transact in transact history
                            transactions.remove(inHotelOrder);                              //remove the transact in customer account
                            Main.roomTransacts.remove(inHotelOrder);                        //remove the transact in Admin records
                            System.out.println("You have check-out in the hotel successfully!");
                            break;
                        }else if(inHotelOrder.getBills()!=0) {      //If there are bills
                            System.out.print("\n=====CHECK-OUT=====");
                            inHotelOrder.setDateOfTrans(new Date(inHotelOrder.getDateOfTrans()));
                            boolean isPaid = method.paymentProcess(inHotelOrder);

                            if(isPaid==false) { //There is a wrong in paymentProcess
                                System.out.println("INVALID: Payment is unsuccessful");
                            }else {
                                ArrayList<Transact> transactions = customerAcct.getTransact();

                                //Set the room or amenity unoccupied
                                if(inHotelOrder.getRoom()!=null) {
                                    Room room = inHotelOrder.getRoom();
                                    room.setIsOccupied(false);
                                }else if(inHotelOrder.getAmenity()!=null) {
                                    Amenity amenity = inHotelOrder.getAmenity();
                                    amenity.setIsReserved(false);
                                }

                                customerAcct.addTransHistory(inHotelOrder);                     //save the transact in transact history
                                transacts.remove(inHotelOrder);
                                Main.roomTransacts.remove(inHotelOrder);                        //remove the transact in Admin records
                                System.out.println("You have check-out in the hotel successfully!");
                            }
                        }
                        break;
                    }//end of checkout loop
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    public void checkAccountForDate() {
        //Keep in mind remove the reservation obj in the customer account and in admin reservations
        ArrayList<Transact> customerTransacts = customerAcct.getTransact();
        HotelTransact inHotel = method.getHotelTransact(customerTransacts);
        ArrayList<ReserveTransact> reserveTransact = method.getReservationTransact(customerTransacts);
        Date globalDate = Main.globalDate;      //current date

        //CHECK HOTEL TRANSACTION OF CUSTOMER
        if(inHotel!=null && method.compareDate(inHotel.getEndDate(), globalDate)==-1) {     //The hotelOrder is overdue
            if(inHotel.getBills()!=0) {                                                     //There is bill balance in customers
                while(true) {
                    System.out.println("You have a balance in the past occupation in the hotel");
                    boolean isPaid = method.paymentProcess(inHotel);

                    if (isPaid == false) { //There is a wrong in paymentProcess
                        System.out.println("INVALID: Payment is unsuccessful");
                    } else {
                        ArrayList<Transact> transactions = customerAcct.getTransact();
                        customerAcct.addTransHistory(inHotel);                     //save the transact in transact history
                        Main.pastTransacts.add(inHotel);                            //Add transact in pastTransaction
                        customerTransacts.remove(inHotel);
                        Main.roomTransacts.remove(inHotel);                        //remove the transact in Admin records
                        System.out.println("You have successfully paid your bills!");
                        break;
                    }
                }
            }else {                                                                 //There is no balance
                customerTransacts.remove(inHotel);
                Main.roomTransacts.remove(inHotel);
                System.out.println(customerAcct.getName() + " your occupation has ended!");
                method.isGoContinue();
            }
        }//End of checkHotelTransaction of Customer

        //THERE IS A RESERVATION TRANSACTION OF CUSTOMER
        if(reserveTransact.size()!=0) {
            boolean noConflict = true;

            //check if there is a reservation within this current date
            for(int i = 0; i<reserveTransact.size(); i++) {//Get the reserve transact
                ReserveTransact reserve = reserveTransact.get(i);
                Reservation reservation = reserve.getReservation();        //Retrieve the reservation in transact

                //IF GLOBALDATE IS WITHIN DATE DURATION OF RESERVATION
                if(((method.compareDate(reservation.getStartDate(), globalDate)==-1) && method.compareDate(reservation.getEndDate(), globalDate)==1)
                        || method.compareDate(reservation.getStartDate(), globalDate)==0
                        || method.compareDate(reservation.getEndDate(), globalDate)==0) {    //The reservation have started
                    if(reservation.getRoom()!=null) {
                        Room room = reservation.getRoom();
                        Date newDate = new Date(reserve.getDateOfTrans());
                        HotelTransact inHotelTransact = new HotelTransact(newDate,
                                customerAcct,
                                room,
                                reservation.getStartDate(),
                                0,
                                reservation.getDuration(),
                                room.getReservationPrice());
                        reserve.setRsrvEnded(true);                     //The reservation have ended

                        customerAcct.addTransact(inHotelTransact);      //Add transact in customer
                        Main.roomTransacts.add(inHotelTransact);        //Add transact in MainRoomTransact
                        customerAcct.addTransHistory(reserve);          //Add || in the customer's transact history
                        customerTransacts.remove(reserve);              //Remove the reserve in customer Account
                        Main.reserveTransacts.remove(reserve);          //Remove the transact in main;
                        Main.reservations.remove(reservation);          //Remove the reservation in reservations
                    }
                    if(reservation.getAmenity()!=null) {
                        Amenity amenity = reservation.getAmenity();
                        Date newDate = new Date(reserve.getDateOfTrans());
                        HotelTransact inHotelTransact = new HotelTransact(newDate,
                                customerAcct,
                                amenity,
                                reservation.getStartDate(),
                                0,
                                reservation.getDuration(),
                                amenity.getReservationCost());
                        reserve.setRsrvEnded(true);                     //The reservation have ended

                        customerAcct.addTransact(inHotelTransact);      //Add transact in customer
                        amenity.setIsReserved(true);
                        Main.roomTransacts.add(inHotelTransact);        //Add transact in MainRoomTransact
                        customerAcct.addTransHistory(reserve);          //Add || in the customer's transact history
                        Main.reserveTransacts.remove(reserve);  //Remove the transact in main;
                        Main.reservations.remove(reservation);          //Remove the reservation in reservations
                    }
                    System.out.println("Your reservation have started!");

                //IF DURATION OF RESERVATION IS OVERDUE BY GLOBALDATE
                }else if(method.compareDate(reservation.getEndDate(), globalDate)==-1) {
                    customerAcct.addTransHistory(reserve);          //Add || in the customer's transact history
                    reserve.setRsrvEnded(true);                     //The reservation have ended
                    Main.pastTransacts.add(reserve);                //Add transact in pastTransaction
                    Main.reserveTransacts.remove(reserve);  //Remove the transact in main;
                    Main.reservations.remove(reservation);          //Remove the reservation in reservations
                    System.out.println("Your reservation have ended!");
                }
            }//End of Reservation forloop
        }//End of check HotelReservationTransact

    }//End of checkAccountDate

}
