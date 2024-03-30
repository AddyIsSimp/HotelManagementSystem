import Rooms.*;
import Rooms.Date;

import java.util.*;

import static Main.Main.rooms;

public class ForCodeTrial {

    //Debug the getEndDate-Date here
    //Dili ma increment ang date

    public static void main(String[] args)  {
        Date date1 = new Date(12, 13, 2003);
        Date date2 = new Date(12, 13, 2003);
        Date date3 = new Date(11, 13, 2003);
        Date date4 = new Date(12, 14, 2003);
        Date date5 = new Date(12, 13, 2002);

        if(date1.equals(date5)==true) System.out.println("Still same");
        else System.out.println("false");
    }

}