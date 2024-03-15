package Main;

import Entity.Customer;
import Entity.Staff;
import Main.Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
                        System.out.println("[1] View accounts");
                        System.out.println("[2] Edit account");
                        System.out.println("[3] Delete account");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch (choice) {
                            case 1:     //CUSTOMER - VIEW ACCOUNTS
                                System.out.println("=VIEW-ACCOUNTS=");
                                for (int i = 0; i < customers.size(); i++) {
                                    Customer customer = customers.get(i);
                                    System.out.print((i + 1) + ". Name: " + customer.getName());
                                    System.out.println(" Email: " + customer.getEmail());
                                }
                                break;
                            case 2:     //CUSTOMER - EDIT ACCOUNT
                                boolean editCustomer = true;
                                while (editCustomer==true) {
                                    String acctName = null;
                                    String password = null;
                                    Customer customer = null;

//                                    System.out.println("=====EDIT-ACCOUNT=====");
//                                    System.out.print("Search account name to edit:");
//                                    acctName = sc.nextLine();
//
//                                    //Checks if Account is found
//                                    boolean isFound = false;
//                                    int pos = 0;
//                                    for (int i = 0; i < customers.size(); i++) {
//                                        customer = customers.get(i);
//                                        if (customer.getName().equals(acctName)) {      //Checks if the same
//                                            isFound = true;
//                                            pos = i;
//                                            break;
//                                        }
//                                    }
//                                    if (isFound == false) {        //ask if continue or not
//                                        System.out.println("INVALID: Account name not found");
//                                        boolean isCont = method.isContinue();
//                                        if (isCont == true) continue;
//                                        else break;
//                                    }
//
//                                    System.out.println("Enter password");
//                                    password = sc.nextLine();
//
//                                    //Checks if the password is correct in the account name
//                                    boolean isPword = method.pwordChecker(customers, password, pos);
//                                    if (isPword == false) {
//                                        System.out.println("INVALID: Password is incorrect");
//                                        boolean isCont = method.isContinue();
//                                        if (isCont == true) continue;
//                                        else break;
//                                    }
//
//                                    customer = customers.get(pos);

                                    System.out.println("=====EDIT-ACCOUNT=====");
                                    Customer findCustomer = method.searchCustomer(customers);

                                    if(findCustomer==null) {        //Customer is not found
                                        System.out.println("Customer is not found!");
                                        break;
                                    }else {customer = findCustomer;}

                                    //Finds the customer name in the list
                                    System.out.println("==ACCOUNT-INFO==");
                                    System.out.println("[1] Name: " + customer.getName());
                                    System.out.println("[2] Password: " + customer.getPassword());
                                    System.out.println("[3] Email: " + customer.getEmail());
                                    System.out.print("Select number to modify: ");

                                }
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
                        System.out.println("[1] Add accounts");
                        System.out.println("[2] View accounts");
                        System.out.println("[3] Edit account");
                        System.out.println("[4] Delete account");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch(choice) {
                            case 1:    //ADD STAFF ACCOUNT
                                String name = null, password = null, email = null;
                                boolean isCreate = true;
                                while(isCreate==true) {
                                    System.out.println("===CREATE-STAFF-ACCOUNT===");
                                    System.out.println("Enter name: ");
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

                                    System.out.println("Enter password: ");
                                    password = sc.nextLine();
                                    System.out.println("Enter email: ");
                                    email = sc.nextLine();
                                    staffs.add(new Staff(name, password, email));
                                    System.out.println("Account is successfully created!");
                                }

                                System.out.println("Enter password");
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

}

