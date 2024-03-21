package Menus;

public class Food {
    public String foodName;
    public double price;
    public Stocks stock;

    public Food() {
        this.foodName = null;
        this.price = 0;
    }

    public Food(String name, double price) {
        this.foodName = name;
        this.price = price;
    }
}
