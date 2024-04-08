import Amenity.Amenity;
import Entity.Customer;
import Entity.Staff;
import Foods.Food;
import Main.Methods;
import Foods.Menu;
import Transaction.Order;
import Rooms.*;
import Rooms.Date;

import java.util.*;

public class ForCodeTrial {

    public static ArrayList<ArrayList> all = new ArrayList<>();
    public static ArrayList<Customer> customersList = new ArrayList<>();
    public static ArrayList<Staff> staffsList = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Amenity> amenities = new ArrayList<>();
    public static ArrayList<Food> foods = new ArrayList<>();                //Save here is the quantity for food stocks
    public static ArrayList<Menu> menus = new ArrayList<>();                //Save here is the menu with foods
    //public static ArrayList<Order> orders = new ArrayList<>();              //Store here are the orders
    public static ArrayList<Reservation> reservations = new ArrayList<>();

    public static int durationLimit = 10;

    //Debug the getEndDate-Date here
    //Dili ma increment ang date

    public static void main(String[] args)  {

        Methods method = new Methods();

        customersList.add(new Customer("Dano", "secret", "hello world"));

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
        Date date4 = new Date(12, 14, 2003);
        Date date5 = new Date(12, 13, 2002);

        boolean isTrue = checkReservation(reservations, reserve);

        if(isTrue==true) System.out.println("No conflict in reservations");
        else {
            System.out.println("Naay conflict");
            System.out.println("Pag debug diha tapulan");
        }
    }

    public static boolean checkReservation(ArrayList<Reservation> reservations, Reservation reserve) {
        boolean isGood = true;      //Signs if there is a conflict or no conflict in date
        boolean isRoom = false;
        boolean isAmenity = false;

        //Checks whether reserve is amenity or room reserve
        if(reserve.getRoom()!=null) {
            System.out.println("room ang na reserve");
            isRoom = true;
        } else if(reserve.getAmenity()!=null) {
            System.out.println("amenity ang na reserve");
            isAmenity = true;
        }

        if(isRoom==true) {
            for(int i = 0; i<reservations.size(); i++) {
                System.out.println("Sulod dre 1");
                Reservation reserveList = reservations.get(i);
                if(reserveList.getRoom()!=null) {   //Reservation is room

                    Room roomList = reserveList.getRoom();  //Get the room reservation in list
                    Room room = reserve.getRoom();          //Get the room reservation in reserve
                    if(roomList!=room) continue;
                    ArrayList<Date> durationDays = reserveList.getDuration(reserveList.getStartDate(), reserveList.getDuration());
                    for(int j = 0; j<durationDays.size(); j++) {        //Iterate for Date in durationDays compareTo rsrvation obj
                        Date date = durationDays.get(j);                //Get the date in durationDays
                        ArrayList<Date> durationToRsrv = reserve.getDuration(reserve.getStartDate(), reserve.getDuration());
                        for(int k = 0; k<durationToRsrv.size(); k++) {
                            if(durationToRsrv.get(k)==(date)) isGood = false;     //There is a conflict in Date;
                        }
                    }
                }else continue;
            }
        }else if(isAmenity==true) {
            for(int i = 0; i<reservations.size(); i++) {
                Reservation reserveList = reservations.get(i);
                if(reserveList.getAmenity()!=null) {   //Reservation is amenity
                    System.out.println("Sulod dre 2");
                    Amenity amenityList = reserveList.getAmenity();  //Get the amenity reservation in list
                    Amenity amenity = reserve.getAmenity();          //Get the amenity reservation in reserve
                    if(amenityList.equals(amenity)==false) {
                        System.out.println("kapareha amenity sa index pero lahi obj sa " + i);
                        continue;
                    }

                    System.out.println("Same cla ug amenityngaObj");
                    ArrayList<Date> durationDays = reserveList.getDuration(reserveList.getStartDate(), reserveList.getDuration());
                    for(int j = 0; j<durationDays.size(); j++) {        //Iterate for Date in durationDays compareTo rsrvation obj
                        Date date = durationDays.get(j);                //Get the date in durationDays
                        ArrayList<Date> durationToRsrv = reserve.getDuration(reserve.getStartDate(), reserve.getDuration());
                        for(int k = 0; k<durationToRsrv.size(); k++) {
                            if(durationToRsrv.get(k).equals(date)) {
                                System.out.println("Conflict in i: " + (i) + " || j: " + (j) + " || k: " + (k));
                                isGood = false;     //There is a conflict in Date;
                            }
                        }
                    }
                }else continue;
            }
        }
        return isGood;
    }

}