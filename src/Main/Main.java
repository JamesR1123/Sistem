package Main;

import Config.Conf;
import java.util.Scanner;
import Services.manageUser;
import Services.manageCondo;
import Services.manageBooking;


public class Main {
    
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        Conf db = new Conf();
        db.connectDB();
        
        manageUser mu = new manageUser();
        manageCondo mc = new manageCondo();
        manageBooking bk = new manageBooking();
      

        int choice;
        
        do {
            System.out.println("\n===== Condo Rental System =====");
            System.out.println("1. Manage User");
            System.out.println("2. Manage Condo Unit");
            System.out.println("3. Add Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    
                    mu.manageUser();
                            
                    break;
                case 2:
                 
                    mc.manageCondo();
                    
                    break;
                case 3:
                   
                    bk.manageBooking();
                    
                    break;
                case 4:
                    
                    System.out.println("Exiting program...");
                    break;
                    
                default:
                    
                    System.out.println("Invalid choice. Try again.");
            }
            
        } while (choice != 4);
    }
}

        
    
    
    

