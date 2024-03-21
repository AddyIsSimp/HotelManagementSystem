package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Entity.*;

public class Methods{

    private Scanner sc = new Scanner(System.in);        //For string inputs
    private Scanner in = new Scanner(System.in);        //For integer/long/double inputs
    String choice = null;

    //CONSTRUCTOR
    public Methods() {
    }

    //====================================USEFUL METHODS============================================

    public void displayCustomer(ArrayList<Customer> customers) {
        for(int i = 0; i<customers.size();i++) {
            Customer customer = customers.get(i);
            System.out.print((i+1) + ". Name: " + customer.getName());
            if(customer.getEmail()!=null) System.out.print(" || Contact: " + customer.getEmail());
            System.out.println();
        }
    }

    public void displayStaff(ArrayList<Staff> staffs) {
        for(int i = 0; i<staffs.size();i++) {
            Staff staff = staffs.get(i);
            System.out.print((i+1) + ". Name: " + staff.getName());
            if(staff.getEmail()!=null) System.out.print(" || Contact: " + staff.getEmail());
            System.out.println();
        }
    }

    public void editCustomer(ArrayList<Customer> customers) {
        String name = null;
        boolean isFound = false;
        Customer customer = null;
        while(true) {
            System.out.println("=====EDIT-ACCOUNT=====");
            System.out.print("Search name: ");
            name = sc.nextLine();

            for (int i = 0; i < customers.size(); i++) {
                Customer temp = customers.get(i);
                if (name.equals(temp.getName()) == true) {
                    customer = temp;
                    isFound = true;
                    break;
                }
            }
            if(isFound = false) {      //When account is not found
                boolean isCont = stateError("Account not found!");
                if (isCont == false) break;
            }

            if(customer!=null) {
                System.out.println("===ACCOUNT-INFO===");
                System.out.println("[1] Name: " + customer.getName());
                System.out.println("[2] Password: " + customer.getPassword());
                if(customer.getEmail()!=null) System.out.println("[3] Email: " + customer.getEmail());
                else System.out.println("[3] No email registered!");
                int choice = inputInt("Select to modify: ");
                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        customer.setName(newName);
                        System.out.println("Modified name successfully!");
                        break;
                    case 2:
                        System.out.println("Enter new password: ");
                        String newPw = sc.nextLine();
                        customer.setPassword(newPw);
                        System.out.println("Modified password successfully!");
                        break;
                    case 3:
                        System.out.println("Enter new email: ");
                        String newEmail = sc.nextLine();
                        customer.setEmail(newEmail);
                        System.out.println("Modified email successfully!");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("INVALID: Use indicated number only!");
                }
            }
            break;
        }
    }

    public void editStaff(ArrayList<Staff> staffs) {
        String name = null;
        boolean isFound = false;
        Staff staff = null;
        while(true) {
            System.out.println("=====EDIT-ACCOUNT=====");
            System.out.println("Search name: ");
            name = sc.nextLine();

            for (int i = 0; i < staffs.size(); i++) {
                Staff temp = staffs.get(i);
                if (name.equals(temp.getName()) == true) {
                    staff = temp;
                    isFound = true;
                    break;
                }
            }
            if (isFound = false) {      //When account is not found
                boolean isCont = stateError("Account not found!");
                if (isCont == false) break;
            }

            System.out.println("==ACCOUNT-INFO==");
            System.out.println("[1] Name: " + staff.getName());
            System.out.println("[2] Password: " + staff.getPassword());
            System.out.println("[3] Email: " + staff.getEmail());
            int choice = inputInt("Select to modify: ");
            switch(choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    staff.setName(newName);
                    System.out.println("Modified name successfully!");
                    break;
                case 2:
                    System.out.println("Enter new password: ");
                    String newPw = sc.nextLine();
                    staff.setPassword(newPw);
                    System.out.println("Modified password successfully!");
                    break;
                case 3:
                    System.out.println("Enter new email: ");
                    String newEmail = sc.nextLine();
                    staff.setEmail(newEmail);
                    System.out.println("Modified email successfully!");
                    break;
                case 0: break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
            break;
        }
    }

    public Person searchPerson(ArrayList<Person> persons, String accountName) {
        String acctName = null;
        String password = null;
        Person person = null;
        boolean isSearch = true;

        while(isSearch==true) {
            acctName = accountName;
            //Checks if Account is found
            boolean isFound = false;
            int pos = 0;
            for (int i = 0; i < persons.size(); i++) {
                person = persons.get(i);
                if (person.getName().equals(acctName)) {      //Checks if the same
                    isFound = true;
                    pos = i;
                    break;
                }
            }
            if (isFound == false) {        //ask if continue or not
                System.out.println("INVALID: Account name not found");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                else break;
            }

            System.out.println("Enter password");
            password = sc.nextLine();

            //Checks if the password is correct in the account name
            boolean isPword = pwordCheckPerson(persons, password, pos);
            if (isPword == false) {
                System.out.println("INVALID: Password is incorrect");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                else break;
            }

            person = persons.get(pos);
        }
        return person;
    }

    public Customer searchCustomer(ArrayList<Customer> customers) {
        String acctName = null;
        String password = null;
        Customer customer = null;
        boolean isSearch = true;

        Search : while(isSearch==true) {
            System.out.print("Search account name: ");
            acctName = sc.nextLine();
            //Checks if Account is found
            boolean isFound = false;
            int pos = 0;
            for (int i = 0; i < customers.size(); i++) {
                customer = customers.get(i);
                if (customer.getName().equals(acctName)) {      //Checks if the same
                    isFound = true;
                    pos = i;
                    break;
                }
            }
            if (isFound == false) {        //ask if continue or not
                System.out.println("INVALID: Account name not found");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                return null;
            }

            System.out.print("Enter password: ");
            password = sc.nextLine();

            //Checks if the password is correct in the account name
            boolean isPword = pwordCheckCustomer(customers, password, pos);
            if (isPword == false) {
                System.out.println("INVALID: Password is incorrect");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                return null;
            }
            customer = customers.get(pos);
        }
        return customer;
    }

    public boolean checkDupPerson(ArrayList<Person> persons, String s) {
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

    boolean checkDupCustomer(ArrayList<Customer> persons, String s) {
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

    boolean checkDupStaff(ArrayList<Staff> persons, String s) {
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

    public boolean nameFinder(ArrayList<Customer> customers, String s) {
        boolean isFound = false;

        for(int i = 0; i<customers.size(); i++) {
            Customer customer = customers.get(i);
            if(customer.getName().equals(s)) {      //Checks if the same
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public boolean pwordCheckPerson(ArrayList<Person> persons, String p, int pos) {    //pos determines the index/postion from the Array
        boolean isFound = false;

        Person person = persons.get(pos);
        if(person.getPassword().equals(p)) isFound = true;

        return isFound;
    }

    public boolean pwordCheckCustomer(ArrayList<Customer> customers, String p, int pos) {    //pos determines the index/postion from the Array
        boolean isFound = false;

        Customer customer = customers.get(pos);
        if(customer.getPassword().equals(p)) isFound = true;

        return isFound;
    }

    public boolean isContinue() {
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

    public boolean stateError(String statement) {      //Used when there is invalid
        System.out.println("INVALID: " + statement);
        boolean isCont = isContinue();
        if(isCont==true) return true;
        else return false;
    }

    public int inputInt(String statement) {
        int choiceNum = -1;
        try {
            System.out.print(statement);
            String choice = sc.nextLine();
            boolean isAllNum = digitChecker(choice);
            choiceNum = StrToInt(choice);
        } catch(InputMismatchException e) {
            System.out.println("INVALID: Input numbers only");
            boolean isCont = isContinue();
            if(isCont==true) inputInt(statement);
            else return -1;
        }
        return choiceNum;
    }

    boolean digitChecker(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)!=true) {
                //System.out.println("There is a string: " + ch);
                return false;
            }
        }
        return true;
    }


    //DATATYPE CONVERTER
    static int StrToInt(String s) {
        int num = 0;
        num = Integer.parseInt(s);
        return num;
    }

    static double StrToDbl(String s) {
        double num = 0;
        num = Double.parseDouble(s);
        return num;
    }

    static String IntToStr(int n) {
        String s = null;
        s = Integer.toString(n);
        return s;
    }

    static String DblToStr(double n) {
        String s = null;
        s = Double.toString(n);
        return s;
    }


}

