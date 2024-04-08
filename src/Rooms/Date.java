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
        System.out.println(date + "/" + month + "/" + year);
    }
    public void displayDate2() {System.out.print(date + "/" + month + "/" + year);}

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

}
