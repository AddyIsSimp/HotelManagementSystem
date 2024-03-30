package Rooms;
import Entity.Customer;
import Entity.Person;
import Rooms.Room;
import Amenity.*;

import java.util.ArrayList;

public class Reservation {

    //Assign the reservation in RoomNum not in the room instance

    private Customer person;    //Person that reserve
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
        this.person = person;
        this.room = room;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Reservation(Customer person, String amenityCode, Date startDate, int duration) {
        this.amenity.setAmenityCode(amenityCode);
        this.startDate = startDate;
        this.duration = duration;
    }

    public void setPerson(Customer person) {this.person = person;}
    public Person getPerson() {return person;}

    public void setstartDate(Date startDate) {this.startDate = startDate;}
    public Date getStartDate() {return startDate;}

    public void setDuration(int duration) {this.duration = duration;}
    public int getDuration() {return this.duration;}

    public void setRoom(Room room) {this.room = room;}
    public Room getRoom() {return room;}

    public Date getEndDate() {
        Date date = startDate;
        int duration = 0;

        for(int i = 0; i<duration; i++) {
            if(date.getDate()<=31 ) {   //Not lapse in 31 days
                date.incrementDate();
            }else {     //if lapse in 31 days
                if(date.getMonth()<=12) {   //Not lapse in 12 months
                    date.incrementMonth();
                    date.setDate(1);
                }else {   //lapse in 12 months
                    date.incrementYear();
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
            if(i!=0) {
                Date date2 = durationDate.get(i-1);
                temp = new Date(date2);
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

    public double getReservationPrice() {
        try{
            computePrice();
            return reservationPrice;
        }catch (Exception e) {
            System.out.println("INVALID: Arithmetic error");
        }
        return -1;
    }

    public void computePrice() throws Exception {
        double price = room.getRatePerDay()*duration;
        price/=2;
        this.reservationPrice = price;
    }

}
