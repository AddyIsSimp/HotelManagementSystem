package Rooms;
import Entity.Customer;
import Amenity.*;
import Transaction.Transact;

import java.util.ArrayList;

public class Reservation extends Transact {

    //Assign the reservation in RoomNum not in the room instance

    private Customer customer;    //Person that reserve
    private Amenity amenity;
    private Room room;
    private Date startDate;     //Start Date of reservation
    private int duration;       //Duration of reservation
    private ArrayList<Date> durationDay = new ArrayList<>();
    private double reservationPrice = 0;

    public Reservation() {
        room.setRoomNum(-1);
        startDate = null;
    }

    public Reservation(Room room, Date startDate, int duration) {
        this.room = room;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Reservation(Customer person, Room room, Date startDate, int duration) {
        this.customer = person;
        this.room = room;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Reservation(Reservation reservation) {
        this.customer = reservation.getCustomer();
        this.room = reservation.getRoom();
        this.startDate = reservation.getStartDate();
        this.duration = reservation.getDuration();
        if(reservation.getRoom()!=null) {
            this.reservationPrice = reservation.getRoom().getReservationPrice();
        }else{
            this.reservationPrice = reservation.getAmenity().getReservationCost();
        }
    }

    public Reservation(Customer person, Amenity amenity, Date startDate, int duration) {
        this.amenity = amenity;
        this.startDate = startDate;
        this.duration = duration;
    }

    public void setCustomer(Customer person) {this.customer = person;}
    public Customer getCustomer() {return customer;}

    public void setStartDate(Date startDate) {this.startDate = startDate;}
    public Date getStartDate() {return startDate;}

    public void setDuration(int duration) {this.duration = duration;}
    public int getDuration() {return this.duration;}

    public void setRoom(Room room) {this.room = room;}
    public Room getRoom() {return room;}

    public void setAmenity(Amenity amenity) {this.amenity = amenity;}
    public Amenity getAmenity() {return amenity;}

    public ArrayList<Date> getDurationDay() {
        ArrayList<Date> durationDay = getDuration(startDate, duration);
        return durationDay;
    }

    public Date getEndDate() {
        Date date = new Date(startDate);
        int duration = this.duration;

        for(int i = 0; i<duration; i++) {
            if(i==0) continue;
            if(date.getDate()<31 ) {   //Not lapse in 31 days
                date.setDate(date.incrementDate());
            }else {     //if lapse in 31 days
                if(date.getMonth()<12) {   //Not lapse in 12 months
                    date.setMonth(date.incrementMonth());
                    date.setDate(1);
                }else {   //lapse in 12 months
                    date.setYear(date.incrementYear());
                    date.setMonth(1);
                    date.setDate(1);
                }
            }
        }
        return date;
    }

    public ArrayList<Date> getDuration(Date start, int duration) {
        ArrayList<Date> durationDate = new ArrayList<Date>();       //Store here the duration of reservation in consecutive dates
        Date temp = new Date(start);      //Temporary date diri mag modify

        for(int i = 0; i<duration; i++) {
            if(i!=0) {      //If there is already a date, increment the date
                Date date2 = durationDate.get(i-1);
                temp = new Date(date2);
            }else {
                durationDate.add(new Date(temp));
                continue;
            }

            if(temp.getDate()<31) {
                temp.setDate(temp.incrementDate());
            }else if(temp.getDate()==31){
                if(temp.getMonth()<12) {
                    temp.setMonth(temp.incrementMonth());
                    temp.setDate(1);
                }else if(temp.getMonth()==12){
                    temp.setYear(temp.incrementYear());
                    temp.setMonth(1);
                    temp.setDate(1);
                }
            }else {
                System.out.println("INVALID: Wrong date");
            }

            durationDate.add(temp);

        }
        this.durationDay = durationDate;
        return durationDate;
    }

    public void displayDateDuration(ArrayList<Date> durationDate) {
        for(int i = 0; i<durationDate.size(); i++) {
            Date date = durationDate.get(i);
            date.displayDate();
        }
    }

    public double computeReservationPrice() {
        try{
            if(this.room!=null) computePriceRoom();
            else if(this.amenity!=null) computeAmenityReservePrice();
            return reservationPrice;
        }catch (Exception e) {
            System.out.println("INVALID: Arithmetic error");
        }
        return -1;
    }

    public void computeRoomReservePrice() {
        double price = room.getReservationPrice()*duration;
        this.reservationPrice = price;
    }

    public void computeAmenityReservePrice() {
        double price = amenity.getReservationCost()*duration;
        this.reservationPrice = price;
    }

    public void computePriceRoom() {
        double price = room.getRatePerDay()*duration;
        this.reservationPrice = price;
    }

}
