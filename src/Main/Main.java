package Main;

import Entity.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    //INSTANCE VARIABLES
    private static Scanner sc = new Scanner(System.in);            //For string inputs
    private static Scanner in = new Scanner(System.in);            //For int inputs
    public static Admin admin = new Admin();
    static StaffPage staffP = new StaffPage();
    static CustomerPage customerP = new CustomerPage();
    static Methods method = new Methods();

    public static ArrayList<Customer> customersList = new ArrayList<>();
    public static ArrayList<Staff> staffsList = new ArrayList<>();

    //MAIN METHOD
    public static void main(String[] args) {

        //SAMPLE CREDENTIALS
        //Main.AdminPage(); Username = Admin123, Password = admin123
        customersList.add(new Customer("Beth", "Sophia"));
        staffsList.add(new Staff("Dave", "Gaga-a", "Gaga-a"));

        //Local Main.Methods
        Scanner sc = new Scanner(System.in);    //Use for string inputs
        Scanner in = new Scanner(System.in);    //Use for int /double inputs
        int choice = 0;
        boolean main = true;

        System.out.println("HOTEL MANAGEMENT SYSTEM");

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
                    isStaff = method.loginStaff(staffsList);
                    if(isStaff==true) StaffPage();
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
                    System.out.println("=====MANAGE-ROOMS======");
                    break;
                case 3:
                    System.out.println("=====FOOD-SERVICE======");
                    break;
                case 4:
                    System.out.println("=====FOOD-INVENTORY======");
                    break;
                case 5:
                    System.out.println("=====RESERVATIONS======");
                    break;
                case 6:
                    System.out.println("=====REPORTS======");
                    break;
                case 0:
                    System.out.println("Log-out successfully");
                    isAdminPage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    boolean isCont = method.isContinue();
                    if (isCont==true) AdminPage();
                    else break;
            }
        }
    }//End of AdminPage method

    static void StaffPage() {
        String choice = null;
        boolean isStaffPage = true;

        while(isStaffPage==true) {
            System.out.println("==========STAFF==========");
            System.out.println("[1] Room management");
            System.out.println("[2] Customer management");
            System.out.println("[3] Reservations");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("=====ROOM-MANAGEMENT======");
                    break;
                case "2":
                    System.out.println("=====CUSTOMER-MANAGEMENT======");
                    break;
                case "3":
                    System.out.println("=====RESERVATIONS======");
                    break;
                case "0":
                    System.out.println("Log-out successfully");
                    isStaffPage = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    boolean isCont = method.isContinue();
                    if (isCont==true) continue;
                    else break;
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
                    System.out.println("=====ACCOUNT======");
                    break;
                case "2":
                    System.out.println("=====ROOMS======");
                    break;
                case "3":
                    System.out.println("=====RESERVATIONS======");
                    break;
                case "4":
                    System.out.println("=====IN-HOTEL-ORDERS======");
                    break;
                case "0":
                    System.out.println("Log-out successfully");
                    isCustomerPage = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    boolean isCont = method.isContinue();
                    if (isCont==true) continue;
                    else break;
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
