package Rooms;

public class Date {
    int date;
    int month;
    int year;

    Date() {
        date = -1;
        month = -1;
        year = -1;
    }
    
    Date(int day, int month, int year) {
        this.date = day;
        this.month = month;
        this.year = year;
    }

    public void setDate(int date) {this.date = date;}
    public int getDate() {return  date;}

    public void setMonth(int month) {this.month = month;}
    public int getMonth() {return  month;}

    public void setYear(int year) {this.year = year;}
    public int getYear() {return  year;}
}
