package Main;

import Entity.*;
import Rooms.*;
import Amenity.*;
import Foods.*;
import Transaction.Order;
import Transaction.ReserveTransact;
import Transaction.RoomTransact;
import Transaction.Transact;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    //BUGS
    //  Admin-Reservation-ModifyReservation - checkReservation returns to false;
    //  When delete the amenity in the previous number then add a new one will result in wrong code.
    //  The checkReservation for amenity should be added in the check Reservation

    //TO-DO
    //  There should be a message when a customer reach its time of reservation/ and a follow-up payment for the kulang
    //  Admin-Room Management-Method-setRate()  - to modify the rate of the room
    //  Methods-computeBills()
    //  Methods-paymentProcess()
    //Create a method to search for duplicate reservation in RoomsList and reservations

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
    public static ArrayList<Amenity> amenities = new ArrayList<>();
    public static ArrayList<Food> foods = new ArrayList<>();                //Save here is the quantity for food stocks
    public static ArrayList<Menu> menus = new ArrayList<>();                //Save here is the menu with foods
    public static ArrayList<Reservation> reservations = new ArrayList<>();

    public static ArrayList<ReserveTransact> pendingReservation = new ArrayList<>();
    public static ArrayList<RoomTransact> roomTransacts = new ArrayList<>();

    //  DILI SURE NI public static ArrayList<Order> orders = new ArrayList<>();              //Store here are the orders of customer ug maremove if mocheck out na ang
    //      customer ibalhin sa past order sa customer account
    //Diri magbase sa katong nag-occupy ug room/ ug sa katong naay reserve

    public static int durationLimit = 10;               //Maximum days to reserve or occupy in a single transact

    public static Date globalDate = new Date(8, 4, 2024);

    //MAIN METHOD
    public static void main(String[] args) {

        while (true) {

            Scanner sc = new Scanner(System.in);    //Use for string inputs
            Scanner in = new Scanner(System.in);    //Use for int /double inputs
            int choice = 0;
            String pick = null;
            boolean main = true;

            //SAMPLE CREDENTIALS
            //Main.AdminPage(); Username = Admin123, Password = admin123
            customersList.add(new Customer("Beth", "Sophia"));
            staffsList.add(new Staff("Dave", "Gaga-a", "Gaga-a"));

            //Add all arraylist in all
            all.add(customersList);
            all.add(staffsList);
            all.add(rooms);
            all.add(reservations);
            all.add(foods);
            all.add(menus);

            foods.add(new MainDish("Humba", 120, 30));
            foods.add(new MainDish("Tinola Manok", 170, 30));
            foods.add(new SideDish("Crispy chicken", 120, 30));
            foods.add(new SideDish("Toron", 170, 30));
            foods.add(new Drinks("Lambanog", 120, 30));
            foods.add(new Drinks("Red Horse", 170, 30));
            foods.add(new Dessert("MangoFloat", 120, 30));
            foods.add(new Dessert("Macaroni", 170, 30));

            menus.add(new Menu("Adrian", foods.get(1), foods.get(3), foods.get(5), foods.get(7)));
            menus.add(new Menu("Santos", foods.get(0), foods.get(2), foods.get(4), foods.get(6)));

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

            reservations.add(new Reservation(customersList.get(0), rooms.get(5), (new Date(26, 27, 2024)), 3));
            reservations.add(new Reservation(customersList.get(0), rooms.get(1), (new Date(11, 12, 2024)), 3));
            reservations.add(new Reservation(customersList.get(0), rooms.get(6), (new Date(20, 13, 2024)), 3));
            reservations.add(new Reservation(customersList.get(0), amenities.get(2), (new Date(20, 13, 2024)), 3));

            System.out.println("HOTEL MANAGEMENT SYSTEM");

            int count = 0;
            //MAIN LOOP
            while (main) {
                Person loginPerson;             //stores the person that Login
                boolean isAdmin = false;
                boolean isStaff = false;
                boolean isCustomer = false;

                if (count == 0) {
                    System.out.println("=====SELECT-USER=====");
                    count++;
                } else System.out.println("\n=====SELECT-USER=====");
                System.out.println("[1] ADMIN");
                System.out.println("[2] STAFF");
                System.out.println("[3] CUSTOMER");
                System.out.println("[4] SET CURRENT DATE");
                System.out.print("Enter choice: ");
                choice = method.inputInt();

                switch (choice) {
                    case 1:
                        isAdmin = admin.loginAdmin();
                        if (isAdmin == true) AdminPage();
                        else continue;
                        break;
                    case 2:
                        isStaff = staffP.loginStaff(staffsList);
                        if (isStaff == true) StaffPage(StaffPage.staffAcct);
                        break;
                    case 3:
                        isCustomer = EnterCustomer();
                        if (isCustomer == true) CustomerPage();
                        else continue;
                        break;
                    case 4:
                        globalDate = method.inputDate();
                        System.out.print("Date is successfully set to ");
                        globalDate.displayDate();
                        break;
                    default:
                        if (choice != -1) System.out.println("INVALID: Use indicated number only");
                        continue;
                }
            }
//            } catch (Exception e) {
//                System.out.println(e);
//                System.out.println("ERROR: Please try again");
//            }
        }

    }

    //=====================================METHODS================================================
    static void AdminPage() {
        boolean adminMain = true;
        Admin:
        while (adminMain == true) {

            try {
                int choice = 0;
                Scanner sc = new Scanner(System.in);
                Scanner in = new Scanner(System.in);

                boolean isAdminPage = true;
                while (isAdminPage == true) {
                    System.out.println("\n==========ADMIN==========");
                    System.out.println("[1] Manage Accounts");
                    System.out.println("[2] Manage Rooms");
                    System.out.println("[3] Food Service");
                    System.out.println("[4] Food Inventory");
                    System.out.println("[5] Reservations");
                    System.out.println("[6] Reports");
                    System.out.println("[0] Logout");
                    System.out.print("Enter choice: ");
                    choice = method.inputInt();

                    switch (choice) {
                        case 1:
                            admin.manageAccounts(customersList, staffsList, admin);
                            break;
                        case 2:
                            admin.manageRoom(rooms, amenities);
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
                            while (true) {
                                System.out.println("Do you want to log-out? [1]Yes/[2]No");
                                choice = method.inputInt();
                                if (choice == 1) {
                                    System.out.println("Log-out successfully");
                                    adminMain = false;
                                    isAdminPage = false;
                                    break;
                                } else if (choice == 2) continue Admin;
                                else {
                                    System.out.println("INVALID: Use indicated number only!");
                                }
                            }
                            break;
                        default:
                            System.out.println("INVALID: Use indicated number only!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: Please try again!");
            }
        }
    }//End of AdminPage method

    static void StaffPage(Staff staff) {
        int choice = 0;
        boolean staffMain = true;
        Staff:
        while (staffMain == true) {
            try {

                int pick = 0;
                boolean isStaffPage = true;

                while (isStaffPage == true) {
                    System.out.println("\n==========STAFF==========");
                    System.out.println("[1] Room management");
                    System.out.println("[2] Customer management");
                    System.out.println("[3] Reservations");
                    System.out.println("[4] Sales");
                    System.out.println("[0] Logout");
                    System.out.print("Enter choice: ");
                    pick = method.inputInt();

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
                            while (true) {
                                System.out.println("Do you want to log-out? [1]Yes/[2]No");
                                choice = method.inputInt();
                                if (choice == 1) {
                                    System.out.println("Log-out successfully");
                                    staffMain = false;
                                    isStaffPage = false;
                                    break;
                                } else if (choice == 2) continue Staff;
                                else {
                                    System.out.println("INVALID: Use indicated number only!");
                                }
                            }
                            break;
                        default:
                            System.out.println("INVALID: Use indicated number only!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: Please try again!");
            }
        }
    }//End of Main.StaffPage method

    static void CustomerPage() {
        int choice = 0;
        boolean customerMain = true;
        Customer:
        while (customerMain == true) {
            try {
                int pick = 0;
                boolean isCustomerPage = true;

                while (isCustomerPage == true) {
                    System.out.println("\n==========CUSTOMERS==========");
                    System.out.println("[1] Account");
                    System.out.println("[2] Rooms");
                    System.out.println("[3] Reservation");
                    System.out.println("[4] In-Hotel Orders");
                    System.out.println("[0] Logout");
                    System.out.print("Enter choice: ");
                    pick = method.inputInt();

                    switch (pick) {
                        case 1:
                            customerP.goAccount(CustomerPage.customerAcct);
                            break;
                        case 2:
                            customerP.goSelectRoom(rooms);
                            break;
                        case 3:
                            customerP.goBookReservation(rooms, amenities, reservations);
                            break;
                        case 4:
                            customerP.goHotelOrders();
                            break;
                        case 0:
                            while (true) {
                                System.out.println("Do you want to log-out? [1]Yes/[2]No");
                                choice = method.inputInt();
                                if (choice == 1) {
                                    System.out.println("Log-out successfully");
                                    customerMain = false;
                                    isCustomerPage = false;
                                    break;
                                } else if (choice == 2) continue Customer;
                                else {
                                    System.out.println("INVALID: Use indicated number only!");
                                }
                            }
                            break;
                        default:
                            System.out.println("INVALID: Use indicated number only!");
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please try again!");
            }

        }
    }//End of CustomerPage

    static boolean EnterCustomer() {
        int choice = 0;
        boolean isCustomer = false;             //this is the return type

        try {
            Scanner sc = new Scanner(System.in);
            Scanner in = new Scanner(System.in);
            Methods method = new Methods();

            Customer:
            while (true) {
                System.out.println("\n======CUSTOMER======");
                System.out.println("[1] Login");
                System.out.println("[2] Register");
                choice = method.inputInt("Enter choice: ");

                switch (choice) {
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
        } catch (Exception e) {
            System.out.println("ERROR: Please try again!");
        }
        return isCustomer;
    }//End of Enter Customer

}
