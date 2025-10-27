
package Dashboard;

import Main.Main;
import Config.Conf;
import Authentication.Authentication;

public class OwnerDashboard {
    
    public void manageCondo(){
    
    int choice;
    do{    
        System.out.println("============ OWNER DASHBOARD ============");
        System.out.println("1.Add Condo: ");
        System.out.println("2.VIew Condo: ");
        System.out.println("3.Update Condo: ");
        System.out.println("4.Delete Condo: ");
        System.out.println("5.View Booking Requests: ");
        System.out.println("6.Exit: ");
        
        System.out.println("Enter Choice: ");
        choice = Main.sc.nextInt();
        Main.sc.nextLine();
        
        
        switch(choice){
            
            case 1:
                
                addCondo();
                
                break;
                
            case 2:
                
                viewCondo();
                
                break;
                
            case 3:
                
                updateCondo();
                viewCondo();
                
                break;
                
            case 4:
                
                deleteCondo();
                viewCondo();
                
                break;
                
            case 5:
                
                viewBookingRequests();
                
                break;
                
            case 6: 
                System.out.println("Going back...");
                
            default:
                System.out.println("Invalid Choice!!! Try Again");
              
            }
        }while(choice != 6);
    }
    
    
    public void addCondo(){
    
        
        System.out.print("Enter Unit Number: ");
        int un = Main.sc.nextInt();

        System.out.print("Enter Condo Floor: ");
        String cf = Main.sc.nextLine();
        Main.sc.nextLine(); 
        
        System.out.print("Enter Condo Type (1.Studio, 2.1BR, 3.2BR): ");
        int choosety = Main.sc.nextInt(); 
        Main.sc.nextLine();
        
        String ty = (choosety == 1) ? "Studio" 
        : (choosety == 2) ? "1BR" 
        : "2BR";
        
        System.out.println("Successfully added Condo type " + ty + "!");
                 
        System.out.print("Enter Condo Size (sqm): ");
        String cz = Main.sc.nextLine();
        
        System.out.print("Enter Condo Monthly Rate): ");
        String cmr = Main.sc.nextLine();
        
        System.out.print("Enter Condo Status (1.Available, 2.Occupied, 3.Maintenance): ");
        int choosest = Main.sc.nextInt();
        Main.sc.nextLine();

        String st = (choosest == 1) ? "Available" 
             : (choosest == 2) ? "Occupied" 
             : "Maintenance";

        System.out.println("Update Successfully status " + st + "!");
        
        
        Conf db = new Conf();        
        String sqlUser = "INSERT INTO tbl_Condo (C_unitn, C_floor, C_utype, C_sqm, C_mrate, C_status, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        db.addRecord(sqlUser, un, cf, ty, cz, cmr, st, Authentication.loggedInUserId);

        System.out.println("Condo record inserted successfully!");
   
    }
    
    public void viewCondo(){
        
        Conf db = new Conf();
        String CondoQuery = "SELECT * FROM tbl_Condo WHERE owner_id = " + Authentication.loggedInUserId;
        String[] CondoHeaders = {"ID", "Condo Unit", "Condo Floor", "Condo Type", "Condo Size", "Condo Monthly Rate", "Condo Status"};
        String[] CondoColumns = {"C_id", "C_unitn", "C_floor", "C_utype", "C_sqm", "C_mrate", "C_status"};
    
        db.viewRecords(CondoQuery, CondoHeaders, CondoColumns);

    
    }
    
    public void updateCondo(){
        
        System.out.println("Enter ID to update");
        int uid = Main.sc.nextInt();
        Main.sc.nextLine();
        
        System.out.print("Enter New Unit Number: ");
        int un = Main.sc.nextInt();

        System.out.print("Enter New Condo Floor: ");
        String cf = Main.sc.nextLine();
        
        System.out.print("Enter Type (1.Studio, 2.1BR, 3.2BR): ");
        int choosety = Main.sc.nextInt();
        Main.sc.nextLine();

        String ty = (choosety == 1) ? "Studio" 
        : (choosety == 2) ? "1BR" 
        : "2BR";
        System.out.println("Type set to " + ty + "!");

        System.out.print("Enter New Condo Size (sqm): ");
        String cz = Main.sc.nextLine();
        
        System.out.print("Enter New Condo Monthly Rate): ");
        String cmr = Main.sc.nextLine();
        
        System.out.print("Enter New Status (1.Available, 2.Occupied, 3.Maintenance): ");
        int choosest = Main.sc.nextInt();
        Main.sc.nextLine(); 

        String st = (choosest == 1) ? "Available"
        : (choosest == 2) ? "Occupied"
        : "Maintenance";
        
        Conf db = new Conf();
        String sqlUpdate = (" UPDATE tbl_Users SET C_unitf = ?, C_floor = ?, C_utype = ?, C_sqm = ?, C_mrate = WHERE U_id = ? ");
        db.updateRecord(sqlUpdate, cf, ty, cz, cmr, st, un);
        
    }
    
    public void deleteCondo(){
    
        System.out.println("Enter Condo ID to delete: ");
        int und = Main.sc.nextInt();
        
        Conf db = new Conf();
        String sqlDelete = ("DELETE FROM tbl_Condo WHERE C_id = ? ");
        db.deleteRecord(sqlDelete, und);
    
    }
    
    public void viewBookingRequests(){
        Conf db = new Conf();
        
        String bookingQuery = "SELECT b.B_id, u.U_name, c.C_unitn, b.B_start, b.B_end, b.B_total, b.B_stat " +
                              "FROM tbl_booking b " +
                              "JOIN tbl_Users u ON b.U_id = u.U_id " +
                              "JOIN tbl_Condo c ON b.C_id = c.C_id " +
                              "WHERE c.owner_id = " + Authentication.loggedInUserId + " AND b.B_stat = 'Pending'";
        
        String[] headers = {"Booking ID", "Renter Name", "Unit Number", "Start Date", "End Date", "Total", "Status"};
        String[] columns = {"B_id", "U_name", "C_unitn", "B_start", "B_end", "B_total", "B_stat"};
        
        System.out.println("\n===== PENDING BOOKING REQUESTS FOR YOUR CONDOS =====");
        db.viewRecords(bookingQuery, headers, columns);
        
        System.out.print("\nDo you want to approve/reject a booking? (1.Yes / 2.No): ");
        int choice = Main.sc.nextInt();
        Main.sc.nextLine();
        
        if(choice == 1){
            System.out.print("Enter Booking ID: ");
            int bookingId = Main.sc.nextInt();
            Main.sc.nextLine();
            
            System.out.print("Action (1.Approve / 2.Reject): ");
            int action = Main.sc.nextInt();
            Main.sc.nextLine();
            
            String newStatus = (action == 1) ? "Confirmed" : "Rejected";
            
            String sqlUpdate = "UPDATE tbl_booking SET B_stat = ? WHERE B_id = ?";
            db.updateRecord(sqlUpdate, newStatus, bookingId);
            
            // If approved, update condo status to Occupied
            if(action == 1){
                String getCondoId = "SELECT C_id FROM tbl_booking WHERE B_id = " + bookingId;
                java.util.List<java.util.Map<String, Object>> result = db.fetchRecords(getCondoId);
                if(!result.isEmpty()){
                    int condoId = (int) result.get(0).get("C_id");
                    String updateCondo = "UPDATE tbl_Condo SET C_status = ? WHERE C_id = ?";
                    db.updateRecord(updateCondo, "Occupied", condoId);
                }
            }
            
            System.out.println("Booking " + newStatus + "!");
        }
    }
    
}
