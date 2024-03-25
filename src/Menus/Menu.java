package Menus;

public class Menu {
    private String menuName;
    private Food dessert;
    private Food mainDish;
    private Food sideDish;
    private Food drinks;
    private double totalPrice;

    public Menu(String menuName, Food main, Food drinks, Food side, Food dessert) {
        this.menuName = menuName;
        this.dessert = dessert;
        this.mainDish = main;
        this.sideDish = side;
        this.drinks = drinks;
    }

    public Menu(String menuName, Food main, Food drinks, Food side) {
        this.menuName = menuName;
        this.mainDish = main;
        this.sideDish = side;
        this.drinks = drinks;
    }

    public Menu(String menuName, Food main, Food drinks) {
        this.menuName = menuName;
        this.mainDish = main;
        this.drinks = drinks;
    }

    public void setMenuName(String menuName) {this.menuName = menuName;}
    public String getMenuName() {return menuName;}

    public void setMainDish(Food food) {this.mainDish = food;}
    public Food getMainDish() {return mainDish;}

    public void setSideDish(Food food) {this.sideDish = food;}
    public Food getSideDish() {return sideDish;}

    public void setDessert(Food food) {this.dessert = food;}
    public Food getDessert() {return dessert;}

    public void setDrinks(Food food) {this.drinks = food;}
    public Food getDrinks() {return drinks;}

    public void setTotalPrice(double price) {this.totalPrice = price;}
    public double getTotalPrice() {return totalPrice;}
    //There should be a method that computes the total bill
}
