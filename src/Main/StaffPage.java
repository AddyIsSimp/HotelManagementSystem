package Main;

import Entity.*;
import Rooms.*;
import Rooms.Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffPage {

    public static Staff staffAcct = null;

    private Scanner sc = new Scanner(System.in);
    private String choice = null;
    private Methods method = new Methods();
    private CustomerPage customerPage = new CustomerPage();

    //LOGIN STAFF
    boolean loginStaff(ArrayList<Staff> staffsList) {
        String un = null;
        String pw = null;
        boolean isLogin = false;

        while(true) {
            System.out.println("\n=====STAFF=====");
            System.out.print("Username: ");
            un = sc.nextLine();
            System.out.print("Password: ");
            pw = sc.nextLine();

            //Checks the list of Staff if there is same name and password
            for(int i = 0; i<staffsList.size(); i++) {
                Staff temp = staffsList.get(i);
                if(temp.getName().equals(un) && temp.getPassword().equals(pw)) {
                    staffAcct = temp;
                    isLogin = true;
                    return true;
                }
            }

            //No staff found w/ same un & pw
            if(isLogin==false) {
                boolean isCont = method.isContinue();
                if(isCont==true) continue;
                else return false;
            }
        }//End of isLogin whileloop
    }//end of loginStaff method


    //========================================SUB-MENU============================================================
    //================================MANAGE-ROOMS
    public void goManageRoom(ArrayList<Room> rooms) {
        boolean isManage = true;
        ArrayList<Room> occupiedRoom = new ArrayList<>();
        ArrayList<Room> availRoom = new ArrayList<>();
        ArrayList<Room> disableRoom = new ArrayList<>();

        if(rooms.size()!=0) {
            for(int i = 0; i<rooms.size(); i++) {
                Room room = rooms.get(i);
                if(room.getIsOccupied()==true) {
                    occupiedRoom.add(room);
                }else if(room.getIsOccupied()==false) availRoom.add(room);
                if(room.getIsDisabled()==true) disableRoom.add(room);
            }
        }

        while(isManage==true) {
            System.out.println("\n=====MANAGE-ROOMS=====");
            System.out.println("[1] Display occupied rooms");
            System.out.println("[2] Display available rooms");
            System.out.println("[3] Display disabled rooms");
            System.out.println("[4] View category");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //display occupied rooms
                    if(occupiedRoom.size()==0) {
                        System.out.println("\nThere are no rooms occupied");
                    }else {
                        System.out.println("=====OCCUPIED-ROOM=====");
                        for(int i = 0; i<occupiedRoom.size(); i++) {
                            Room room = occupiedRoom.get(i);
                            System.out.println((i+1) + " Room: " + room.getRoomType() + " " +
                                    room.getRoomNum() + " || Customer: ");
                        }
                    }
                    break;
                case 2:     //display available roos
                    if(rooms.size()==0) {
                        System.out.println("\nThere are no rooms");
                    }else {
                        for(int i = 0; i<availRoom.size(); i++) {
                            Room room = availRoom.get(i);
                            System.out.println((i+1) + " Room: " + room.getRoomType() + " " +
                                    room.getRoomNum() + " || Customer: ");
                        }
                    }
                    break;
                case 3:     //display occupied room
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================CUSTOMER-ACCOUNT================================
    public void goCustomerAccount(ArrayList<Customer> customers) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("\n=====CUSTOMER-ACCOUNT=====");
            System.out.println("[1] Register customer");
            System.out.println("[2] View all customers");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    Customer customer = customerPage.register(customers);
                    customers.add(customer);
                    break;
                case 2:
                    System.out.println("=====Customer Lists=====");
                    method.displayCustomer(customers);
                    method.isGoBack();
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================RESERVATIONS================================
    public void goReservations(ArrayList<Reservation> reservations) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("\n=====RESERVATIONS=====");
            System.out.println("[1] View all reservations");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    method.displayReservations(reservations);
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================SALES================================
    public void goSales(Staff staff) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("\n=====SALES=====");
            System.out.println("[1] Sales");
            System.out.println("[2] Total sales");
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

}
