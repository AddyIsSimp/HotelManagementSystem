import Rooms.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static Main.Main.rooms;

public class ForCodeTrial {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //SAMPLE ROOMS
        rooms.add(new SingleRoom(5));
        rooms.add(new SingleRoom(2));
        rooms.add(new SingleRoom(1));
        rooms.add(new SingleRoom(4));
        rooms.add(new SingleRoom(3));
        rooms.add(new CoupleRoom(7));
        rooms.add(new CoupleRoom(6));
        rooms.add(new CoupleRoom(8));
        rooms.add(new CoupleRoom(10));
        rooms.add(new CoupleRoom(9));

        isContinue();

    }

    public static boolean isContinue() {       //Return boolean if still want to continue or not
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
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    public static int inputInt() {
        int choiceNum = -1;
        try {
            String choice = sc.nextLine();
            boolean isAllNum = digitChecker(choice);
            choiceNum = StrToInt(choice);
        } catch(Exception e) {
            System.out.println("INVALID: Input numbers only!");
        }
        return choiceNum;
    }

    public static int inputInt(String statement) {
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

    static boolean digitChecker(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)!=true) {
                //System.out.println("There is a string: " + ch);
                return false;
            }
        }
        return true;
    }

    static int StrToInt(String s) {
        int num = 0;
        num = Integer.parseInt(s);
        return num;
    }

}
