package Main;

import Entity.*;
import Rooms.*;
import Rooms.Reservation;
import Transaction.HotelTransact;
import Transaction.ReserveTransact;
import Transaction.Transact;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffPage {

    public static Staff staffAcct = null;

    private Scanner sc = new Scanner(System.in);
    private String choice = null;
    private Methods method = new Methods();
    private CustomerPage customerPage = new CustomerPage();

    //LOGIN STAFF
    boolean loginStaff(ArrayList<Staff> staffsList) {
        String un = null;
        String pw = null;
        boolean isLogin = false;

        while(true) {
            System.out.println("\n=====STAFF=====");
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
                boolean isCont = method.isContinue();
                if(isCont==true) continue;
                else return false;
            }
        }//End of isLogin whileloop
    }//end of loginStaff method


    //========================================SUB-MENU============================================================
    //================================MANAGE-ROOMS
    public void goManageRoom(ArrayList<Room> rooms) {
        boolean isManage = true;
        ArrayList<Room> occupiedRoom = new ArrayList<>();
        ArrayList<Room> availRoom = new ArrayList<>();
        ArrayList<Room> disableRoom = new ArrayList<>();

        if(rooms.size()!=0) {
            for(int i = 0; i<rooms.size(); i++) {
                Room room = rooms.get(i);
                if(room.getIsOccupied()==true) {
                    occupiedRoom.add(room);
                }else if(room.getIsOccupied()==false) availRoom.add(room);
                if(room.getIsDisabled()==true) disableRoom.add(room);
            }
        }

        while(isManage==true) {
            System.out.println("\n=====MANAGE-ROOMS=====");
            System.out.println("[1] Display occupied rooms");
            System.out.println("[2] Display available rooms");
            System.out.println("[3] Display disabled rooms");
            System.out.println("[4] View category");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:     //display occupied rooms
                    if(occupiedRoom.size()==0) {
                        System.out.println("\nThere are no rooms occupied");
                    }else {
                        System.out.println("=====OCCUPIED-ROOM=====");
                        for(int i = 0; i<occupiedRoom.size(); i++) {
                            Room room = occupiedRoom.get(i);
                            System.out.println((i+1) + " Room: " + room.getRoomType() + " " +
                                    room.getRoomNum() + " || Customer: ");
                        }
                    }
                    break;
                case 2:     //display available roos
                    System.out.println("=====Available-Rooms=====");
                    if(rooms.size()==0) {
                        System.out.println("\nThere are no available rooms");
                    }else {
                        for(int i = 0; i<availRoom.size(); i++) {
                            Room room = availRoom.get(i);
                            System.out.println((i+1) + " Room: " + room.getRoomType() + " " +
                                    room.getRoomNum());
                        }
                    }
                    break;
                case 3:
                    if(disableRoom.size()==0) {
                        System.out.println("\nThere are no disabled rooms");
                    }else {
                        System.out.println("=====Disabled-Rooms=====");
                        for(int i = 0; i<disableRoom.size(); i++) {
                            Room room = disableRoom.get(i);
                            System.out.println((i+i) + "Room: " + room.getRoomType() + " " +
                                    room.getRoomNum() + "|| Customer: ");
                        }
                    }
                    break;
                case 4:
                    System.out.println("=====Room-Category=====");
                    method.displayRoomCategory(rooms);
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================CUSTOMER-ACCOUNT================================
    public void goCustomerAccount(ArrayList<Customer> customers) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("\n=====CUSTOMER-ACCOUNT=====");
            System.out.println("[1] Register customer");
            System.out.println("[2] View all customers");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    Customer customer = customerPage.register(customers);
                    customers.add(customer);
                    break;
                case 2:
                    System.out.println("=====Customer Lists=====");
                    method.displayCustomer(customers);
                    method.isGoBack();
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    //================================RESERVATIONS================================
    public void goReservations(ArrayList<Reservation> reservations) {
        boolean isManage = true;
        while(isManage==true) {
            System.out.println("\n=====RESERVATIONS=====");
            System.out.println("[1] View all reservations");
            System.out.println("[0] Back");
            int choice = method.inputInt("Enter choice: ");

            switch(choice) {
                case 1:
                    method.displayReservations(reservations);
                    break;
                case 0:
                    isManage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    public void goAcceptPayment(ArrayList<Transact> sales) {
        int pick = 0;
        boolean acceptMain = true;
        ArrayList<String> listOfPayment = new ArrayList<>();

        //Add the sales of reservationTransact that have ended in the sales list
        for(int i = 0; i<Main.reserveSales.size(); i++) {
            ReserveTransact transact = (ReserveTransact) Main.reserveSales.get(i);
            if(transact.getRsrvEnded()==true) {
                sales.add(transact);
                Main.reserveSales.remove(transact);
            }
        }

        while(acceptMain==true) {
            System.out.println("\n=====ACCEPT-PAYMENT=====");

            if(sales.size()==0) {       //There are no payment
                System.out.println("\nThere are no payments currently!");
                break;
            }

            for(int i = 0; i<sales.size(); i++) {                               //Display each sale details
                Transact transact = sales.get(i);
                Customer customer = transact.getCustomer();
                System.out.print("[" + (i+1) + "] Customer: " + customer.getName());
                if(transact.getClass()==HotelTransact.class) {                  //Transact is hotelTransact
                    System.out.print(" || Type: HotelTransact");
                }else if(transact.getClass()== ReserveTransact.class) {         //Transact is ReserveTransact
                    System.out.print(" || Type: ReserveTransact");
                }

                if(transact.getClass()== HotelTransact.class) {                 //If it is a room transact
                    HotelTransact transact1 = (HotelTransact) transact;
                    if(transact1.getBills()==0 && transact1.getRoomCost()>0) {
                        System.out.print(" || Bills: " + ((HotelTransact) transact).getRoomCost());
                    }
                }else {
                    System.out.print(" || Bills: " + transact.getBills());
                }

                System.out.print(" || Transaction date: ");
                transact.getDateOfTrans().displayDate();
                listOfPayment.add(method.IntToStr(i+1));
            }

            System.out.println("[a] Accept all");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();

            if(choice.equals("0")) break;       //Exit clause

            //ACCEPT ALL SALES
            if(choice.equalsIgnoreCase("a")) {
                int saleTransactCount=0;
                for(int i = 0; i<sales.size(); i++) {
                    Transact transact = sales.get(i);

                    if(transact.getClass()== HotelTransact.class) {             //If the sale is a roomTransact
                        HotelTransact transact1 = (HotelTransact) transact;
                        if (transact1.getBills() == 0 && transact1.getRoomCost() > 0) {
                            transact.setBills(transact1.getRoomCost());
                        }
                    }

                    transact.setStaff(staffAcct);       //Set the transaction owner to this staff
                    staffAcct.addSales(transact);       //Add the transact in staff sale list
                    Main.pastTransacts.add(transact);   //Save this transact in pastTransacts
                    sales.remove(transact);
                    saleTransactCount++;
                }
                System.out.println("\nSuccessfully accepted " + saleTransactCount + " payment!");
                break;

            }else {     //ACCEPT BY INDEX

                //GET THE PAYMENT INDEX
                boolean isPaymentFound = false;
                int indexPayment = 0;       //index of payment if found
                for (int i = 0; i < listOfPayment.size(); i++) {      //Finds the index of payment
                    if (choice.equalsIgnoreCase(listOfPayment.get(i))) {     //Get the index of
                        indexPayment = i + 1;
                        isPaymentFound = true;
                        break;
                    }
                }

                if (isPaymentFound == false) {     //If choice is not on the given list
                    System.out.println("INVALID: Payment number is not found!");
                    boolean isCont = method.isContinue();
                    if (isCont == true) continue;
                    else break;
                }

                //GET THE TRANSACT OBJECT
                Transact transact = sales.get(indexPayment - 1);

                if(transact.getClass()== HotelTransact.class) {             //If the sale is a roomTransact
                    HotelTransact transact1 = (HotelTransact) transact;
                    if (transact1.getBills() == 0 && transact1.getRoomCost() > 0) {
                        transact.setBills(transact1.getRoomCost());
                    }
                }

                transact.setStaff(staffAcct);       //Set the transaction owner to this staff
                staffAcct.addSales(transact);       //Add the transact in staff sale list
                Main.pastTransacts.add(transact);   //Save this transact in pastTransacts
                sales.remove(transact);
                System.out.println("\nSuccesfully accepted payment number " + indexPayment);
                break;
            }
        }//end of AcceptMain loop
    }

}
