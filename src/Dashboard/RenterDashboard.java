
package Dashboard;

import Main.Main;
import Config.Conf;
import Dashboard.AdminDashboard;
import Dashboard.OwnerDashboard;
import Authentication.Authentication;


public class RenterDashboard {

    public void manageBooking() {
        
        AdminDashboard mu = new AdminDashboard();
        OwnerDashboard mc = new OwnerDashboard();
        
        int choice;
        do {
            
            
            System.out.println("============ RENTER DASHBOARD ============");
            System.out.println("1. Add Booking");
            System.out.println("2. View Booking");
            System.out.println("3. Update Booking");
            System.out.println("4. Delete Booking");
            System.out.println("5. Exit");
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
                    
                    viewBooking();    
                    updateBooking();
                    
                    break;
                case 4:
                    
                    viewBooking();
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
         
    Conf db = new Conf();
    System.out.println("\n===== AVAILABLE CONDO UNITS =====");
    String availableCondoQuery = "SELECT * FROM tbl_Condo WHERE C_status = 'Available'";
    String[] CondoHeaders = {"ID", "Condo Unit", "Condo Floor", "Condo Type", "Condo Size", "Condo Monthly Rate"};
    String[] CondoColumns = {"C_id", "C_unitn", "C_floor", "C_utype", "C_sqm", "C_mrate"};
    db.viewRecords(availableCondoQuery, CondoHeaders, CondoColumns);

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

    String sqlBooking = "INSERT INTO tbl_booking (U_id, C_id, B_start, B_end, B_total, B_stat) VALUES (?, ?, ?, ?, ?, ?)";
    db.addRecord(sqlBooking, Authentication.loggedInUserId, unitid, startDate, endDate, total, "Pending");

    System.out.println("Booking request submitted successfully! Waiting for owner approval.");
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
        
        // Get condo ID and status before deleting
        String getBooking = "SELECT C_id, B_stat FROM tbl_booking WHERE B_id = " + bid;
        java.util.List<java.util.Map<String, Object>> result = db.fetchRecords(getBooking);
        
        if(!result.isEmpty()){
            int condoId = (int) result.get(0).get("C_id");
            String status = (String) result.get(0).get("B_stat");
            
            // Delete the booking
            String sqlDelete = "DELETE FROM tbl_booking WHERE B_id = ?";
            db.deleteRecord(sqlDelete, bid);
            
            // If booking was confirmed, set condo back to Available
            if("Confirmed".equals(status)){
                String updateCondo = "UPDATE tbl_Condo SET C_status = ? WHERE C_id = ?";
                db.updateRecord(updateCondo, "Available", condoId);
            }
        }

        System.out.println("Booking deleted successfully!");
    }
}