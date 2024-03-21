package Main;

import Entity.*;
import Main.*;
import Menus.*;
import Rooms.Reservation;
import Rooms.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private String userName = "Admin123";
    private String password = "admin123";
    private Methods method = new Methods();

    private String text = null;
    private int choice = 0;
    private Scanner sc = new Scanner(System.in);
    private Scanner in = new Scanner(System.in);

    Admin() {

    }

    //====================GETTER&&SETTER========================
    public String getUsername() {
        return userName;
    }
    public void setUsername(String s) {
        this.userName = s;
    }

    public String getPassword() {return password;}
    public void setPassword(String s) {this.password = s;}

    //====================METHODS=============================

    //LOGIN ADMIN
    boolean loginAdmin() {
        String un = null;
        String pw = null;
        boolean login = false;

        while(login==false) {
            System.out.println("=====ADMIN-LOGIN=====");
            System.out.print("Username: ");
            un = sc.nextLine();
            System.out.print("Password: ");
            pw = sc.nextLine();

            //Checks if the credentials is correct
            if(un.equals(Main.admin.getUsername()) && pw.equals(Main.admin.getPassword())) {
                System.out.println("Welcome back commander!");
                login = true;
                return true;
            }else {
                System.out.println("Wrong username or password");
                boolean isCont = method.isContinue();
                if (isCont==true) continue;
                else return false;
            }
        }
        return login;
    }

