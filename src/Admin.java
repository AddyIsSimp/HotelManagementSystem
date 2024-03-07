import java.util.Scanner;
public class Admin{
    private String userName = "Admin123";
    private String password = "admin123";
    private Methods method = new Methods();


    private String choice = null;
    private Scanner sc = new Scanner(System.in);

    Admin() {

    }

    public void AdminPage() {
        boolean isAdminPage = true;

        while(isAdminPage==true) {
            System.out.println("==========ADMIN==========");
            System.out.println("[1] Manage Accounts");
            System.out.println("[2] Manage Rooms");
            System.out.println("[3] Food Service");
            System.out.println("[4] Food Inventory");
            System.out.println("[5] Reservations");
            System.out.println("[6] Reports");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("=====MANAGE-ACCOUNTS======");
                    break;
                case "2":
                    System.out.println("=====MANAGE-ROOMS======");
                    break;
                case "3":
                    System.out.println("=====FOOD-SERVICE======");
                    break;
                case "4":
                    System.out.println("=====FOOD-INVENTORY======");
                    break;
                case "5":
                    System.out.println("=====RESERVATIONS======");
                    break;
                case "6":
                    System.out.println("=====REPORTS======");
                    break;
                case "0":
                    System.out.println("Log-out successfully");
                    isAdminPage=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    boolean isCont = method.isContinue();
                    if (isCont==true) continue;
                    else break;
            }
        }
    }//End of AdminPage method

    public String getUsername() {return userName;}
    public void setUsername(String s) {this.userName = s;}
    public String getPassword() {return password;}
    public void setPassword(String s) {this.password = s;}

}
