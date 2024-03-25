package Main;

import Entity.*;
import Main.*;
import Main.Main.*;
import Menus.*;
import Rooms.*;

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
    public void manageRoom(ArrayList<Room> rooms) {
        boolean isManage = true;
        while(isManage==true) {
            int roomNum = 0;
            int roomIndex = 0;

            System.out.println("=====MANAGE-ROOMS=====");
            System.out.println("[1] Rooms");
            System.out.println("[2] Amenities");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    boolean isRoom = true;
                    while(isRoom==true) {
                        System.out.println("=====ROOMS=====");
                        System.out.println("[1] View rooms");
                        System.out.println("[2] Add room");
                        System.out.println("[3] Edit room");
                        System.out.println("[4] Delete room");
                        System.out.println("[0] Back");
                        choice = method.inputInt("Enter choice: ");

                        switch(choice) {
                            case 1:
                                boolean isView = true;
                                while(isView==true) {
                                    System.out.println("===VIEW-ROOMS===");
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
                            case 2:
                                boolean isAdd = true;
                                while(isAdd==true) {
                                    //Set the room number of new room
                                    System.out.println("=====ADD-ROOM=====");
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
                                    System.out.println("Select type of room");
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
                            case 3:
                                //EDIT ROOMS - enable or disable the room
                                boolean isEdit = true;
                                while(isEdit==true) {
                                    System.out.println("=====EDIT-ROOM=====");
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
                                                        break;
                                                    case 2:
                                                        isDisable = false;
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
                                                        isDisable = false;
                                                        break;
                                                    case "2":
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
                            case 4:
                                boolean isDelete = true;

                                while(isDelete==true) {
                                    System.out.println("=====DELETE-ROOM=====");
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
                    method.displayMenus(menus);
                    break;
                case 2:
                    while(true) {
                        System.out.println("=====CREATE-MENU=====");
                        System.out.print("Set menu name: ");
                        String newMenuName = sc.nextLine();

                        //
                    }
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

