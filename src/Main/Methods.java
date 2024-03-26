package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.*;
import Foods.Food;
import Menus.Menu;
import Rooms.*;

public class Methods{

    private Scanner sc = new Scanner(System.in);        //For string inputs
    private Scanner in = new Scanner(System.in);        //For integer/long/double inputs
    String choice = null;
    int pick = 0;

    //CONSTRUCTOR
    public Methods() {
    }

    //====================================USEFUL METHODS============================================

    public void displayAllRoom(ArrayList<Room> rooms) {     //Display all rooms despite occupied or not
        sortRooms(rooms);
        if(rooms.size()!=0) {       //If there is room existing
            System.out.println("===ROOMS-LIST===");
            for (int i = 0; i < rooms.size(); i++) {
                Room room = rooms.get(i);
                System.out.println((i + 1) + ". Room Number: " + room.getRoomNum() + " || Room Type: " + room.getRoomType()
                        + " || Occupied: " + room.getIsOccupied() + " || Disabled: " + room.getIsDisabled());
            }
        }else System.out.println("\nThere is no room found\n");
    }

    public ArrayList<Integer> getAvailRoomNum(ArrayList<Room> rooms) {  //Returns a list of roomNumber existed
        ArrayList<Integer> roomNums = new ArrayList<>();
        sortRoomByNum(rooms);
        if(rooms.size()!=0) {
            for (int i = 0; i < rooms.size(); i++) {
                roomNums.add(rooms.get(i).getRoomNum());
            }
        }
        return roomNums;
    }

    public void displayAvailRoomNum(ArrayList<Room> rooms) {       //Display the list of room number
        sortRoomByNum(rooms);
        ArrayList<Integer> roomNums = getAvailRoomNum(rooms);
        if(roomNums.size()!=0) {
            for(int i = 0; i<roomNums.size(); i++) {
                System.out.print(roomNums.get(i));
                if(i<rooms.size()-1) System.out.print(" | ");;
            }
            System.out.println("");
        }
    }

    public boolean isRoomNumExist(ArrayList<Room> rooms, int roomNum) {     //Returns a boolean if room exist
        boolean isExist = false;
        for(int i = 0; i< rooms.size(); i++) {
            if(rooms.get(i).getRoomNum()==roomNum) isExist = true;
        }
        return isExist;
    }

