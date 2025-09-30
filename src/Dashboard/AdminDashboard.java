package Dashboard;

import Main.Main;
import Config.Conf;

public class AdminDashboard {
    
    public void manageUser(){
    
    int choice ;    
    
    do{    
        System.out.println("==========MANAGE USER==========");
        System.out.println("1.Add User: ");
        System.out.println("2.VIew User: ");
        System.out.println("3.Update User: ");
        System.out.println("4.Delete User: ");
        System.out.println("5.Back: ");
        
        System.out.println("Enter Choice: ");
        choice = Main.sc.nextInt();
        Main.sc.nextLine();
        
  
        switch(choice){
            
            case 1:
                
                addUser();
                
                break;
                
            case 2:
                
                viewUser();
                
                break;
                
            case 3:
                
                viewUser();
                updateUser();
                
                break;
                
            case 4:
                
                viewUser();
                deleteUser();
                
                break;
            
            case 5: 
                System.out.println("Going back...");
                
            default:
                System.out.println("Invalid Choice!!! Try Again");
           }
        }while(choice !=5);
    }
    
    
       
        
    
    public void addUser(){

        
        System.out.print("Enter Full Name: ");
        String nm = Main.sc.nextLine();

        System.out.print("Enter Role (1.Owner or 2.Renter): ");
        int choosetp = Main.sc.nextInt();
        
        String tp = (choosetp == 1) ? "Owner": "Renter";
        System.out.println("Successfully added type " +tp+"!");
        Main.sc.nextLine();
        
        System.out.print("Enter Email: ");
        String em = Main.sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String pn = Main.sc.nextLine();
        
        Conf db = new Conf();        
        String sqlUser = "INSERT INTO tbl_Users (U_name, U_type, U_phonenumber, U_email) VALUES (?, ?, ?, ?)";
        db.addRecord(sqlUser, nm, tp, em, pn);

        System.out.println("User record inserted successfully!");
   
    }
    
    public void viewUser(){
        
        Conf db = new Conf();
        String usersQuery = "SELECT * FROM tbl_Users";
        String[] usersHeaders = {"ID", "Name", "Type", "Phone", "Email"};
        String[] usersColumns = {"U_id", "U_name", "U_type", "U_phonenumber", "U_email"};
    
        db.viewRecords(usersQuery, usersHeaders, usersColumns);

    
    }
    
    public void updateUser(){
        
        System.out.println("Enter ID to update");
        int uid = Main.sc.nextInt();
        Main.sc.nextLine();
        
        System.out.println("Enter new Name: ");
        String nm = Main.sc.nextLine();
        
        System.out.println("Enter new Type (1.Owner or 2. Renter");
        int choosetp = Main.sc.nextInt();
        
        String newtp = (choosetp == 1) ? "Owner": "Renter";
        System.out.println("Update Successfully type " +newtp+"!");
        
        System.out.println("Enter new Phonenumber: ");
        String pn = Main.sc.nextLine();
        
        System.out.println("Enter new Email: ");
        String em = Main.sc.nextLine();
        
        Conf db = new Conf();
        String sqlUpdate = (" UPDATE tbl_Users SET U_name = ?, U_type = ?, U_phonenumber = ?, U_email = ? WHERE U_id = ? ");
        db.updateRecord(sqlUpdate, nm, newtp, pn, em, uid);
        
    }
    
    public void deleteUser(){
    
        System.out.println("Enter ID to delete: ");
        int did = Main.sc.nextInt();
        
        Conf db = new Conf();
        String sqlDelete = ("DELETE FROM tbl_Users WHERE U_id = ? ");
        db.deleteRecord(sqlDelete, did);
    
    }
    
    public void viewRentersOnly() {
    Conf db = new Conf();
    String Renterquery = "SELECT * FROM tbl_Users WHERE U_type = 'Renter'";
    String[] Renterheaders = {"ID", "Full Name", "Type", "Email", "Phone"};
    String[] Rentercolumns = {"U_id", "U_name", "U_type", "U_email", "U_phonenumber"};

    db.viewRecords(Renterquery, Renterheaders, Rentercolumns);
}
    
}

   

    

