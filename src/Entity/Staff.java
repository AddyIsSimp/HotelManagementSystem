package Entity;
import Transaction.Transact;

import java.util.ArrayList;
import java.util.Scanner;
public class Staff extends Person{
    private ArrayList<Transact> sales = new ArrayList<>();

    public Staff() {
    }

    public Staff(String name, String password) {
        this.name = name;
        setPassword(password);
    }

    public Staff(String name, String password, String email) {
        this.name = name;
        setPassword(password);
        setEmail(email);
    }

//    Staff registerStaff(ArrayList<Staff> staffs){        //The parameter is to check whether there is duplicate name
//        Staff newStaff = null;         //new staff instance
//        boolean isDupl = false;         //if staff name is duplicate
//
//        System.out.print("Enter name: ");
//        newStaff.name = sc.nextLine();
//        for(int i = 0; i<staffs.size(); i++) {      //Checks if there is duplicate nam
//            Staff staf1 = staffs.get(i);
//            if(staf1.name.equalsIgnoreCase(newStaff.name)) {
//                isDupl=true;
//                break;
//            }
//        }
//        return newStaff;
//    }

    public void addSales(Transact transact) {this.sales.add(transact);}
    public ArrayList<Transact> getSales() {return this.sales;}

}
