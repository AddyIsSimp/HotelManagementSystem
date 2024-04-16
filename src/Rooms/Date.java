package Rooms;

public class Date {
    private int date;
    private int month;
    private int year;

    public Date() {
        date = -1;
        month = -1;
        year = -1;
    }

    public Date(Date dat) {
        this.date = dat.getDate();
        this.month = dat.getMonth();
        this.year = dat.getYear();
    }
    
    public Date(int day, int month, int year) {
        this.date = day;
        this.month = month;
        this.year = year;
    }

    public void displayDate() {
        System.out.println(month + "/" + date + "/" + year);
    }
    public void displayDate2() {System.out.print(month + "/" + date + "/" + year);}
    public void displayDate3() {System.out.print(monthValue(month) + "," + date + "," + year);}

    public boolean equals(Object obj) {
        // check for reference equality.
        if(obj instanceof Date){
            Date obj1 = (Date) obj;
            boolean flag = true;
            if((this.getDate()==obj1.getDate())==false ||
                    (this.getMonth()==obj1.getMonth())==false ||
                    (this.getYear()==obj1.getYear())==false) {
                flag = false;
            }
            return flag;
        }
        return true;
    }

    public void setDate(int date) {this.date = date;}
    public int getDate() {return date;}

    public void setMonth(int month) {this.month = month;}
    public int getMonth() {return  month;}

    public void setYear(int year) {this.year = year;}
    public int getYear() {return  year;}

    public int incrementDate() {return getDate()+1;}
    public int incrementMonth() {return getMonth()+1;}
    public int incrementYear() {return getYear()+1;}

    public static String monthValue(int d) {
        String month = null;
        if(d==1) {
            month="January";
        }else if(d==2) {
            month="February";
        }else if(d==3) {
            month="March";
        }else if(d==4) {
            month="April";
        }else if(d==5) {
            month="May";
        }else if(d==6) {
            month="June";
        }else if(d==7) {
            month="July";
        }else if(d==8) {
            month="August";
        }else if(d==9) {
            month="September";
        }else if(d==10) {
            month="October";
        }else if(d==11) {
            month="November";
        }else if(d==12) {
            month="December";
        }
        return month;
    }

}