//========================================SUB-MENU============================================================
    //================================MANAGE ACCOUNTS
    public void manageAccounts(ArrayList<Customer> customers, ArrayList<Staff> staffs, Admin admin) {

        Account : while(true) {
            System.out.println("=====MANAGE-ACCOUNTS=====");
            System.out.println("[1] Customer");
            System.out.println("[2] Staff");
            System.out.println("[3] Admin");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch (choice) {
                case 1:
                    boolean isCustomer = true;
                    while (isCustomer == true) {
                        System.out.println("===CUSTOMER-ACCOUNTS===");
                        System.out.println("Number of customer registered: " + customers.size());
                        System.out.println("[1] View customers");
                        System.out.println("[2] Edit account");
                        System.out.println("[3] Delete account");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch (choice) {
                            case 1:     //CUSTOMER - VIEW ACCOUNTS
                                System.out.println("=====VIEW-ACCOUNTS=====");
                                method.displayCustomer(customers);
                                break;
                            case 2:     //CUSTOMER - EDIT ACCOUNT
                                method.editCustomer(customers);
                                break;
                            case 3:     //CUSTOMER - DELETE ACCOUNT
                                System.out.println("=DELETE-ACCOUNT");

                                break;
                            case 0:
                                isCustomer = false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only");
                        }
                    }
                    break;

                case 2:     //MANAGE ACCOUNTS - STAFF
                    boolean isStaff = true;
                    while (isStaff == true) {
                        System.out.println("===STAFF-ACCOUNTS===");
                        System.out.println("Number of staff registered: " + staffs.size());
                        System.out.println("[1] Add staff");
                        System.out.println("[2] View staffs");
                        System.out.println("[3] Edit staff");
                        System.out.println("[4] Delete staff");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch(choice) {
                            case 1:    //ADD STAFF ACCOUNT
                                String name = null, password = null, email = null;
                                boolean isCreate = true;
                                while(isCreate==true) {
                                    System.out.println("===CREATE-STAFF-ACCOUNT===");
                                    System.out.print("Enter name: ");
                                    name = sc.nextLine();

                                    //Checks if there is already name existed
                                    boolean isDupl = method.checkDupStaff(staffs, name);
                                    if (isDupl == true) {      //If isDupl is true
                                        System.out.println("INVALID: Duplicate name is prohibited");
                                        boolean isCont = method.isContinue();
                                        if (isCont == true) {continue;}
                                        else {
                                            isCreate = false;
                                            break;
                                        }
                                    }

                                    System.out.print("Enter password: ");
                                    password = sc.nextLine();
                                    System.out.print("Enter email: ");
                                    email = sc.nextLine();
                                    if(email.equals("0")) {email=null;}
                                    staffs.add(new Staff(name, password, email));
                                    System.out.println("Account is successfully created!");
                                    break;
                                }

                                break;
                            case 2:     //VIEW STAFF ACCOUNT
                                System.out.println("=====STAFF-ACCOUNT=====");
                                for (int i = 0; i < staffs.size(); i++) {
                                    Staff staff = staffs.get(i);
                                    System.out.print((i + 1) + "Name: " + staff.getName());
                                    System.out.println(" Email: " + staff.getEmail());
                                }
                                break;
                            case 3:     //EDIT ACCOUNT
                                break;
                            case 4:     //DELETE ACCOUNT
                                break;
                            case 0:     //BACK
                                isStaff=false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only");
                        }

                    }
                    break;
                case 3:
                    String password = null;
                    boolean Admin = true;
                    while(Admin==true) {
                        System.out.println("=====ADMINISTRATOR=====");
                        System.out.println("[1] Set username");
                        System.out.println("[2] Set password");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch (choice) {
                            case 1:
                                System.out.print("Enter password to continue: ");
                                password = sc.nextLine();
                                if (password.equals(admin.getPassword()) == false) {
                                    System.out.println("INVALID: Wrong password");
                                    continue;
                                }
                                System.out.print("Enter new admin username: ");
                                admin.setUsername(sc.nextLine());
                                System.out.println("Username is modified successfully!");
                                break Account;
                            case 2:
                                System.out.print("Enter current password to continue: ");
                                password = sc.nextLine();
                                if (password.equals(admin.getPassword()) == false) {
                                    System.out.println("INVALID: Wrong password");
                                    continue;
                                }
                                System.out.print("Enter new admin password: ");
                                admin.setPassword(sc.nextLine());
                                System.out.println("Password is modified successfully!");
                                break;
                            case 0: Admin = false;
                            default:
                        }
                    }
                case 0:
                    System.out.println("Input is 0");
                    break Account;
                default:
                    System.out.println("INVALID: Use indicated number only");
                    boolean isCont = method.isContinue();
                    if (isCont == true) continue;
                    else break Account;
            }
        }
    }
    //================================ROOM-MANAGE
    public void manageRoom(ArrayList<Room> rooms) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("=====MANAGE-ROOMS=====");
            System.out.println("[1] Rooms");
            System.out.println("[2] Amenities");
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

    //================================FOOD-SERVICE
    public void foodService(ArrayList<Menu> menus) {
        boolean isFoods = true;
        while(isFoods==true) {
            System.out.println("=====FOOD-SERVICE=====");
            System.out.println("[1] View food menu");
            System.out.println("[2] Add menu");
            System.out.println("[3] Modify menu");
            System.out.println("[4] Delete menu");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    isFoods=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================FOOD-SERVICE
    public void goInventory(ArrayList<Food> foods) {
        boolean isInventory = true;
        while(isInventory==true) {
            System.out.println("=====INVENTORY=====");
            System.out.println("[1] View food stocks");
            System.out.println("[2] Add stocks");
            System.out.println("[3] Modify stocks");
            System.out.println("[4] Delete stocks");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    isInventory=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================FOOD-SERVICE
    public void goReservations(ArrayList<Reservation> reservations) {
        boolean isReservation = true;
        while(isReservation==true) {
            System.out.println("=====RESERVATIONS=====");
            System.out.println("[1] View reservation");
            System.out.println("[2] Edit reservation");
            System.out.println("[3] Cancel reservation");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    isReservation=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    public void goReports(ArrayList<ArrayList> all) {
        boolean isReports = true;
        while(isReports==true) {
            System.out.println("=====REPORTS=====");
            System.out.println("[1] Accounts");
            System.out.println("[2] Rooms");
            System.out.println("[3] Reservations");
            System.out.println("[4] Sales");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    isReports=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

}

