import java.util.Scanner;

public class CustomerPage {

    private Scanner sc = new Scanner(System.in);
    private String choice = null;
    private Methods method = new Methods();

    public void CustomerPage() {
        boolean isCustomerPage = true;

        while(isCustomerPage==true) {
            System.out.println("==========CUSTOMERS==========");
            System.out.println("[1] Account");
            System.out.println("[2] Rooms");
            System.out.println("[3] Reservation");
            System.out.println("[4] In-Hotel Orders");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("=====ACCOUNT======");
                    break;
                case "2":
                    System.out.println("=====ROOMS======");
                    break;
                case "3":
                    System.out.println("=====RESERVATIONS======");
                    break;
                case "4":
                    System.out.println("=====IN-HOTEL-ORDERS======");
                    break;
                case "0":
                    System.out.println("Log-out successfully");
                    isCustomerPage = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    boolean isCont = method.isContinue();
                    if (isCont==true) continue;
                    else break;
            }
        }
    }//End of AdminPage method
}
