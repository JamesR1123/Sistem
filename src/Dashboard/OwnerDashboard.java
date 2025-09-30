
package Dashboard;

import Main.Main;
import Config.Conf;

public class OwnerDashboard {
    
    public void manageCondo(){
    
    int choice;
    do{    
        System.out.println("==========MANAGE CONDO==========");
        System.out.println("1.Add Condo: ");
        System.out.println("2.VIew Condo: ");
        System.out.println("3.Update Condo: ");
        System.out.println("4.Delete Condo: ");
        System.out.println("5.Back: ");
        
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
                System.out.println("Going back...");
                
            default:
                System.out.println("Invalid Choice!!! Try Again");
              
            }
        }while(choice != 5);
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
        String sqlUser = "INSERT INTO tbl_Condo (C_unitn, C_floor, C_utype, C_sqm, C_mrate, C_status) VALUES (?, ?, ?, ?, ?, ?)";
        db.addRecord(sqlUser, un, cf, ty, cz, cmr, st);

        System.out.println("Condo record inserted successfully!");
   
    }
    
    public void viewCondo(){
        
        Conf db = new Conf();
        String CondoQuery = "SELECT * FROM tbl_Condo";
        String[] CondoHeaders = {"ID", "Condo Unit", "Condo Floor", "Condo Type", "Condo Size", "Condo Monthly Rate", "Condo Status"};
        String[] CondoColumns = {"C_id", "C_unitn", "C_floor", "C_utype", "C_sqm", "C_mrate", "C_status"};
    
        db.viewRecords(CondoQuery, CondoHeaders, CondoColumns);

    
    }
    
    public void updateCondo(){
        
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
    
}