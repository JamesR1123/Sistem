package Main;

import Config.Conf;
import java.util.Scanner;
import Authentication.Authentication;
import Dashboard.AdminDashboard;
import Dashboard.OwnerDashboard;
import Dashboard.RenterDashboard;


public class Main {
   
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        Conf db = new Conf();
        db.connectDB();
        
        Authentication au = new Authentication();
        
        System.out.println("WELCOME TO MY SYSTEM");
        System.out.println("1. Log-in");
        System.out.println("2. Register");
        System.out.println("3. Exit ");
        
        System.out.println("Enter Choice: ");
        int choice = Main.sc.nextInt();
        
        switch(choice){
        
            case 1: 
                  
                au.login();
                
                break;
            case 2: 
                  
                au.register();
                
                break;
            case 3: 
                  
                
                
                break;
        
        }
    }
}