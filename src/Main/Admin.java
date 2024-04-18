package Main;

import Entity.*;
import Rooms.*;
import Foods.*;
import Amenity.*;
import Rooms.Reservation;
import Transaction.ReserveTransact;
import Transaction.Transact;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

    private String userName = "Admin123";
    private String password = "admin123";
    private Methods method = new Methods();

    private String text = null;
    private int choice = 0;
    private String pick = null;
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
            System.out.println("\n=====ADMIN-LOGIN=====");
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
            System.out.println("\n=====MANAGE-ACCOUNTS=====");
            System.out.println("[1] Customer");
            System.out.println("[2] Staff");
            System.out.println("[3] Admin");
            System.out.println("[0] Back");
            System.out.print("Enter choice: ");
            choice = method.inputInt();

            switch (choice) {
                case 1:
                    boolean isCustomer = true;
                    while (isCustomer == true) {
                        System.out.println("=====CUSTOMER-ACCOUNTS=====");
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
                                method.isGoBack();
                                break;
                            case 2:     //CUSTOMER - EDIT ACCOUNT
                                method.editCustomer(customers);
                                break;
                            case 3:     //CUSTOMER - DELETE ACCOUNT
                                while(true) {
                                    System.out.println("=====DELETE-ACCOUNT=====");
                                    System.out.print("Enter name to delete: ");
                                    String nameDel = sc.nextLine();

                                    int index = method.isCustomerExistReturnIndex(customers, nameDel);
                                    if (index == -1) {     //Account not found
                                        boolean isCont = method.stateError("Account not found!");
                                        if (isCont == true) continue;
                                        else break;
                                    }

                                    //If customer is Exist delete then
                                    customers.remove(index);
                                    System.out.println("Customer account is deleted successfully!");
                                    break;
                                }
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
                                method.isGoBack();
                                break;
                            case 3:     //EDIT ACCOUNT
                                boolean isFound = false;
                                boolean editStaff = true;
                                Staff staff = null;
                                while(editStaff==true) {
                                    System.out.println("=====EDIT-ACCOUNT=====");
                                    System.out.print("Search name: ");
                                    name = sc.nextLine();

                                    for (int i = 0; i < staffs.size(); i++) {
                                        Staff temp = staffs.get(i);
                                        if (name.equals(temp.getName()) == true) {
                                            staff = temp;
                                            isFound = true;
                                            break;
                                        }
                                    }
                                    if(isFound==false) {      //When account is not found
                                        boolean isCont = method.stateError("Account not found!");
                                        if (isCont == false) break;
                                        else continue;
                                    }

                                    if(staff!=null) {
                                        System.out.println("===ACCOUNT-INFO===");
                                        System.out.println("[1] Name: " + staff.getName());
                                        System.out.println("[2] Password: " + staff.getPassword());
                                        if(staff.getEmail()!=null) System.out.println("[3] Email: " + staff.getEmail());
                                        else System.out.println("[3] No email registered!");
                                        choice = method.inputInt("Select to modify: ");
                                        switch (choice) {
                                            case 1:
                                                System.out.print("Enter new name: ");
                                                String newName = sc.nextLine();
                                                staff.setName(newName);
                                                System.out.println("Modified name successfully!");
                                                break;
                                            case 2:
                                                System.out.print("Enter new password: ");
                                                String newPw = sc.nextLine();
                                                staff.setPassword(newPw);
                                                System.out.println("Modified password successfully!");
                                                break;
                                            case 3:
                                                System.out.print("Enter new email: ");
                                                String newEmail = sc.nextLine();
                                                staff.setEmail(newEmail);
                                                System.out.println("Modified email successfully!");
                                                break;
                                            case 0:
                                                editStaff=true;
                                                break;
                                            default:
                                                System.out.println("INVALID: Use indicated number only!");
                                        }
                                    }
                                    break;
                                }
                                break;
                            case 4:     //DELETE ACCOUNT
                                while(true) {
                                    System.out.println("=====DELETE-ACCOUNT=====");
                                    System.out.print("Enter name to delete: ");
                                    String nameDel = sc.nextLine();

                                    int index = method.isStaffExistReturnIndex(staffs, nameDel);
                                    if (index == -1) {     //Account not found
                                        boolean isCont = method.stateError("Account not found!");
                                        if (isCont == true) continue;
                                        else break;
                                    }

                                    //If customer is Exist delete then
                                    staffs.remove(index);
                                    System.out.println("Staff account is deleted successfully!");
                                    break;
                                }
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
    public void manageRoom(ArrayList<Room> rooms, ArrayList<Amenity> amenities) {
        boolean isManage = true;
        while(isManage==true) {
            int roomNum = 0;
            int roomIndex = 0;

            System.out.println("\n=====MANAGE-ROOMS=====");
            System.out.println("[1] Rooms");
            System.out.println("[2] Amenities");
            System.out.println("[3] Set duration limit");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    boolean isRoom = true;
                    while(isRoom==true) {
                        System.out.println("\n=====ROOMS=====");
                        System.out.println("[1] View rooms");
                        System.out.println("[2] Add room");
                        System.out.println("[3] Edit room");
                        System.out.println("[4] Edit room price");
                        System.out.println("[5] Delete room");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch(choice) {
                            case 1:     //View room
                                boolean isView = true;
                                while(isView==true) {
                                    System.out.println("\n===VIEW-ROOMS===");
                                    System.out.println("[1] Display All");
                                    System.out.println("[2] In category");
                                    System.out.println("[0] Back");
                                    choice = method.inputInt("Enter choice: ");
                                    if(choice==-1) continue;

                                    switch(choice) {
                                        case 1:
                                            method.displayAllRoom(rooms);
                                            method.isGoBack();
                                            break;
                                        case 2:
                                            method.displayRoomCategory(rooms);
                                            break;
                                        case 0:
                                            isView = false;
                                            break;
                                        default:
                                            System.out.println("INVALID: Use indicated number only!");
                                    }
                                }
                                break;
                            case 2:     //Add room
                                boolean isAdd  = true;
                                while(isAdd==true) {
                                    //Set the room number of new room
                                    System.out.println("\n=====ADD-ROOM=====");
                                    System.out.println("Existing rooms: ");
                                    method.displayAvailRoomNum(rooms);
                                    roomNum = method.inputInt("Set the room number: ");
                                    if(roomNum==-1) continue;

                                    if(method.isRoomNumExist(rooms, roomNum)==true) { //Restricts duplicate roomNum
                                        System.out.println("INVALID: Duplicate room number is prohibited");
                                        boolean isCont = method.isContinue();
                                        if(isCont==true) continue;
                                        else break;
                                    }

                                    //Set the type of room
                                    System.out.println("\nSelect type of room");
                                    System.out.println("[1]Single Room");
                                    System.out.println("[2]Couple Room");
                                    System.out.println("[3]Family Room");
                                    System.out.println("[4]VIP Room");
                                    choice = method.inputInt("Enter choice: ");
                                    if(choice==-1) continue;

                                switch(choice) {
                                    case 1:
                                        rooms.add(new SingleRoom(roomNum));
                                        System.out.println("Successfully added Single Room " + roomNum);
                                        isAdd=false;
                                        break;
                                    case 2:
                                        rooms.add(new CoupleRoom(roomNum));
                                        System.out.println("Successfully added Couple Room " + roomNum);
                                        isAdd=false;
                                        break;
                                    case 3:
                                        rooms.add(new FamilyRoom(roomNum));
                                        System.out.println("Successfully added Family Room " + roomNum);
                                        isAdd=false;
                                        break;
                                    case 4:
                                        rooms.add(new VIPRoom(roomNum));
                                        System.out.println("Successfully added VIP Room " + roomNum);
                                        isAdd=false;
                                        break;
                                    case 0:
                                        isAdd=false;
                                        break;
                                    default:
                                        System.out.println("INVALID: Use indicated number only");
                                }
                                }
                                break;
                            case 3:     //Edit room
                                //EDIT ROOMS - enable or disable the room
                                boolean isEdit = true;
                                while(isEdit==true) {
                                    System.out.println("\n=====EDIT-ROOM=====");
                                    method.displayAllRoom(rooms);
                                    roomNum = method.inputInt("Select room number to edit: ");
                                    roomIndex = method.isRoomNumReturnIndex(rooms, roomNum);    //get the index of rooms

                                    if(method.isRoomNumExist(rooms, roomNum)==true) {  //if roomNum exist
                                        Room roomEdit = rooms.get(roomIndex);

                                        if (roomEdit.getIsDisabled() == true) {   //Enable if isDisabled is true
                                            boolean isDisable = true;

                                            while (isDisable == true) {
                                                choice = method.inputInt("\nDo you want to enable Room " + roomNum +
                                                        "? [1]Yes/ [2] No");
                                                System.out.println("");
                                                switch (choice) {
                                                    case 1:
                                                        roomEdit.setIsDisabled(false);
                                                        System.out.println("Room is enabled successfully!");
                                                        isDisable = false;
                                                        isEdit = false;
                                                        break;
                                                    case 2:
                                                        isDisable = false;
                                                        isEdit = false;
                                                        break;
                                                    default:
                                                        System.out.println("INVALID: Use indicated number only!");
                                                }
                                            }

                                        } else if (roomEdit.getIsDisabled() == false) {  //Disable if isDisabled is false
                                            boolean isDisable = true;
                                            while (isDisable == true) {
                                                System.out.println("\nDo you want to disable Room" + roomNum + "? [1]Yes/ [2] No");
                                                pick = sc.nextLine();

                                                switch (pick) {
                                                    case "1":
                                                        roomEdit.setIsDisabled(true);
                                                        System.out.println("Room is disabled successfully!");
                                                        isEdit = false;
                                                        isDisable = false;
                                                        break;
                                                    case "2":
                                                        isEdit = false;
                                                        isDisable = false;
                                                        break;
                                                    default:
                                                        System.out.println("INVALID: Use indicated number only!");
                                                }
                                            }//End of disable loop
                                        }//else if roomEdit
                                    }//roomNum is exist in rooms list
                                    else {
                                        System.out.println("INVALID: There is no room with the number specified!");
                                        boolean isCont = method.isContinue();
                                        if (isCont == true) continue;
                                        else break;
                                    }//end of roomExist condition
                                }//end of isEdit loop
                                break;
                            case 4:     //EDIT ROOM PRICE
                                boolean isEditPrice = true;
                                while(isEditPrice==true) {
                                    ArrayList<Room> roomsType = new ArrayList<>();
                                    double rate = 0;
                                    System.out.println("\n=====SET-ROOM-PRICE=====");
                                    System.out.println("[1] Single Room");
                                    System.out.println("[2] Couple Room");
                                    System.out.println("[3] Family Room");
                                    System.out.println("[4] VIP Room");
                                    System.out.print("Enter choice: ");
                                    choice = method.inputInt();

                                    switch(choice) {
                                        case 0:
                                            isEditPrice = false;
                                            break;
                                        case 1:
                                            System.out.print("\nSet single room price: ");
                                            rate = method.inputDouble();
                                            if(rate<0) {
                                                System.out.println("INVALID: Price should be real number");
                                                continue;
                                            }
                                            roomsType = method.getRoomsType(rooms, "Single");
                                            method.setPriceRooms(roomsType, rate);
                                            System.out.println("Price of single rooms are set to " + rate);
                                            isEditPrice=false;
                                            break;
                                        case 2:
                                            System.out.print("\nSet couple room price: ");
                                            rate = method.inputDouble();
                                            if(rate<0) {
                                                System.out.println("INVALID: Price should be real number");
                                                continue;
                                            }
                                            roomsType = method.getRoomsType(rooms, "Couple");
                                            method.setPriceRooms(roomsType, rate);
                                            System.out.println("Price of couple rooms are set to " + rate);
                                            isEditPrice=false;
                                            break;
                                        case 3:
                                            System.out.print("\nSet family room price: ");
                                            rate = method.inputDouble();
                                            if(rate<0) {
                                                System.out.println("INVALID: Price should be real number");
                                                continue;
                                            }
                                            roomsType = method.getRoomsType(rooms, "Family");
                                            method.setPriceRooms(roomsType, rate);
                                            System.out.println("Price of family rooms are set to " + rate);
                                            isEditPrice=false;
                                            break;
                                        case 4:
                                            System.out.print("\nSet VIP room price: ");
                                            rate = method.inputDouble();
                                            if(rate<0) {
                                                System.out.println("INVALID: Price should be real number");
                                                continue;
                                            }
                                            roomsType = method.getRoomsType(rooms, "VIP");
                                            method.setPriceRooms(roomsType, rate);
                                            System.out.println("Price of VIP rooms are set to " + rate);
                                            isEditPrice=false;
                                            break;
                                        default:
                                            System.out.println("INVALID: Use indicated number only");
                                    }

                                }
                                break;
                            case 5:     //DELETE ROOM
                                boolean isDelete = true;

                                while(isDelete==true) {
                                    System.out.println("\n=====DELETE-ROOM=====");
                                    method.displayAllRoom(rooms);
                                    roomNum = method.inputInt("Select room number to delete: ");

                                    if(roomNum==-1) {break;}
                                    roomIndex = method.isRoomNumReturnIndex(rooms, roomNum);    //get the index of roomNum

                                    if(method.isRoomNumExist(rooms, roomNum)==true) {    //Checks whether the roomNum exist
                                        rooms.remove(roomIndex);
                                        System.out.println("Deleted Room " + roomNum + " successfully!");
                                    }else {
                                        System.out.println("INVALID: There is no room with the number specified!");
                                        boolean isCont = method.isContinue();
                                        if (isCont == true) continue;
                                        else break;
                                    }
                                }//end of isDelete loop
                                break;
                            case 0:
                                isRoom = false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only!");
                        }
                    }
                    break;
                case 2:     //AMENITIES EDIT
                    boolean isAmenity = true;
                    while(isAmenity==true) {
                        System.out.println("\n=====AMENITIES=====");
                        System.out.println("[1] Add amenity");
                        System.out.println("[2] View amenities");
                        System.out.println("[3] Edit amenity");
                        System.out.println("[4] Edit reservation cost");
                        System.out.println("[5] Delete amenity");
                        System.out.println("[0] Back");
                        System.out.print("Enter choice: ");
                        choice = method.inputInt();

                        switch(choice) {
                            case 1:     //Add amenity
                                boolean isAdd = true;
                                while(isAdd==true) {
                                    System.out.println("\n=====ADD-AMENITY=====");
                                    System.out.println("[1] Karaoke");
                                    System.out.println("[2] Reception Hall");
                                    System.out.println("[3] Pool");
                                    System.out.println("[4] Game Room");
                                    System.out.print("Select type of amenity: ");
                                    choice = method.inputInt();

                                    switch (choice) {
                                        case 1:
                                            method.addAmenity(amenities, "Karaoke");
                                            Amenity amenityLast = amenities.get(amenities.size()-1);
                                            System.out.println("\nYou have successfully added Karaoke " + amenityLast.getAmenityCode());
                                            isAdd = false;
                                            break;
                                        case 2:
                                            method.addAmenity(amenities, "ReceptionHall");
                                            amenityLast = amenities.get(amenities.size()-1);
                                            System.out.println("\nYou have successfully added Reception Hall " + amenityLast.getAmenityCode());
                                            isAdd = false;
                                            break;
                                        case 3:
                                            method.addAmenity(amenities, "Pool");
                                            amenityLast = amenities.get(amenities.size()-1);
                                            System.out.println("\nYou have successfully added Pool " + amenityLast.getAmenityCode());
                                            isAdd = false;
                                            break;
                                        case 4:
                                            method.addAmenity(amenities, "GameRoom");
                                            amenityLast = amenities.get(amenities.size()-1);
                                            System.out.println("\nYou have successfully added Game Room " + amenityLast.getAmenityCode());
                                            isAdd = false;
                                            break;
                                        case 0:
                                            isAdd=false;
                                            break;
                                        default:
                                            System.out.println("INVALID: Use indicated number only");

                                    }

                                }
                                break;
                            case 2:     //View amenity
                                method.displayAmenities(amenities);
                                method.isGoBack();
                                break;
                            case 3:     //Edit amenity
                                boolean editAmenity = true;
                                while(editAmenity==true) {
                                    String amenityCode = null;
                                    System.out.println("\n=====EDIT-AMENITY=====");
                                    System.out.print("Enter the amenity code to edit: ");
                                    amenityCode = sc.nextLine();

                                    if(amenityCode.equals("0")) break;

                                    Amenity amenityEdit = method.getAmenity(amenities, amenityCode);
                                    if(amenityEdit==null) {     //If amenity is not found
                                        System.out.println("INVALID: Amenity not found");
                                        break;
                                    }

                                    System.out.println("\n=====AMENITY-INFO=====");
                                    System.out.println("Amenity Type: " + amenityEdit.getAmenityType());
                                    System.out.println("[1] Reservation Cost: " + amenityEdit.getReservationCost());
                                    System.out.print("Select details to modify: ");
                                    choice = method.inputInt();

                                    double dblChoice = 0;
                                    int intChoice = 0;
                                    switch (choice) {
                                        case 1: //Edit reservation cost
                                            while(true) {
                                                System.out.print("\nEnter new reservation cost: ");
                                                dblChoice = method.inputDouble();

                                                if (dblChoice == -1) {    //Invalid double input
                                                    System.out.println("INVALID: Use real numbers only");
                                                    boolean isCont = method.isContinue();
                                                    if(isCont==true) continue;
                                                    else {      //Exit clause
                                                        System.out.println("\nEdit amenity is failed!");
                                                        editAmenity = false;
                                                        break;
                                                    }
                                                }
                                                amenityEdit.setReservationCost(dblChoice);
                                                System.out.println("\nAmenity is edited successfully");
                                                editAmenity = false;
                                                break;
                                            }//End of loop
                                            break;
                                        case 0:
                                            editAmenity = false;
                                            break;
                                        default:
                                            System.out.println("INVALID: Use indicated numbers only");
                                    }
                                }
                                break;
                            case 4:     //Edit price
                                    ArrayList<Amenity> amenityTypeList = new ArrayList<>();
                                    double newRate = 0;

                                    boolean editRsrvCost = true;
                                    while (editRsrvCost == true) {
                                        System.out.println("\n=====AMENITY=====");
                                        System.out.println("[1] Karaoke");
                                        System.out.println("[2] Reception Hall");
                                        System.out.println("[3] Pool");
                                        System.out.println("[4] Game Room");
                                        System.out.print("Select amenity to modify: ");
                                        choice = method.inputInt();

                                        System.out.print("Enter new reservation cost: ");
                                        newRate = method.inputDouble();

                                        if (newRate == -1) {    //Invalid double input
                                            System.out.println("INVALID: Use real numbers only");
                                            boolean isCont = method.isContinue();
                                            if (isCont == true) continue;
                                            else {      //Exit clause
                                                System.out.println("\nEdit amenity is failed!");
                                                break;
                                            }
                                        }

                                        switch (choice) {
                                            case 1:
                                                amenityTypeList = method.getAmenitiesType(amenities, "Karaoke");
                                                method.setRsrvCostAmenities(amenityTypeList, newRate);
                                                System.out.println("The reservation cost in all karaoke is " + newRate);
                                                editRsrvCost = false;
                                                break;
                                            case 2:
                                                amenityTypeList = method.getAmenitiesType(amenities, "ReceptionHall");
                                                method.setRsrvCostAmenities(amenityTypeList, newRate);
                                                System.out.println("The reservation cost in all reception hall is " + newRate);
                                                editRsrvCost = false;
                                                break;
                                            case 3:
                                                amenityTypeList = method.getAmenitiesType(amenities, "Pool");
                                                method.setRsrvCostAmenities(amenityTypeList, newRate);
                                                System.out.println("The reservation cost in all pool is " + newRate);
                                                editRsrvCost = false;
                                                break;
                                            case 4:
                                                amenityTypeList = method.getAmenitiesType(amenities, "GameRoom");
                                                method.setRsrvCostAmenities(amenityTypeList, newRate);
                                                System.out.println("The reservation cost in all game room is " + newRate);
                                                editRsrvCost = false;
                                                break;
                                            default:
                                                System.out.println("INVALID: Use indicated numbers only");
                                        }
                                    }
                                break;
                            case 5:     //Delete amenity
                                while(true) {
                                    String amenityCode = null;
                                    System.out.println("\n=====DELETE-AMENITY=====");
                                    System.out.print("Enter the amenity code to delete: ");
                                    amenityCode = sc.nextLine();

                                    if (amenityCode.equals("0")) break;

                                    Amenity amenityDel = method.getAmenity(amenities, amenityCode);
                                    if (amenityDel == null) {     //If amenity is not found
                                        System.out.println("INVALID: Amenity not found");
                                        break;
                                    }

                                    boolean isCont = method.isContinue("Do you want to continue delete?");
                                    if(isCont==true) {      //Continue delete amenity
                                        amenities.remove(amenityDel);
                                        System.out.println("Amenity " + amenityDel.getAmenityCode() + " is deleted successfully");
                                        break;
                                    }else {     //Delete is unsuccessful
                                        System.out.println("\nAmenity is not deleted!");
                                        break;
                                    }
                                }
                                break;
                            case 0:
                                isAmenity = false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only!");
                        }
                    }
                    break;
                case 3:         //SET DURATION LIMIT
                    boolean setLimit = true;
                    while(setLimit==true) {
                        System.out.println("\n=====DURATION-LIMIT=====");
                        System.out.println("Current duration(day) limit: " + Main.durationLimit);
                        System.out.println("[1] Change duration limit: ");
                        System.out.println("[0] Back");
                        System.out.print("Enter choice: ");
                        choice = method.inputInt();
                        switch (choice) {
                            case 1:
                                System.out.print("\nSet duration limit: ");
                                int durationLimit = method.inputInt();
                                if (durationLimit == -1) {
                                    System.out.println("INVALID: Use real numbers only");
                                } else {
                                    Main.durationLimit = durationLimit;
                                    System.out.println("The duration limit is successfully set to " + Main.durationLimit + "day");
                                }
                                break;
                            case 0:
                                setLimit=false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated numbers only!");
                        }
                    }
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
            System.out.println("\n=====FOOD-SERVICE=====");
            System.out.println("[1] View food menu");
            System.out.println("[2] Add menu");
            System.out.println("[3] Modify menu");
            System.out.println("[4] Delete menu");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {     //VIEW MENU
                case 1:
                    method.displayMenus(menus);
                    method.isGoBack();
                    break;
                case 2:         //ADD MENU
                    while(true) {   //
                        ArrayList<Food> mainDishes = new ArrayList<>();
                        ArrayList<Food> sideDishes = new ArrayList<>();
                        ArrayList<Food> drinksList = new ArrayList<>();
                        ArrayList<Food> desserts = new ArrayList<>();
                        Food mainDish;
                        Food sideDish;
                        Food drinks;
                        Food dessert;
                        int index = 0;

                        System.out.println("=====CREATE-MENU=====");
                        System.out.print("Set menu name: ");
                        String newMenuName = sc.nextLine();

                        Menu menuFound = method.checkMenuExist(menus, newMenuName);
                        if(menuFound!=null) {       //Restricts creation of duplicate menu name
                            method.stateError("Duplicate menu name is prohibited!");
                            boolean isCont = method.isContinue();
                            if(isCont==true) continue;
                            else break;
                        }

                        //MAIN DISH SELECTION
                        System.out.println("=====MAIN-DISH=====");
                        int count = 1;
                        for(int i = 0; i< Main.foods.size(); i++) {
                            Food food = Main.foods.get(i);
                            if(food.getClass()== MainDish.class) {
                                System.out.println("Dish " + count + " : " + food.getFoodName());
                                mainDishes.add(food);
                                count++;
                            }
                        }
                        System.out.print("Main dish number: ");
                        index = method.inputInt();
                        if(index==0) break;     //Exit clause
                        mainDish = mainDishes.get(index-1);
                        System.out.println("You selected " + mainDish.getFoodName() + " as main dish");

                        //SIDE DISH SELECTION
                        System.out.println("=====SIDE-DISH=====");
                        count = 1;
                        for(int i = 0; i< Main.foods.size(); i++) {
                            Food food = Main.foods.get(i);
                            if(food.getClass()== SideDish.class) {
                                System.out.println("Dish " + count + " : " + food.getFoodName());
                                sideDishes.add(food);
                                count++;
                            }
                        }
                        System.out.print("Side dish number: ");
                        index = method.inputInt();
                        if(index==0) sideDish = null;     //No sidedish selected means if 0
                        sideDish = sideDishes.get(index-1);
                        System.out.println("You selected " + sideDish.getFoodName() + " as side dish");

                        //DRINKS SELECTION
                        while(true) {
                            System.out.println("=====DRINKS=====");
                            count = 1;
                            for (int i = 0; i < Main.foods.size(); i++) {
                                Food food = Main.foods.get(i);
                                if (food.getClass() == Drinks.class) {
                                    System.out.println("Drink " + count + " : " + food.getFoodName());
                                    drinksList.add(food);
                                    count++;
                                }
                            }
                            System.out.print("Drink number: ");
                            index = method.inputInt();
                            if(index>drinksList.size() || index<1) {       //Restricts to priority get drinks in the menu
                                System.out.println("INVALID: You must select a drinks for the menu");
                                continue;
                            }
                            drinks = drinksList.get(index-1);
                            System.out.println("You selected " + drinks.getFoodName() + " as your drinks");
                            break;
                        }

                        //DESSERT SELECTION
                        System.out.println("=====DESSERTS=====");
                        count = 1;
                        for(int i = 0; i< Main.foods.size(); i++) {
                            Food food = Main.foods.get(i);
                            if(food.getClass()== Dessert.class) {
                                System.out.println("Dessert " + count + " : " + food.getFoodName());
                                desserts.add(food);
                                count++;
                            }
                        }
                        System.out.print("Dessert number: ");
                        index = method.inputInt();
                        if(index==0) dessert = null;     //No sidedish selected means if 0
                        dessert = desserts.get(index-1);
                        System.out.println("You selected " + dessert.getFoodName() + " as dessert");

                        if(desserts==null && sideDish==null) {
                            menus.add(new Menu(newMenuName, mainDish, drinks));
                            System.out.println("Successfully added menu " + newMenuName);
                        }else if(desserts==null) {
                            menus.add(new Menu(newMenuName, mainDish, sideDish, drinks));
                            System.out.println("Successfully added menu " + newMenuName);
                        }else {
                            menus.add(new Menu(newMenuName, mainDish, sideDish, drinks, dessert));
                            System.out.println("Successfully added menu "  + newMenuName);
                        }

                        break;
                    }   //End of Add menu
                    break;
                case 3:         //MODIFY MENU
                    System.out.println("=====EDIT-MENU=====");
                    Menu menuSelect = null;
                    int menuIndex = method.selectMenuIndex(menus);
                    if(menuIndex==-1) {
                        System.out.println("INVALID: No menu selected");
                        continue;
                    }else {
                        menuSelect = menus.get(menuIndex-1);
                    }

                    while(menuSelect!=null) {
                        ArrayList<Food> mainDishes = new ArrayList<>();
                        ArrayList<Food> sideDishes = new ArrayList<>();
                        ArrayList<Food> drinksList = new ArrayList<>();
                        ArrayList<Food> desserts = new ArrayList<>();
                        Food mainDish;
                        Food sideDish;
                        Food drinks;
                        Food dessert;
                        int index = 0;
                        String newMenuName = null;

                        System.out.println("Do you want to change name of menu " + menuSelect.getMenuName() +
                                "?[1]Yes/[2]No");
                        choice = method.inputInt();
                        if(choice==1) { //Set menu name
                            System.out.print("Set menu name: ");
                            newMenuName = sc.nextLine();

                            Menu menuFound = method.checkMenuExist(menus, newMenuName);
                            if(menuFound!=null) {       //Restricts creation of duplicate menu name
                                boolean isCont = method.stateError("Duplicate menu name is prohibited!");
                                if(isCont==true) continue;
                                else break;
                            }

                            menuSelect.setMenuName(newMenuName);
                        }
                        newMenuName = menuSelect.getMenuName();


                        //MAIN DISH SELECTION
                        System.out.println("=====MAIN-DISH=====");
                        int count = 1;
                        for(int i = 0; i< Main.foods.size(); i++) {
                            Food food = Main.foods.get(i);
                            if(food.getClass()== MainDish.class) {
                                System.out.println("Dish " + count + " : " + food.getFoodName());
                                mainDishes.add(food);
                                count++;
                            }
                        }
                        System.out.print("Main dish number: ");
                        index = method.inputInt();
                        if(index==0) break;     //Exit clause
                        mainDish = mainDishes.get(index-1);
                        System.out.println("You selected " + mainDish.getFoodName() + " as main dish");

                        //SIDE DISH SELECTION
                        System.out.println("=====SIDE-DISH=====");
                        count = 1;
                        for(int i = 0; i< Main.foods.size(); i++) {
                            Food food = Main.foods.get(i);
                            if(food.getClass()== SideDish.class) {
                                System.out.println("Dish " + count + " : " + food.getFoodName());
                                sideDishes.add(food);
                                count++;
                            }
                        }
                        System.out.print("Side dish number: ");
                        index = method.inputInt();
                        if(index==0) sideDish = null;     //No sidedish selected means if 0
                        sideDish = sideDishes.get(index-1);
                        System.out.println("You selected " + sideDish.getFoodName() + " as side dish");

                        //DRINKS SELECTION
                        while(true) {
                            System.out.println("=====DRINKS=====");
                            count = 1;
                            for (int i = 0; i < Main.foods.size(); i++) {
                                Food food = Main.foods.get(i);
                                if (food.getClass() == Drinks.class) {
                                    System.out.println("Drink " + count + " : " + food.getFoodName());
                                    drinksList.add(food);
                                    count++;
                                }
                            }
                            System.out.print("Drink number: ");
                            index = method.inputInt();
                            if(index>drinksList.size() || index<1) {       //Restricts to priority get drinks in the menu
                                System.out.println("INVALID: You must select a drinks for the menu");
                                continue;
                            }
                            drinks = drinksList.get(index-1);
                            System.out.println("You selected " + drinks.getFoodName() + " as your drinks");
                            break;
                        }

                        //DESSERT SELECTION
                        System.out.println("=====DESSERTS=====");
                        count = 1;
                        for(int i = 0; i< Main.foods.size(); i++) {
                            Food food = Main.foods.get(i);
                            if(food.getClass()== Dessert.class) {
                                System.out.println("Dessert " + count + " : " + food.getFoodName());
                                desserts.add(food);
                                count++;
                            }
                        }
                        System.out.print("Dessert number: ");
                        index = method.inputInt();
                        if(index==0) dessert = null;     //No sidedish selected means if 0
                        dessert = desserts.get(index-1);
                        System.out.println("You selected " + dessert.getFoodName() + " as dessert");

                        if(desserts==null && sideDish==null) {
                            menuSelect = new Menu(newMenuName, mainDish, drinks);
                            System.out.println("Successfully modified menu " + menuSelect.getMenuName());
                        }else if(desserts==null) {
                            menuSelect = new Menu(newMenuName, mainDish, sideDish, drinks);
                            System.out.println("Successfully modified menu " + menuSelect.getMenuName());
                        }else {
                            menuSelect = new Menu(newMenuName, mainDish, sideDish, drinks, dessert);
                            System.out.println("Successfully modified menu "  + menuSelect.getMenuName());
                        }
                        menus.set(menuIndex-1, menuSelect);

                        break;
                    }

                    break;
                case 4:         //DELETE MENU
                    boolean delMenu = true;

                    System.out.println("=====DELETE-MENU=====");
                    while(delMenu==true) {
                        System.out.println("=====MENU-LIST=====");
                        if (menus.size() == 0) System.out.println("There is no menu created");
                        for (int i = 0; i < menus.size(); i++) {        //Display all menu created
                            Menu menu = menus.get(i);
                            System.out.print((i + 1) + " Name: " + menu.getMenuName()
                                    + " || Food: " + menu.getMainDish().getFoodName());
                            if (menu.getSideDish() != null) System.out.print(" , " + menu.getSideDish().getFoodName());
                            if (menu.getDrinks() != null) System.out.print(" , " + menu.getDrinks().getFoodName());
                            if (menu.getDessert() != null) System.out.print(" , " + menu.getDessert().getFoodName());
                            System.out.print(" || Price: " + menu.getTotalPrice());
                            System.out.println();
                        }

                        System.out.print("\nSelect menu number: ");
                        int menuNum = method.inputInt();
                        if(menuNum>menus.size() || menuNum<0) {
                            System.out.println("INVALID: Pick number with existing menu or 0 to exit");
                            continue;
                        }

                        if(menuNum==0) break;

                        Menu menu = menus.get(menuNum-1);
                        menus.remove(menu);
                        System.out.println("Menu " + menu.getMenuName() + " is removed successfully!");
                        break;
                    }   //End of delMenu loop
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
            ArrayList<Food> mainDishes = new ArrayList<>();
            ArrayList<Food> sideDishes = new ArrayList<>();
            ArrayList<Food> drinksList = new ArrayList<>();
            ArrayList<Food> desserts = new ArrayList<>();

            System.out.println("\n====INVENTORY=====");
            System.out.println("[1] View food stocks");
            System.out.println("[2] Add new food");
            System.out.println("[3] Add stocks");
            System.out.println("[4] Modify stocks");
            System.out.println("[5] Delete stocks");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            //Add foods in the respective list
            for(int i = 0; i<foods.size(); i++) {
                Food food = foods.get(i);
                if(food.getClass()==MainDish.class){
                    mainDishes.add(food);
                }else if(food.getClass()==SideDish.class) {
                    sideDishes.add(food);
                }else if(food.getClass()== Drinks.class) {
                    drinksList.add(food);
                }else if(food.getClass()==Dessert.class) {
                    desserts.add(food);
                }
            }

            switch(choice) {
                case 1:     //VIEW FOOD STOCKS
                    System.out.println("=====FOOD-LIST=====");

                    for(int i = 0; i<mainDishes.size(); i++) {
                        Food food = mainDishes.get(i);
                        System.out.println("Maindish " + (i+1) + " : " + food.getFoodName() + " || Stocks: " + food.getStocks());
                    }

                    for(int i = 0; i<sideDishes.size(); i++) {
                        Food food = sideDishes.get(i);
                        System.out.println("Side-dish " + (i+1) + " : " + food.getFoodName() + " || Stocks: " + food.getStocks());
                    }

                    for(int i = 0; i<drinksList.size(); i++) {
                        Food food = drinksList.get(i);
                        System.out.println("Drinks " + (i+1) + " : " + food.getFoodName() + " || Stocks: " + food.getStocks());
                    }

                    for(int i = 0; i<desserts.size(); i++) {
                        Food food = desserts.get(i);
                        System.out.println("Dessert " + (i+1) + " : " + food.getFoodName() + " || Stocks: " + food.getStocks());
                    }
                    method.isGoBack();
                    break;
                case 2:     //ADD NEW FOOD
                    boolean addFood = true;
                    String foodName = null;
                    double price = 0;
                    int stock = 0;

                    while(addFood==true) {
                        System.out.println("===ADD-FOOD===");
                        System.out.println("[1] Maindish");
                        System.out.println("[2] Sidedish");
                        System.out.println("[3] Drinks");
                        System.out.println("[4] Desserts");
                        choice = method.inputInt("Select type of food: ");

                        if(choice==1 || choice==2 || choice==3 || choice==4) {
                        }else if(choice==0) { break;
                        }else {
                            System.out.println("INVALID: Use indicated number only");
                            continue;
                        }

                        System.out.print("Set food name: ");
                        foodName = sc.nextLine();

                        if(foods.contains(foodName)) {
                            System.out.println("Food is already exist!");
                            continue;
                        }

                        System.out.print("Set price: ");
                        price = method.inputDouble();
                        if(price==-1) {
                            System.out.println("INVALID: Enter digits only");
                            continue;
                        }
                        System.out.print("Number of stocks: ");
                        stock = method.inputInt();
                        if(stock==-1) {
                            System.out.println("INVALID: Enter digits only");
                            continue;
                        }

                        switch (choice) {
                            case 1:
                                foods.add(new MainDish(foodName, price, stock));
                                System.out.println("Maindish " + foodName + " with stock " + stock + " is added");
                                addFood=false;
                                break;
                            case 2:
                                foods.add(new SideDish(foodName, price, stock));
                                System.out.println("Sidedish " + foodName + " with stock " + stock + " is added");
                                addFood=false;
                                break;
                            case 3:
                                foods.add(new Drinks(foodName, price, stock));
                                System.out.println("Drink " + foodName + " with stock " + stock + " is added");
                                addFood = false;
                                break;
                            case 4:
                                foods.add(new Dessert(foodName, price, stock));
                                System.out.println("Dessert " + foodName + " with stock " + stock + " is added");
                                addFood = false;
                                break;
                            case 0:
                                addFood = false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only!");
                        }
                    }
                    break;
                case 3:     //ADD STOCKS
                    String foodAdd = null;
                    boolean isFound = false;
                    Food foodToAdd = null;
                    int stockInc = 0;

                    while(true) {
                        System.out.println("===ADD-STOCKS===");
                        System.out.print("Enter food name to add: ");
                        foodAdd = sc.nextLine();

                        if(foodAdd.equals("0")) break;  //Exit clause

                        for (int i = 0; i < foods.size(); i++) { //check if food name is existing
                            Food food = foods.get(i);
                            if (food.getFoodName().equals(foodAdd)) {
                                foodToAdd = food;
                                isFound = true;
                            }
                        }

                        if(isFound==false) {    // if food not found
                            System.out.println("INVALID: Food is not found");
                            continue;
                        }

                        int foodStock = foodToAdd.getStocks();

                        System.out.println("Stock: " + foodStock);
                        System.out.print("\nEnter stocks increase: ");
                        stockInc = method.inputInt();

                        //Set the stock into the sum of foodStock and stockInc
                        foodToAdd.setStocks(foodStock+stockInc);
                        System.out.println("Food " + foodToAdd.getFoodName() + " stock is now: " + foodToAdd.getStocks());
                        break;
                    }

                    break;
                case 4:     //MODIFY STOCKS
                    foodAdd = null;
                    isFound = false;
                    Food foodModify = null;
                    int newStock = 0;

                    while(true) {
                        System.out.println("===MODIFY-STOCKS===");
                        System.out.print("Enter food name to modify: ");
                        foodAdd = sc.nextLine();

                        if(foodAdd.equals("0")) break;  //Exit clause

                        for (int i = 0; i < foods.size(); i++) { //check if food name is existing
                            Food food = foods.get(i);
                            if (food.getFoodName().equals(foodAdd)) {
                                foodModify = food;
                                isFound = true;
                            }
                        }

                        if(isFound==false) {    // if food not found
                            System.out.println("INVALID: Food is not found");
                            continue;
                        }

                        System.out.println("Stock: " + foodModify.getStocks());
                        System.out.print("\nSet number of stocks: ");
                        newStock = method.inputInt();

                        if(newStock==-1) {      //Invalid input
                            System.out.println("INVALID: Enter digits only");
                            continue;
                        }

                        //Set the stock into the sum of foodStock and stockInc
                        foodModify.setStocks(newStock);
                        System.out.println("Food " + foodModify.getFoodName() + " stock is now: " + foodModify.getStocks());
                        break;
                    }

                    break;
                case 5:     //DELETE STOCKS
                    foodAdd = null;
                    Food foodDel = null;
                    isFound = false;
                    newStock = 0;

                    while(true) {
                        System.out.println("===DELETE-STOCKS===");
                        System.out.print("Enter food name to delete: ");
                        foodAdd = sc.nextLine();

                        if(foodAdd.equals("0")) break;  //Exit clause

                        for (int i = 0; i < foods.size(); i++) { //check if food name is existing
                            Food food = foods.get(i);
                            if (food.getFoodName().equals(foodAdd)) {
                                foodDel = food;
                                isFound = true;
                            }
                        }

                        if(isFound==false) {    // if food not found
                            System.out.println("INVALID: Food is not found");
                            continue;
                        }

                        int foodStock = foodDel.getStocks();

                        System.out.println("Stock: " + foodStock);
                        if(foodStock==0) {
                            System.out.println("There is no stock to delete");
                            break;
                        }
                        System.out.print("\nEnter stocks decrease: ");
                        int stockDel = method.inputInt();

                        if(foodStock<stockDel) {
                            System.out.println("INVALID: Stocks are smaller than stocks to delete!");
                            continue;
                        }

                        //Set the stock into the sum of foodStock and stockInc
                        foodDel.setStocks(foodStock-stockDel);
                        System.out.println("Food " + foodDel.getFoodName() + " stock is now: " + foodDel.getStocks());
                        break;
                    }
                    break;
                case 0:
                    isInventory=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================RESERVATIONS
    public void goReservations(ArrayList<Reservation> reservations) {
        boolean isReservation = true;
        while(isReservation==true) {
            System.out.println("\n=====RESERVATIONS=====");
            System.out.println("[1] View reservation");
            System.out.println("[2] Edit reservation");
            System.out.println("[3] Cancel reservation");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //VIEW RESERVATION
                    method.displayReservations(reservations);
                    method.isGoBack();
                    break;
                case 2:     //EDIT RESERVATION
                    boolean isEdit = true;
                    isEdit:
                    while (isEdit == true) {
                        Reservation reservation = null;
                        Reservation reserveToEdit = null;
                        int roomNum = 0;

                        System.out.println("\n=====EDIT-RESERVATIONS=====");
                        method.displayReservations(reservations);
                        System.out.print("Enter reservation room number/amenity code to modify: ");
                        String code = method.inputString();

                        if (method.digitChecker(code)==true) {
                            roomNum = method.StrToInt(code);
                        }

                        //Checks if roomNum exist
                        boolean isExistRoom = false;
                        boolean isExistAmenity = false;
                        if (roomNum != 0) {
                            isExistRoom = method.isReservationRoomNumExist(reservations, roomNum);
                            if (isExistRoom == false) {
                                System.out.println("INVALID: Room number do not have a reservation");
                                break;
                            }else isExistRoom = true;
                        }else {
                            isExistAmenity = method.isReservationAmenityCodeExist(reservations, code);
                            if (isExistAmenity == false) {
                                System.out.println("INVALID: Amenity code do not have a reservation");
                                break;
                            }else isExistAmenity = true;
                        }

                        boolean isGood = true;
                        if(isExistRoom==true) reservation = Main.reservations.get(method.getReservationRoomIndex(Main.reservations, roomNum));
                        while (isExistRoom == true) {
                            reserveToEdit = reservation;
                            isGood = true;      //use to determine if reservation date or duration do not conflict

                            System.out.println("\n===RESERVATION-DETAILS===");
                            System.out.println("[1] Room Number: " + reserveToEdit.getRoom().getRoomNum());
                            System.out.print("[2] Start Date: ");
                            reserveToEdit.getStartDate().displayDate();
                            System.out.print("[3] Duration: " + reserveToEdit.getDuration());
                            if (reserveToEdit.getDuration() == 1) System.out.println("day");
                            else System.out.println("days");
                            System.out.println("[4] Continue");
                            System.out.print("Select to modify: ");
                            choice = method.inputInt();

                            switch (choice) {
                                case 1:     //Modify room number
                                    boolean modifyRoomNum = true;
                                    Room roomToMove = null;
                                    int duration = 0;
                                    Date startDate = null;

                                    while (modifyRoomNum == true) {
                                        boolean isFound = false;
                                        roomToMove = method.selectRoom(Main.rooms);
                                        if(roomToMove!=null) {
                                            System.out.println("INVALID: Room do not exist!");
                                        }

                                        reserveToEdit.setRoom(roomToMove);
                                        method.checkReservation(reservations, reserveToEdit, reservation);
                                        break;
                                    }
                                    break;
                                case 2:     //Modify start date
                                    while (true) {
                                        startDate = method.inputDate();
                                        if (startDate == null) {        //If wrong date input
                                            boolean isCont = method.isContinue();
                                            if (isCont == false) continue;
                                            else break;
                                        }
                                        reserveToEdit.setStartDate(startDate);
                                        break;
                                    }
                                    isGood = method.checkReservation(reservations, reserveToEdit, reservation);

                                    break;
                                case 3:     //Modify duration
                                    System.out.print("Set the duration(day): ");
                                    duration = method.inputInt();
                                    if (duration < 1) {
                                        System.out.println("INVALID: Duration of days should be real number");
                                    } else if (duration > Main.durationLimit) {
                                        System.out.println("INVALID: Duration limit is only " + Main.durationLimit + "days");
                                    } else {
                                        reserveToEdit.setDuration(duration);
                                    }
                                    isGood = method.checkReservation(reservations, reserveToEdit, reservation);
                                    break;
                                case 4:
                                    isExistRoom = false;
                                    isEdit = false;
                                    break;
                                default:
                                    System.out.println("INVALID: Use indicated number only!");
                            }
                        }

                        if(isExistAmenity==true) reservation = Main.reservations.get(method.getReservationAmenityIndex(reservations, code));
                        while (isExistAmenity == true) {
                            reserveToEdit = reservation;
                            isGood = true;      //use to determine if reservation date or duration do not conflict

                            System.out.println("\n===RESERVATION-DETAILS===");
                            System.out.println("[1] Amenity : " + reserveToEdit.getAmenity().getAmenityType() + " " +reserveToEdit.getAmenity().getAmenityCode());
                            System.out.print("[2] Start Date: ");
                            reserveToEdit.getStartDate().displayDate();
                            System.out.print("[3] Duration: " + reserveToEdit.getDuration());
                            if (reserveToEdit.getDuration() == 1) System.out.println("day");
                            else System.out.println("days");
                            System.out.println("[4] Continue");
                            System.out.print("Select to modify: ");
                            choice = method.inputInt();

                            switch (choice) {
                                case 1:     //Modify room number
                                    boolean modifyAmenity = true;
                                    int duration = 0;
                                    Date startDate = null;

                                    while (modifyAmenity == true) {
                                        boolean isFound = false;

                                        Amenity amenity = method.selectAmenity(Main.amenities);
                                        if (amenity != null) {
                                            isFound = true;
                                        }

                                        if (isFound == false) {    //If room number not exist
                                            boolean isCont = method.stateError("Amenity code do not exist");
                                            if (isCont == false) {
                                                modifyAmenity = false;
                                                break isEdit;
                                            }
                                        }

                                        reserveToEdit.setAmenity(amenity);
                                        method.checkReservation(reservations, reserveToEdit, reservation);
                                        break;
                                    }
                                    break;
                                case 2:     //Modify start date
                                    while (true) {
                                        startDate = method.inputDate();
                                        if (startDate == null) {        //If wrong date input
                                            boolean isCont = method.isContinue();
                                            if (isCont == false) continue;
                                            else break;
                                        }
                                        reserveToEdit.setStartDate(startDate);
                                        break;
                                    }
                                    isGood = method.checkReservation(reservations, reserveToEdit, reservation);
                                    break;
                                case 3:     //Modify duration
                                    System.out.print("Set the duration(day): ");
                                    duration = method.inputInt();
                                    if (duration < 1) {
                                        System.out.println("INVALID: Duration of days should be real number");
                                    } else if (duration > Main.durationLimit) {
                                        System.out.println("INVALID: Duration limit is only " + Main.durationLimit + "days");
                                    } else {
                                        reserveToEdit.setDuration(duration);
                                    }
                                    isGood = method.checkReservation(reservations, reserveToEdit, reservation);
                                    break;
                                case 4:
                                    isExistAmenity = false;
                                    isEdit = false;
                                    break;
                                default:
                                    System.out.println("INVALID: Use indicated number only!");
                            }
                            System.out.println();
                        }

                        if (isGood == true) {
                            reservation = reserveToEdit;
                            System.out.println("Reservation is modified successfully!");
                            isEdit = false;
                            break;
                        } else {
                            System.out.println("\nINVALID: Reservation date have conflict in other reservations");
                            System.out.println("Modification of reservation is unsuccessful!");
                            isEdit = false;
                            break;
                        }

                    }//End of Modify reservation
                    break;
                case 3:     //Cancel reservation
                    boolean isCancel = true;
                    while(isCancel==true) {
                        Reservation reservation = null;
                        int roomNum = 0;

                        System.out.println("\n=====CANCEL-RESERVATIONS=====");
                        method.displayReservations(reservations);
                        System.out.print("Enter reservation room number to cancel: ");
                        roomNum = method.inputInt();

                        //Checks if roomNum exist
                        boolean isExist = method.isReservationRoomNumExist(reservations, roomNum);
                        if(isExist==false) {
                            System.out.println("INVALID: Room number do not have a reservation");
                            break;
                        }

                        reservation = Main.reservations.get(method.getReservationRoomIndex(reservations, roomNum));

                        boolean isCont = method.isContinue("Do you want to cancel reservation?");
                        if(isCont==true) {
                            reservations.remove(reservation);
                            System.out.println("Cancel of reservation is successful");
                            break;
                        }else {
                            System.out.println("Cancel of reservation is unsuccessful");
                            break;
                        }
                    }//End of isCancel loop
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
            System.out.println("\n=====REPORTS=====");
            System.out.println("[1] Accounts");
            System.out.println("[2] Rooms");
            System.out.println("[3] Reservations");
            System.out.println("[4] Sales");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1: //ACCOUNTS-REPORTS
                    boolean isAccount = true;
                    while(isAccount==true) {
                        System.out.println("\n=====ACCOUNTS=====");
                        System.out.println("[1] Customer");
                        System.out.println("[2] Staff");
                        System.out.println("[0] Back");
                        System.out.print("Enter choice: ");
                        choice = method.inputInt();

                        switch (choice) {
                            case 1:
                                System.out.println("\n====CUSTOMER-LIST====");
                                if(Main.customersList.size()!=0) {      //There is a customer registered
                                    for (int i = 0; i < Main.customersList.size(); i++) {
                                        Customer customer = Main.customersList.get(i);
                                        int transactNum = customer.getTransact().size() + customer.getTransHistory().size();
                                        System.out.print((i + 1) + " " + customer.getName() + " || Transacts: " + transactNum);
                                        System.out.println(" || Contact: " + customer.getEmail());
                                    }
                                }else {
                                    System.out.println("\nThere is no customer registered!");
                                }
                                break;
                            case 2:
                                System.out.println("\n====STAFF-LIST====");
                                if(Main.staffsList.size()!=0) {      //There is a staff registered
                                    for (int i = 0; i < Main.staffsList.size(); i++) {
                                        Staff staff = Main.staffsList.get(i);
                                        System.out.print((i + 1) + " " + staff.getName() + " || Sales: " + method.getStaffSales(staff));
                                        System.out.println(" || Contact: " + staff.getEmail());
                                    }
                                }else {
                                    System.out.println("\nThere is no staff registered!");
                                }
                                break;
                            case 0:
                                isAccount=false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only!");
                        }
                    }
                    break;
                case 2:     //ROOMS-REPORTS
                    boolean isRooms = true;
                    while(isRooms==true){
                        StaffPage staffP = new StaffPage();
                        staffP.goManageRoom(Main.rooms);
                    }
                    break;
                case 3:     //RESERVATION-REPORTS
                    boolean isReservation = true;
                    while(isReservation==true) {
                        ArrayList<ReserveTransact> reserveTransacts = Main.reserveTransacts;
                        System.out.println("=====RESERVATIONS=====");
                        System.out.println("[1] Pending reservation");
                        System.out.println("[2] Reservation history");
                        System.out.println("[0] Back");
                        System.out.print("Enter choice: ");
                        choice = method.inputInt();

                        switch (choice) {
                            case 1:
                                System.out.println("===PENDING-RESERVATION===");
                                if(reserveTransacts.size()!=0) {       //There is pending reservation
                                    method.displayReserveTransacts(Main.reserveTransacts);
                                }else {         //There is no pending
                                    System.out.println("\nThere is no pending reservations listed!");
                                }
                                break;
                            case 2:
                                boolean isHistory = true;
                                while(isHistory==true) {
                                    System.out.println("===RESERVATION-HISTORY===");
                                    ArrayList<ReserveTransact> reserveTransacts1 = method.getReserveTransact(Main.pastTransacts);
                                    method.displayReserveTransacts(reserveTransacts1);
                                    break;
                                }
                                break;
                            case 0:
                                isReservation=false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only!");
                        }
                        break;
                    }
                    break;
                case 4:     //SALES-REPORTS
                    boolean isSales = true;
                    while(isSales==true) {
                        System.out.println("\n=====SALES=====");
                        System.out.println("[1] Sales per staff");
                        System.out.println("[2] Total sales");
                        System.out.println("[0] Back");
                        System.out.print("Enter choice: ");
                        choice = method.inputInt();

                        switch (choice) {
                            case 1:
                                boolean perStaff = true;
                                while(perStaff==true) {
                                    Staff staffFound = null;
                                    ArrayList<Transact> sales = new ArrayList<>();

                                    System.out.println("\n====SALES-PER-STAFF=====");
                                    System.out.print("Enter staff name: ");
                                    String staffName = sc.nextLine();

                                    for(int i = 0; i<Main.staffsList.size(); i++) {     //Find the staff in staffList
                                        Staff staff = Main.staffsList.get(i);
                                        if(staff.getName().equals(staffName)) {         //Staff is found
                                            staffFound=staff;
                                            break;
                                        }
                                    }

                                    if(staffFound==null) {      //Staff is not found
                                        System.out.println("\nThere are no staff registered with same name!");
                                        break;
                                    }

                                    sales = staffFound.getSales();
                                    method.sortTransact(sales);
                                    if(sales.size()==0) {
                                        System.out.println("\nThis staff have no sales recorded!");
                                        break;
                                    }

                                    boolean inSales = true;
                                    while(inSales==true) {
                                        System.out.println("\n===SALES===");
                                        System.out.println("Name: " + staffFound.getName());
                                        System.out.println("[1] Daily");
                                        System.out.println("[2] Weekly");
                                        System.out.println("[3] Monthly");
                                        System.out.println("[0] Back");
                                        System.out.print("Enter choice: ");
                                        choice = method.inputInt();

                                        switch (choice) {
                                            case 1:     //DAILY
                                                method.displayDailyTransact(sales);
                                                System.out.println("");
                                                method.isGoBack();
                                                break;
                                            case 2:     //WEEKLY
                                                method.displayWeeklySales(sales);
                                                System.out.println("");
                                                method.isGoBack();
                                                break;
                                            case 3:     //MONTHLY
                                                method.displayMonthlySales(sales);
                                                System.out.println("");
                                                method.isGoBack();
                                                break;
                                            case 0:
                                                inSales = false;
                                                perStaff = false;
                                                break;
                                            default:
                                                System.out.println("INVALID: Use indicated number only!");
                                        }
                                    }
                                }
                                break;
                            case 2:
                                ArrayList<Transact> transacts = Main.pastTransacts;
                                method.sortTransact(transacts);
                                if(transacts.size()==0) {
                                    System.out.println("\nThere is no sales recorded!");
                                    break;
                                }

                                boolean inSales = true;
                                while(inSales==true) {
                                    System.out.println("\n===SALES===");
                                    System.out.println("[1] Daily");
                                    System.out.println("[2] Weekly");
                                    System.out.println("[3] Monthly");
                                    System.out.println("[4] Total Sales");
                                    System.out.println("[0] Back");
                                    System.out.print("Enter choice: ");
                                    choice = method.inputInt();

                                    switch (choice) {
                                        case 1:     //DAILY
                                            method.displayDailyTransact(transacts);
                                            System.out.println("");
                                            method.isGoBack();
                                            break;
                                        case 2:     //WEEKLY
                                            method.displayWeeklySales(transacts);
                                            System.out.println("");
                                            method.isGoBack();
                                            break;
                                        case 3:     //MONTHLY
                                            method.displayMonthlySales(transacts);
                                            System.out.println("");
                                            method.isGoBack();
                                            break;
                                        case 4:
                                            if(transacts.size()!=0) {       //There is a sale
                                                double totalSales = 0;

                                                for(int i = 0; i<transacts.size(); i++) {       //Add the transactSale in the sales
                                                    Transact transact = transacts.get(i);
                                                    totalSales += transact.getBills();
                                                }
                                                System.out.println("\n=====TOTAL-SALES=====");
                                                System.out.println("Sales: " + totalSales);

                                            }else {     //There is no sale
                                                System.out.println("\nThere is no sales!");
                                            }
                                            break;
                                        case 0:
                                            inSales = false;
                                            break;
                                        default:
                                            System.out.println("INVALID: Use indicated number only!");
                                    }
                                }
                                break;
                            case 0:
                                isSales=false;
                                break;
                            default:
                                System.out.println("INVALID: Use indicated number only!");
                        }
                    }
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

