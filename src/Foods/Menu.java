package Foods;

public class Menu {
    private String menuName;
    private Food dessert;
    private Food mainDish;
    private Food sideDish;
    private Food drinks;
    private double totalPrice;

    public Menu(String menuName, Food main, Food side, Food drinks, Food dessert) {
        this.menuName = menuName;
        this.mainDish = main;
        this.sideDish = side;
        this.drinks = drinks;
        this.dessert = dessert;
        computeTotalPrice();
    }

    public Menu(String menuName, Food main, Food side, Food drinks) {
        this.menuName = menuName;
        this.mainDish = main;
        this.sideDish = side;
        this.drinks = drinks;
        this.dessert = null;
        computeTotalPrice();
    }

    public Menu(String menuName, Food main, Food drinks) {
        this.menuName = menuName;
        this.mainDish = main;
        this.drinks = drinks;
        this.sideDish = null;
        this.dessert = null;
        computeTotalPrice();
    }

    public Menu(Menu menu) {
        this.menuName = menu.getMenuName();
        this.mainDish = menu.getMainDish();
        this.sideDish = menu.getSideDish();
        this.drinks = menu.getDrinks();
        this.dessert = menu.getDessert();
        this.totalPrice = menu.getTotalPrice();
    }

    public Menu() {
        this.menuName = null;
        this.mainDish = null;
        this.sideDish = null;
        this.drinks = null;
        this.dessert = null;
        this.totalPrice = 0;
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

    public void computeTotalPrice() {
        if(this.dessert==null && this.sideDish==null) {
            totalPrice += mainDish.getPrice();
            totalPrice += drinks.getPrice();
        }else if(this.dessert==null) {
            totalPrice += mainDish.getPrice();
            totalPrice += sideDish.getPrice();
            totalPrice += drinks.getPrice();
        }else {
            totalPrice += mainDish.getPrice();
            totalPrice += sideDish.getPrice();
            totalPrice += drinks.getPrice();
            totalPrice += dessert.getPrice();
        }
    }

    public void setTotalPrice(double price) {this.totalPrice = price;}
    public double getTotalPrice() {return totalPrice;}
    //There should be a method that computes the total bill
}
