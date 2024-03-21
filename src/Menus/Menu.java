package Menus;

public class Menu {
    public Food dessert;
    public Food mainDish;
    public Food sideDish;
    public Food drinks;
    public double totalPrice;

    public Menu(Food main, Food drinks, Food side, Food dessert) {
        this.dessert = dessert;
        this.mainDish = main;
        this.sideDish = side;
        this.drinks = drinks;
    }

    public Menu(Food main, Food drinks, Food side) {
        this.mainDish = main;
        this.sideDish = side;
        this.drinks = drinks;
    }

    public Menu(Food main, Food drinks) {
        this.mainDish = main;
        this.drinks = drinks;
    }

    //There should be a method that computes the total bill
}
