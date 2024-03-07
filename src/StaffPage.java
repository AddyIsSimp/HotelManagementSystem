import java.util.Scanner;

public class StaffPage {
    private Scanner sc = new Scanner(System.in);
    private String choice = null;
    private Methods method = new Methods();

    public void StaffPage() {
        boolean isStaffPage = true;

        while(isStaffPage==true) {
            System.out.println("==========STAFF==========");
            System.out.println("[1] Room management");
            System.out.println("[2] Customer management");
            System.out.println("[3] Reservations");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("=====ROOM-MANAGEMENT======");
                    break;
                case "2":
                    System.out.println("=====CUSTOMER-MANAGEMENT======");
                    break;
                case "3":
                    System.out.println("=====RESERVATIONS======");
                    break;
                case "0":
                    System.out.println("Log-out successfully");
                    isStaffPage = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
                    boolean isCont = method.isContinue();
                    if (isCont==true) continue;
                    else break;
            }
        }
    }//End of StaffPage method
}
