package Main;

import Entity.*;
import Rooms.*;
import Menus.*;
import Amenity.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    //CONCERNS: Remove the addAppliance() in Rooms for easy no hassle
    //          Ask if pwede ba ang ArrayList

    //INSTANCE VARIABLES
    private static Scanner sc = new Scanner(System.in);            //For string inputs
    private static Scanner in = new Scanner(System.in);            //For int inputs
    public static Admin admin = new Admin();
    static StaffPage staffP = new StaffPage();
    static CustomerPage customerP = new CustomerPage();
    static Methods method = new Methods();

    //LISTS
    public static ArrayList<ArrayList> all = new ArrayList<>();
    public static ArrayList<Customer> customersList = new ArrayList<>();
    public static ArrayList<Staff> staffsList = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Amenity> amenities = new ArrayList<Amenity>();
    public static ArrayList<Food> foods = new ArrayList<>();                //Save here is the quantity
    public static ArrayList<Menu> menus = new ArrayList<>();                //Save here is the menu with foods
    public static ArrayList<Order> orders = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();

    public static Staff staffAcct = null;
    public static Customer customerAcct = null;

    //MAIN METHOD
    public static void main(String[] args) {

        //SAMPLE CREDENTIALS
        //Main.AdminPage(); Username = Admin123, Password = admin123
        customersList.add(new Customer("Beth", "Sophia"));
        staffsList.add(new Staff("Dave", "Gaga-a", "Gaga-a"));

        //Add all arraylist in all
        all.add(customersList); all.add(staffsList);
        all.add(rooms); all.add(reservations);
        all.add(foods); all.add(menus);

        //Local Main.Methods
        Scanner sc = new Scanner(System.in);    //Use for string inputs
        Scanner in = new Scanner(System.in);    //Use for int /double inputs
        int choice = 0;
        boolean main = true;

        System.out.println("HOTEL MANAGEMENT SYSTEM");

        //MAIN LOOP
        while(main) {
            Person loginPerson;             //stores the person that Login
            boolean isAdmin = false;
            boolean isStaff = false;
            boolean isCustomer = false;

            System.out.println("=====SELECT-USER=====");
            System.out.println("[1] ADMIN");
            System.out.println("[2] STAFF");
            System.out.println("[3] CUSTOMER");
            System.out.print("Enter choice: ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    isAdmin = admin.loginAdmin();
                    if(isAdmin==true) AdminPage();
                    else continue;
                    break;
                case 2:
                    isStaff = staffP.loginStaff(staffsList);
                    if(isStaff==true) StaffPage(staffAcct);
                    break;
                case 3:
                    isCustomer = EnterCustomer();
                    if(isCustomer==true) CustomerPage();
                    else continue;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    continue;
            }
        }
    }

//=====================================METHODS================================================
    static void AdminPage() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner(System.in);

        boolean isAdminPage = true;
        while(isAdminPage==true) {
            System.out.println("==========ADMIN==========");
            System.out.println("[1] Manage Accounts");
            System.out.println("[2] Manage Rooms");
            System.out.println("[3] Food Service");
            System.out.println("[4] Food Inventory");
            System.out.println("[5] Reservations");
            System.out.println("[6] Reports");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    admin.manageAccounts(customersList, staffsList, admin);
                    break;
                case 2:
                    admin.manageRoom(rooms);
                    break;
                case 3:
                    admin.foodService(menus);
                    break;
                case 4:
                    admin.goInventory(foods);
                    break;
                case 5:
                    admin.goReservations(reservations);
                    break;
                case 6:
                    admin.goReports(all);
                    break;
                case 0:
                    System.out.println("Log-out successfully");
                    isAdminPage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }//End of AdminPage method

    static void StaffPage(Staff staff) {
        String choice = null;
        boolean isStaffPage = true;

        while(isStaffPage==true) {
            System.out.println("==========STAFF==========");
            System.out.println("[1] Room management");
            System.out.println("[2] Customer management");
            System.out.println("[3] Reservations");
            System.out.println("[4] Sales");
            System.out.println("[0] Logout");
            int pick = method.inputInt("Enter choice: ");

            switch (pick) {
                case 1:
                    staffP.goManageRoom(rooms);
                    break;
                case 2:
                    staffP.goCustomerAccount(customersList);
                    break;
                case 3:
                    staffP.goReservations(reservations);
                    break;
                case 4:
                    staffP.goSales(staff);
                    break;
                case 0:
                    System.out.println("Log-out successfully");
                    isStaffPage = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }//End of Main.StaffPage method

    static void CustomerPage() {
        String choice = null;
        boolean isCustomerPage = true;

        while(isCustomerPage==true) {
            System.out.println("==========CUSTOMERS==========");
            System.out.println("[1] Account");
            System.out.println("[2] Rooms");
            System.out.println("[3] Reservation");
            System.out.println("[4] In-Hotel Orders");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    customerP.goAccount(customerAcct);
                    break;
                case "2":
                    customerP.goSelectRoom(rooms);
                    break;
                case "3":
                    customerP.goBookReservation(rooms, amenities, reservations);
                    break;
                case "4":
                    customerP.goHotelOrders(orders);
                    break;
                case "0":
                    System.out.println("Log-out successfully");
                    isCustomerPage = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }//End of CustomerPage

    static boolean EnterCustomer() {
        int choice = 0;
        boolean isCustomer = false;             //this is the return type
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Methods method = new Methods();

        Customer:while(true) {
            System.out.println("======CUSTOMER======");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    isCustomer = customerP.loginCustomer(customersList);
                    if (isCustomer == true) return true;
                    break;
                case 2:
                    Customer newUser = customerP.register(customersList);
                    if (newUser != null) customersList.add(newUser);
                    break;
                default:
                    boolean isCont = method.isContinue();
                    if (isCont == true) continue;
                    break Customer;
            }
        }
        return isCustomer;
    }//End of Enter Customer

}