    public int isRoomNumReturnIndex(ArrayList<Room> rooms, int roomNum) {   //Returns the index of room with room number specified
        int index = -1;
        for(int i = 0; i< rooms.size(); i++) {
            if(rooms.get(i).getRoomNum()==roomNum) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void displayRoomCategory(ArrayList<Room> rooms) {    //Display all room in category
        ArrayList<SingleRoom> snRooms = new ArrayList<SingleRoom>();
        ArrayList<CoupleRoom> cpRooms = new ArrayList<CoupleRoom>();
        ArrayList<FamilyRoom> fmRooms = new ArrayList<FamilyRoom>();
        ArrayList<VIPRoom> vpRooms = new ArrayList<VIPRoom>();

        sortRooms(rooms);

        for (int i = 0; i< rooms.size(); i++) {     // To separate each subclasses
            if(rooms.get(i).getRoomType().equals("Single")==true) {snRooms.add((SingleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Couple")==true) {cpRooms.add((CoupleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Family")==true) fmRooms.add((FamilyRoom) rooms.get(i));
            else if(rooms.get(i).getRoomType().equals("VIP")==true) vpRooms.add((VIPRoom) rooms.get(i));
        }

        if(rooms.size()!=0) System.out.println("===ROOMS-IN-CATEGORY===");
        else System.out.println("\nThere is no room found!\n");

        if(snRooms.size()!=0) System.out.println("===SINGLE-ROOMS===");
        for (SingleRoom snRoom: snRooms) {
            System.out.println("Room Number: " + snRoom.getRoomNum() + " || Occupied: " + snRoom.getIsOccupied() +
                            " || Disabled: " + snRoom.getIsDisabled());
        }
        if(cpRooms.size()!=0) System.out.println("===COUPLE-ROOMS===");
        for (CoupleRoom cpRoom: cpRooms) {
            System.out.println("Room Number: " + cpRoom.getRoomNum() + " || Occupied: " + cpRoom.getIsOccupied() +
                    " || Disabled: " + cpRoom.getIsDisabled());
        }
        if(fmRooms.size()!=0) System.out.println("===FAMILY-ROOMS===");
        for (FamilyRoom fmRoom: fmRooms) {
            System.out.println("Room Number: " + fmRoom.getRoomNum() + " || Occupied: " + fmRoom.getIsOccupied() +
                    " || Disabled: " + fmRoom.getIsDisabled());
        }
        if(vpRooms.size()!=0) System.out.println("===VIP-ROOMS===");
        for (VIPRoom vpRoom: vpRooms) {
            System.out.println("Room Number: " + vpRoom.getRoomNum() + " || Occupied: " + vpRoom.getIsOccupied() +
                    " || Disabled: " + vpRoom.getIsDisabled());
        }

        isGoBack();
    }

    public void displayRoomByNum(ArrayList<Room> rooms) {       //Display room by sorted room number
        sortRoomByNum(rooms);
        for(Room room : rooms) {
            System.out.println("Room Number: " + room.getRoomNum() +
                    " || Room Type: " + room.getRoomType() +
                    " || Occupied: " + room.getIsOccupied() +
                    " || Disabled: " + room.getIsDisabled());
        }
    }

    public void displayMenus(ArrayList<Menu> menus) {
        System.out.println("=====MENU-LIST=====");
        for(int i = 0; i<menus.size(); i++) {        //Display all menu created
            Menu menu = menus.get(i);
            System.out.println((i+1) + " Name: " + menu.getMenuName()
                    + "|| Food: " + menu.getMainDish());
            if(menu.getSideDish() !=null) System.out.println(" , " + menu.getSideDish());
            if(menu.getDrinks()!=null) System.out.println(" , " + menu.getDrinks());
            if(menu.getDessert()!=null) System.out.println(" , " + menu.getDessert());
            System.out.println(" || Price: " + menu.getTotalPrice());
        }
    }

    public Menu checkMenuExist(ArrayList<Menu> menus, String menuName) {    //Returns a menu if that menuname existed in list
        Menu foundMenu = null;
        for(int i = 0; i<menus.size(); i++) {
            Menu menu = menus.get(i);
            if(menu.getMenuName().equals(menuName)==true) {
                foundMenu = menu;
                break;
            }
        }
        return foundMenu;
    }

    public void displayCustomer(ArrayList<Customer> customers) {    //Display all customer
        for(int i = 0; i<customers.size();i++) {
            Customer customer = customers.get(i);
            System.out.print((i+1) + ". Name: " + customer.getName());
            if(customer.getEmail()!=null) System.out.print(" || Contact: " + customer.getEmail());
            System.out.println();
        }
    }

    public void displayStaff(ArrayList<Staff> staffs) {             //Display all staff
        for(int i = 0; i<staffs.size();i++) {
            Staff staff = staffs.get(i);
            System.out.print((i+1) + ". Name: " + staff.getName());
            if(staff.getEmail()!=null) System.out.print(" || Contact: " + staff.getEmail());
            System.out.println();
        }
    }

    public void editCustomer(ArrayList<Customer> customers) {       //Edit a customer
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
            if(isFound==false) {      //When account is not found
                boolean isCont = stateError("Account not found!");
                if (isCont == false) break;
                else continue;
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
                        System.out.print("Enter new password: ");
                        String newPw = sc.nextLine();
                        customer.setPassword(newPw);
                        System.out.println("Modified password successfully!");
                        break;
                    case 3:
                        System.out.print("Enter new email: ");
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

    public void editStaff(ArrayList<Staff> staffs) {        //Edit a staff account
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

    public int isCustomerExistReturnIndex(ArrayList<Customer> customers, String name) {     //Returns an int index if customer exist
        int index = -1;
        for(int i = 0; i< customers.size(); i++) {
            if(customers.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int isStaffExistReturnIndex(ArrayList<Staff> staffs, String name) {     //Returns a boolean if room exist
        int index = -1;
        for(int i = 0; i< staffs.size(); i++) {
            if(staffs.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Person searchPerson(ArrayList<Person> persons, String accountName) {     //Returns the Person object if person is found
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

    public Customer searchCustomer(ArrayList<Customer> customers) {     //Returns the Customer object if its found
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

    public boolean checkDupPerson(ArrayList<Person> persons, String s) {    //Returns the boolean whether there is duplicate in person name
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

    public boolean checkDupCustomer(ArrayList<Customer> persons, String s) {       //Returns the boolean whether there is duplicate in customer name
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

    public boolean checkDupStaff(ArrayList<Staff> persons, String s) {         //Returns the boolean whether there is duplicate in staff name
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

    public boolean nameFinder(ArrayList<Customer> customers, String s) {       //Returns a boolean if customer name is found
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

    public boolean isContinue() {       //Return boolean if still want to continue or not
        String choice = null;
        while(true) {
            System.out.println("Do you want to continue? [1]Yes/[2]No");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
            }
        }
    }

    public boolean isGoBack() {     //This is to ask user if go back for avoiding print issue
        while(true) {
            System.out.print("Press 0 to go back: ");
            choice = sc.nextLine();

            switch (choice) {
                case "0":
                    return true;
                default:
                    System.out.println("INVALID: Press 0 key only");
            }
        }
    }

    public boolean stateError(String statement) {      //Used when there is invalid
        System.out.println("INVALID: " + statement);
        boolean isCont = isContinue();
        if(isCont==true) return true;
        else return false;
    }

    public int inputInt(String statement) { //For taking an integer input
        int choiceNum = -1;
        try {
            System.out.print(statement);
            String choice = sc.nextLine();
            boolean isAllNum = digitChecker(choice);
            choiceNum = StrToInt(choice);
        } catch(Exception e) {
            System.out.println("INVALID: Input numbers only!");
            return -1;
        }
        return choiceNum;
    }

    public int inputInt() {
        int choiceNum = -1;
        try {
            String choice = sc.nextLine();
            boolean isAllNum = digitChecker(choice);
            choiceNum = StrToInt(choice);
        } catch(Exception e) {
            return -1;
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

    void sortRooms(ArrayList<Room> rooms) {
        ArrayList<SingleRoom> snRooms = new ArrayList<SingleRoom>();
        ArrayList<CoupleRoom> cpRooms = new ArrayList<CoupleRoom>();
        ArrayList<FamilyRoom> fmRooms = new ArrayList<FamilyRoom>();
        ArrayList<VIPRoom> vpRooms = new ArrayList<VIPRoom>();

        for (int i = 0; i< rooms.size(); i++) {     // To separate each subclasses
            if(rooms.get(i).getRoomType().equals("Single")==true) {snRooms.add((SingleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Couple")==true) {cpRooms.add((CoupleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Family")==true) fmRooms.add((FamilyRoom) rooms.get(i));
            else if(rooms.get(i).getRoomType().equals("VIP")==true) vpRooms.add((VIPRoom) rooms.get(i));
        }

        rooms.removeAll(rooms);

        for (int i = 0; i < snRooms.size(); i++) {         //Sort the singleRoomNum by roomNums
            for (int j = i + 1; j < snRooms.size(); j++) {
                SingleRoom snRoom = snRooms.get(i);
                SingleRoom snRoom2 = snRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    snRooms.set(i, snRoom2);
                    snRooms.set(j, snRoom);
                }
            }
        }

        for(int i = 0; i<cpRooms.size(); i++) {         //Sort the coupleRoomNum by roomNums
            for(int j = i+1; j<cpRooms.size(); j++) {
                CoupleRoom snRoom = cpRooms.get(i);
                CoupleRoom snRoom2 = cpRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    cpRooms.set(i, snRoom2);
                    cpRooms.set(j, snRoom);
                }
            }
        }

        for(int i = 0; i<fmRooms.size(); i++) {         //Sort the familyRoomNum by roomNums
            for(int j = i+1; j<fmRooms.size(); j++) {
                FamilyRoom snRoom = fmRooms.get(i);
                FamilyRoom snRoom2 = fmRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    fmRooms.set(i, snRoom2);
                    fmRooms.set(j, snRoom);
                }
            }
        }

        for(int i = 0; i<vpRooms.size(); i++) {         //Sort the familyRoomNum by roomNums
            for(int j = i+1; j<vpRooms.size(); j++) {
                VIPRoom snRoom = vpRooms.get(i);
                VIPRoom snRoom2 = vpRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    vpRooms.set(i, snRoom2);
                    vpRooms.set(j, snRoom);
                }
            }
        }

        for(Room room: snRooms) {rooms.add(room);}
        for(Room room: cpRooms) {rooms.add(room);}
        for(Room room: fmRooms) {rooms.add(room);}
        for(Room room: vpRooms) {rooms.add(room);}


//        if(rooms!=null) {
//            for (Room room : rooms) {
//                System.out.println("Room Num: " + room.getRoomNum() + " || Room Type: " + room.getRoomType());
//            }
//        }

    }

    public void sortRoomByNum(ArrayList<Room> rooms) {
        for(int i = 0; i<rooms.size(); i++) {
            for(int j = i+1; j<rooms.size(); j++) {
                Room room1= rooms.get(i);
                Room room2 = rooms.get(j);
                if(room1.getRoomNum()>room2.getRoomNum()) {
                    rooms.set(i, room2);
                    rooms.set(j, room1);
                }
            }
        }
    }


    //DATATYPE CONVERTER
    public int StrToInt(String s) {
        int num = 0;
        num = Integer.parseInt(s);
        return num;
    }

    public double StrToDbl(String s) {
        double num = 0;
        num = Double.parseDouble(s);
        return num;
    }

    public String IntToStr(int n) {
        String s = null;
        s = Integer.toString(n);
        return s;
    }

    public String DblToStr(double n) {
        String s = null;
        s = Double.toString(n);
        return s;
    }

    //===============================NEW ADDED METHODS===========================
    public void showMainDish(ArrayList<Food> foods) {

    }

    //Create a method to search for duplicate reservation in RoomsList and reservations

    public void setReservationDate(Reservation reservation, ArrayList<Reservation> reservations) {
        Calendar start = null;
        Calendar end = null;
        System.out.println("=====SET-RESERVATION=====");
        while(true) {
            System.out.print("==START-DATE==");
            System.out.println("Date e.g.(31): ");

            //start.set();
        }

    }

    public Date inputDate() {
        boolean isInput = true;
        Date date = null;
        while(isInput==true) {
            System.out.print("Date: ");
            int day = inputInt();

            if(day>31 || day<1) {     //Scans if date is proper
                System.out.println("INVALID: Date is not tarung");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }

            System.out.print("Month: ");
            int month = inputInt();

            if(month>12 || month<1) {     //Scans if date is proper
                System.out.println("INVALID: Month is not tarung");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }

            System.out.print("Year: ");
            int year = inputInt();

            if(year<2024) {     //Scans if date is proper
                System.out.println("INVALID: Year is nalapas");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }


            date.setDate(day);
        }
        return date;
    }


}

