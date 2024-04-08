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
        while(isManage==true) {
            System.out.println("\n=====MANAGE-ROOMS=====");
            System.out.println("[1] Display rooms");
            System.out.println("[2] View category");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    method.displayAllRoom(rooms);
                    break;
                case 2:
                    System.out.println("Displaying Available Rooms");
                    method.displayAvailRoomNum(rooms);
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
                    break;
                case 2:
                    System.out.println("Displaying all customers");
                    method.displayCustomer(customers);
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
                    System.out.println("Displaying all reservations room");
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
