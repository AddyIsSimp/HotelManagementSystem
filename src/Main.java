import Entity.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Customer> customersList = new ArrayList<>();
    public static ArrayList<Staff> staffsList = new ArrayList<>();

    public static void main(String[] args) {

        //Admin: Username - Admin123 | Password - admin123
        customersList.add(new Customer("Adriano", "Santos"));
        staffsList.add(new Staff("Dave", "Gaga-a", "Gaga-a"));

        Admin admin = new Admin();
        StaffPage staffP = new StaffPage();
        CustomerPage customerP = new CustomerPage();

        Methods method = new Methods();

        Scanner sc = new Scanner(System.in);    //Use for string inputs
        Scanner in = new Scanner(System.in);    //Use for int/double inputs
        String choice = null;
        boolean main = true;

        System.out.println("Bonjour");
        System.out.println("Welcome in");
        System.out.println("HOTEL DELE LUNA");

        while(main) {
            boolean isAdmin = false;
            boolean isStaff = false;
            boolean isCustomer = false;

            System.out.println("=====SELECT-USER=====");
            System.out.println("[1] ADMIN");
            System.out.println("[2] STAFF");
            System.out.println("[3] CUSTOMER");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    isAdmin = method.loginAdmin();
                    if(isAdmin==true) admin.AdminPage();
                    else continue;
                    break;
                case "2":
                    isStaff = method.loginStaff(staffsList);
                    if(isStaff==true) staffP.StaffPage();
                    break;
                case "3":
                    isCustomer = method.loginCustomer(customersList);
                    if(isCustomer==true) customerP.CustomerPage();
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    continue;
            }
        }
    }
}
