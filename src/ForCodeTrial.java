import Amenity.Amenity;
import Entity.Customer;
import Entity.Staff;
import Foods.Food;
import Main.Main;
import Main.Methods;
import Foods.Menu;
import Rooms.*;
import Rooms.Date;
import Rooms.Reservation;
import Transaction.HotelTransact;
import Transaction.ReserveTransact;
import Transaction.Transact;

import java.util.*;

public class ForCodeTrial {

    public static Customer customerAccount = new Customer("Adrian", "Santos", "Successor");

    public static Methods method = new Methods();

    public static ArrayList<ArrayList> all = new ArrayList<>();
    public static ArrayList<Customer> customersList = new ArrayList<>();
    public static ArrayList<Staff> staffsList = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Amenity> amenities = new ArrayList<>();
    public static ArrayList<Food> foods = new ArrayList<>();                //Save here is the quantity for food stocks
    public static ArrayList<Menu> menus = new ArrayList<>();                //Save here is the menu with foods
    public static ArrayList<Reservation> reservations = new ArrayList<>();

    public static int durationLimit = 10;

    //Debug the getEndDate-Date here
    //Dili ma increment ang date

    public static void main(String[] args)  {

        Methods method = new Methods();

        customersList.add(new Customer("Dano", "secret", "hello world"));
        staffsList.add(new Staff("Robert", "Oso", "WlaOsoEmail"));

        rooms.add(new SingleRoom(5));
        rooms.add(new SingleRoom(2));
        rooms.add(new SingleRoom(11));
        rooms.add(new SingleRoom(4));
        rooms.add(new SingleRoom(3));
        rooms.add(new CoupleRoom(7));
        rooms.add(new CoupleRoom(6));
        rooms.add(new CoupleRoom(8));
        rooms.add(new CoupleRoom(10));
        rooms.add(new CoupleRoom(9));

        method.addAmenity(amenities, "Karaoke");
        method.addAmenity(amenities, "Pool");
        method.addAmenity(amenities, "Karaoke");
        method.addAmenity(amenities, "ReceptionHall");
        method.addAmenity(amenities, "GameRoom");

        reservations.add(new Reservation(customersList.get(0), rooms.get(5), (new Date(26, 27,2024)), 3));
        reservations.add(new Reservation(customersList.get(0), rooms.get(1), (new Date(11, 12,2024)), 3));
        reservations.add(new Reservation(customersList.get(0), rooms.get(6), (new Date(20, 13,2024)), 3));
        reservations.add(new Reservation(customersList.get(0), amenities.get(1), (new Date(23, 13,2024)), 3));

        reservations.add(new Reservation(customersList.get(0), rooms.get(5), (new Date(26, 27,2024)), 3));
        reservations.add(new Reservation(customersList.get(0), rooms.get(1), (new Date(11, 12,2024)), 3));
        reservations.add(new Reservation(customersList.get(0), rooms.get(6), (new Date(20, 13,2024)), 3));
        reservations.add(new Reservation(customersList.get(0), amenities.get(2), (new Date(22, 13,2024)), 3));
        Reservation reserve = new Reservation(customersList.get(0), amenities.get(2), (new Date(20, 13,2024)), 3);

        Date date1 = new Date(12, 13, 2003);
        Date date2 = new Date(12, 13, 2003);
        Date date3 = new Date(11, 13, 2003);
        Date dateThen = new Date(12, 14, 2003);
        Date date5 = new Date(12, 13, 2002);

        staffsList.get(0).addSales(new Transact(new Date(6, 4, 2024), customersList.get(0), dateThen, 700));
        staffsList.get(0).addSales(new Transact(new Date(14, 4, 2024), customersList.get(0), dateThen,200));
        staffsList.get(0).addSales(new Transact(new Date(6, 5, 2024), customersList.get(0), dateThen, 100));
        staffsList.get(0).addSales(new Transact(new Date(6, 8, 2024), customersList.get(0), dateThen, 3500));
        staffsList.get(0).addSales(new Transact(new Date(9, 4, 2024), customersList.get(0), dateThen, 1600));
        staffsList.get(0).addSales(new Transact(new Date(5, 4, 2024), customersList.get(0), dateThen, 1300));


        //=======================START HERE==============================
        ArrayList<Transact> transacts = staffsList.get(0).getSales();
        transacts = sortTransact(transacts);
        System.out.println("DISPLAY DATE=======");
        for(Transact transact: transacts) {
            Date date = transact.getDateOfTrans();
            System.out.print("Date: ");
            date.displayDate();
        }

        checkAccountForDate();

    }

