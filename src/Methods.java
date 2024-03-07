import java.util.ArrayList;
import java.util.Scanner;
import Entity.*;

public class Methods{

    Staff staffAcct;
    Customer customerAcct;
    Scanner sc = new Scanner(System.in);
    String choice = null;

    Methods() {

    }

//REGISTER CUSTOMER
    Person register(ArrayList<Customer> customersList) {
        Person newUser = new Person();
        boolean main = true;
        String name = null;
        String pw = null;
        String email;

        while(main) {
            System.out.println("=====REGISTER=====");   //Gather credentials
            System.out.println("Enter Name: ");
            name = sc.nextLine();

            boolean dupl = checkDuplicate(customersList, name);
            if(dupl==true) {
                System.out.println("INVALID: Duplicate name is prohibited!");
                boolean isCont = isContinue();
                if(isCont==true) {
                    continue;
                }else {
                    main=false;
                    break;
                }
            }

            System.out.println("Enter Password: ");
            pw = sc.nextLine();
            System.out.println("Enter Email: ");
            email = sc.nextLine();

            newUser = new Customer(name, pw, email);

        }//register loop

        return newUser;
    }


//LOGIN CUSTOMER
    boolean loginCustomer(ArrayList<Customer> customersList) {
        String un = null;
        String pw = null;
        boolean isLogin = false;

        while(isLogin==false) {
            System.out.println("=====CUSTOMER=====");
            System.out.print("Username: ");
            un = sc.nextLine();
            System.out.print("Password: ");
            pw = sc.nextLine();

            //Checks the list of Staff if there is same name and password
            for(int i = 0; i<customersList.size(); i++) {
                Customer temp = customersList.get(i);
                if(temp.getName().equals(un) && temp.getPassword().equals(pw)) {
                    customerAcct = temp;
                    isLogin = true;
                    break;
                }
            }

            //No staff found w/ same un & pw
            if(isLogin==false) {
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }
        }//End of isLogin whileloop
        return isLogin;
    }


//LOGIN STAFF
    boolean loginStaff(ArrayList<Staff> staffsList) {
        String un = null;
        String pw = null;
        boolean isLogin = false;

        while(true) {
            System.out.println("=====STAFF=====");
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
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else return false;
            }
        }//End of isLogin whileloop
    }//end of loginStaff method


    //LOGIN ADMIN
    boolean loginAdmin() {
        Admin admin = new Admin();
        String un = null;
        String pw = null;

        while(true) {
            System.out.println("=====ADMIN-LOGIN=====");
            System.out.print("Username: ");
            un = sc.nextLine();
            System.out.print("Password: ");
            pw = sc.nextLine();

            //Checks if the credentials is correct
            if (un.equals(admin.getUsername()) && pw.equals(admin.getPassword())) {
                System.out.println("Welcome back commander!");
                return true;
            } else {
                System.out.println("Wrong username or password");
                boolean isCont = isContinue();
                if (isCont == true) loginAdmin();
                else return false;
            }
        }
    }


    //====================================USEFUL METHODS============================================
    boolean checkDuplicate(ArrayList<Customer> persons, String s) {
        boolean isDuplicate = false;

        for(int i = 0; i<persons.size(); i++) {
            Person temp = persons.get(i);
            if(temp.getName().equals(s)) {
                isDuplicate=true;
                break;
            }
        }

        return isDuplicate;
    }

    boolean isContinue() {
        while(true) {
            System.out.println("Do you want to continue? [1]Yes/[2]No");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }
}

