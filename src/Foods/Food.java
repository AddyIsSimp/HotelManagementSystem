package Foods;

public class Food {

    protected String foodName;
    protected double price;
    protected int stocks;

    public Food() {
        this.foodName = null;
        this.price = 0;
    }

    public Food(String name, double price) {
        this.foodName = name;
        this.price = price;
    }

    public Food(String name, double price, int stock) {
        this.foodName = name;
        this.price = price;
        this.stocks = stock;
    }

    //STOCK MODIFIER
    public void addStock(int qty) {this.stocks += qty;}
    public void deleteStock(int qty) {
        if(this.stocks<qty) System.out.println("INVALID: Insufficient quantity of stocks. Available: " + this.stocks);
        else this.stocks -= qty;
    }
    public void editStock(int qty){this.stocks = qty;}

    //SETTER & GETTER
    public void setFoodName(String name) {this.foodName=foodName;}
    public String getFoodName() {return foodName;}

    public void setPrice(double price) {this.price=price;}
    public double getPrice() {return price;}

    public void setStocks(int stock) {this.stocks=stock;}
    public double getStocks() {return stocks;}

}
