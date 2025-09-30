
package Dashboard;

import Main.Main;
import Config.Conf;
import Dashboard.AdminDashboard;
import Dashboard.OwnerDashboard;


public class RenterDashboard {

    public void manageBooking() {
        
        AdminDashboard mu = new AdminDashboard();
        OwnerDashboard mc = new OwnerDashboard();
        
        int choice;
        do {
            
            
            System.out.println("\n========== MANAGE BOOKING ==========");
            System.out.println("1. Add Booking");
            System.out.println("2. View Booking");
            System.out.println("3. Update Booking");
            System.out.println("4. Delete Booking");
            System.out.println("5. Back");
            System.out.print("Enter Choice: ");
            choice = Main.sc.nextInt();
            Main.sc.nextLine();

            switch (choice) {
                case 1:
                    
                    addBooking();
                    
                    break;
                case 2:
                    
                    viewBooking();
                    
                    break;
                case 3:
                    
                    updateBooking();
                    
                    break;
                case 4:
                    
                    deleteBooking();
                    
                    break;
                case 5:
                    
                    System.out.println("Going back...");
                    
                    break;
                    
                default:
                    System.out.println("Invalid choice!!! Try Again");
            }
        } while (choice != 5);
    }

   

     public void addBooking() {
         
   
    AdminDashboard mu = new AdminDashboard();
    System.out.println("\n===== RENTERS =====");
    mu.viewRentersOnly(); 

    System.out.print("Enter User ID from the list above: ");
    int uid = Main.sc.nextInt();


    OwnerDashboard mc = new OwnerDashboard();
    System.out.println("\n===== CONDO UNITS =====");
    mc.viewCondo();

    System.out.print("Enter Condo ID from the list above: ");
    int unitid = Main.sc.nextInt();
    Main.sc.nextLine();

    System.out.print("Enter Start Date (YYYY-MM-DD): ");
    String startDate = Main.sc.nextLine();

    System.out.print("Enter End Date (YYYY-MM-DD): ");
    String endDate = Main.sc.nextLine();

    System.out.print("Enter Total Amount: ");
    double total = Main.sc.nextDouble();
    Main.sc.nextLine();

    System.out.print("Enter Status (Confirmed/Pending): ");
    String status = Main.sc.nextLine();

    Conf db = new Conf();
    String sqlBooking = "INSERT INTO tbl_booking (U_id, C_id, B_start, B_end, B_total, B_stat) VALUES (?, ?, ?, ?, ?, ?)";
    db.addRecord(sqlBooking, uid, unitid, startDate, endDate, total, status);

    System.out.println("Booking record inserted successfully!");
}
    

    public void viewBooking() {
        
        Conf db = new Conf();
        String bookingQuery = "SELECT * FROM tbl_booking";
        String[] headers = {"ID", "UserID", "UnitID", "Start Date", "End Date", "Total", "Status"};
        String[] columns = {"B_id", "U_id", "C_id", "B_start", "B_end", "B_total", "B_stat"};
        db.viewRecords(bookingQuery, headers, columns);
    }

    public void updateBooking() {
        System.out.print("Enter Booking ID to update: ");
        int bid = Main.sc.nextInt();
        Main.sc.nextLine();

        System.out.print("Enter new Start Date (YYYY-MM-DD): ");
        String sd = Main.sc.nextLine();

        System.out.print("Enter new End Date (YYYY-MM-DD): ");
        String ed = Main.sc.nextLine();

        System.out.print("Enter new Total Amount: ");
        int tt = Main.sc.nextInt();
        Main.sc.nextLine();

        System.out.print("Enter new Status (Confirmed/Pending): ");
        String sts = Main.sc.nextLine();

        Conf db = new Conf();
        String sqlUpdate = "UPDATE tbl_booking SET B_start = ?, B_end = ?, B_total = ?, B_stat = ? WHERE B_id = ?";
        db.updateRecord(sqlUpdate, sd, ed, tt, sts, bid);

        System.out.println("✅ Booking updated successfully!");
    }

    public void deleteBooking() {
        System.out.print("Enter Booking ID to delete: ");
        int bid = Main.sc.nextInt();

        Conf db = new Conf();
        String sqlDelete = "DELETE FROM tbl_booking WHERE B_id = ?";
        db.deleteRecord(sqlDelete, bid);

        System.out.println("Booking deleted successfully!");
    }
}