    public static void checkAccountForDate() {
        //Keep in mind remove the reservation obj in the customer account and in admin reservations
        ArrayList<Transact> customerTransacts = customerAccount.getTransact();
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
                        ArrayList<Transact> transactions = customerAccount.getTransact();
                        customerAccount.addTransHistory(inHotel);                     //save the transact in transact history
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
                System.out.println(customerAccount.getName() + " your occupation has ended!");
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
                if((method.compareDate(reserve.getStartDate(), globalDate)==-1 &&
                        method.compareDate(reservation.getEndDate(), globalDate)==1)
                        || method.compareDate(reserve.getStartDate(), globalDate)==0) {    //The reservation have started
                    if(reservation.getRoom()!=null) {
                        Room room = reservation.getRoom();
                        Date newDate = new Date(reserve.getDateOfTrans());
                        HotelTransact inHotelTransact = new HotelTransact(newDate,
                                customerAccount,
                                room,
                                reservation.getStartDate(),
                                0,
                                reservation.getDuration(),
                                room.getReservationPrice());

                        customerAccount.addTransact(inHotelTransact);      //Add transact in customer
                        Main.roomTransacts.add(inHotelTransact);        //Add transact in MainRoomTransact
                        customerAccount.addTransHistory(reserve);          //Add || in the customer's transact history
                        Main.pastTransacts.add(reserve);                //Add transact in pastTransaction
                        Main.reserveTransacts.remove(reserve);  //Remove the transact in main;
                        Main.reservations.remove(reservation);          //Remove the reservation in reservations
                    }
                    if(reservation.getAmenity()!=null) {
                        Amenity amenity = reservation.getAmenity();
                        Date newDate = new Date(reserve.getDateOfTrans());
                        HotelTransact inHotelTransact = new HotelTransact(newDate,
                                customerAccount,
                                amenity,
                                reservation.getStartDate(),
                                0,
                                reservation.getDuration(),
                                amenity.getReservationCost());

                        customerAccount.addTransact(inHotelTransact);      //Add transact in customer
                        amenity.setIsReserved(true);
                        Main.roomTransacts.add(inHotelTransact);        //Add transact in MainRoomTransact
                        customerAccount.addTransHistory(reserve);          //Add || in the customer's transact history
                        Main.pastTransacts.add(reserve);                //Add transact in pastTransaction
                        Main.reserveTransacts.remove(reserve);  //Remove the transact in main;
                        Main.reservations.remove(reservation);          //Remove the reservation in reservations
                    }
                    System.out.println("Your reservation have started!");

                    //IF DURATION OF RESERVATION IS OVERDUE BY GLOBALDATE
                }else if(method.compareDate(reservation.getEndDate(), globalDate)==-1) {
                    customerAccount.addTransHistory(reserve);          //Add || in the customer's transact history
                    Main.pastTransacts.add(reserve);                //Add transact in pastTransaction
                    Main.reserveTransacts.remove(reserve);  //Remove the transact in main;
                    Main.reservations.remove(reservation);          //Remove the reservation in reservations
                    System.out.println("Your reservation have ended!");
                }
            }//End of Reservation forloop
        }//End of check HotelReservationTransact

    }//End of checkAccountDate


    public static ArrayList<Transact> sortTransact(ArrayList<Transact> transacts) {
        ArrayList<Transact> sortTransact = transacts;
        for(int i = 0; i<sortTransact.size(); i++) {
            for(int k = i+1; k< sortTransact.size(); k++) {
                Transact transact = sortTransact.get(i);
                Transact transact2 = sortTransact.get(k);
                if(compareDate(transact.getDateOfTrans(), transact2.getDateOfTrans())==1) { //If date1 is greater than date2 then change
                    Transact temp = new Transact(transact);
                    sortTransact.set(i, transact2);
                    sortTransact.set(k, temp);
                    System.out.print("Date is changed at " + i + ": ");
                    transact2.getDateOfTrans().displayDate2();
                    System.out.print("Date is changed from " + k + ": ");
                    transact.getDateOfTrans().displayDate2();
                    System.out.println("");
                }
            }//End of inner for loop
        }//End of outer for loop
        return sortTransact;
    }

    public static int compareDate(Date date, Date date2) {      //Date1 and date2  //0 if equal, -1 if date less than date2, 1 if date greater than date2
        if(date.getYear()==date2.getYear()) {                   //Same year
            if(date.getMonth()==date2.getMonth()) {             //Same month
                if(date.getDate()==date2.getDate()) {           //Same date
                    return 0;
                }else if(date.getDate()<date2.getDate()) {     //date1 date is lesser than
                    return -1;
                }else {
                    return 1;
                }
            }else if(date.getMonth()<date2.getMonth()) {        //date1 month lesser than date 2
                return -1;
            }else {                                             //date1 month lesser than date 2
                return 1;
            }
        }else if(date.getYear()<date2.getYear()) {              //date1 year lesser than date2
            return -1;
        }else {                                                 //date 1 year higher than date2
            return 1;
        }
    }

}