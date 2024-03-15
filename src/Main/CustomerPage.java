package Main;

import Entity.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerPage {

    private Scanner sc = new Scanner(System.in);
    private String choice = null;
    private Methods method = new Methods();

    //=============================CUSTOMERS================================
    //REGISTER CUSTOMER
    Customer register(ArrayList<Customer> customersList) {
        Customer newUser = new Customer();
        boolean main = true;
        boolean isCreate = false;       //indicates if successfully created

        String name = null;
        String pw = null;
        String email;

        while(main) {
            System.out.println("=====REGISTER=====");   //Gather credentials
            System.out.print("Enter Name: ");
            name = sc.nextLine();

            boolean dupl = method.checkDupCustomer(customersList, name);
            if(dupl==true) {
                System.out.println("INVALID: Duplicate name is prohibited!");
                boolean isCont = method.isContinue();
                if(isCont==true) {
                    continue;
                }else {
                    main=false;
                    break;
                }
            }

            System.out.print("Enter Password: ");
            pw = sc.nextLine();
            System.out.print("Enter Email: ");
            email = sc.nextLine();

            newUser = new Customer(name, pw, email);
            isCreate = true;
            System.out.println("Successfully created an account");
            break;
        }//register loop

        if(isCreate==true) return newUser;
        return null;
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
                    method.customerAcct = temp;
                    isLogin = true;
                    break;
                }
            }

            //No staff found w/ same un & pw
            if(isLogin==false) {
                System.out.println("No account found!");
                boolean isCont = method.isContinue();
                if(isCont==true) continue;
                else break;
            }
        }//End of isLogin whileloop
        return isLogin;
    }


}
