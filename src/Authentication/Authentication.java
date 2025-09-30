
package Authentication;

import Config.Conf;
import Dashboard.AdminDashboard;
import Dashboard.OwnerDashboard;
import Dashboard.RenterDashboard;
import Main.Main;

public class Authentication {
    public void register(){
        Main.sc.nextLine();
        
        System.out.print("Enter Username: ");
        String nm = Main.sc.nextLine();
        
        System.out.println("Enter your Password: ");
        String ps = Main.sc.nextLine();

        System.out.print("Choose Role (1.Admin / 2.Owner / 3.Renter): ");
        int chooserole = Main.sc.nextInt();
        
        String role = "";
        if(chooserole == 1){
            role = "Admin";
        }
        else if(chooserole == 2){ 
            role = "Owner";
        }else{
            role = "Renter";
        }
        Main.sc.nextLine();
            
        System.out.print("Enter Email: ");
        String em = Main.sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String pn = Main.sc.nextLine();
        
       
        
        Conf db = new Conf();        
        String sqlUser = "INSERT INTO tbl_Users (U_name, U_pass, U_role, U_phonenumber, U_email, U_status) VALUES (?, ?, ?, ?, ?, ?)";
        db.addRecord(sqlUser, nm, ps, role, pn, em, "Pending");

        System.out.println("User record inserted successfully!");
   
        
    }
    
public void login(){
        Main.sc.nextLine();
        
        System.out.print("Enter Username: ");
        String uname = Main.sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = Main.sc.nextLine();

        Conf db = new Conf();
        String role = db.login(uname, pass);

    if (role != null) {
        switch (role.toLowerCase()) {
            case "admin":
                AdminDashboard adminDash = new AdminDashboard();
                adminDash.manageUser();
                break;
            case "owner":
                System.out.println("Redirecting to Owner dashboard...");
                OwnerDashboard ownerDash = new OwnerDashboard();
                ownerDash.manageCondo();
                break;
            case "renter":
                System.out.println("Redirecting to Renter dashboard...");
                RenterDashboard renterDash = new RenterDashboard();
                renterDash.manageBooking(); 
            default:
                System.out.println("Unknown role. Access denied.");
            }
        }
    
    
    
    }
}
