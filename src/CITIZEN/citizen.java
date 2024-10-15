
package CITIZEN;

import it2c.geonzon.bdcism.IT2CGEONZONBDCIS;
import it2c.geonzon.bdcism.config;
import java.util.Scanner;


public class citizen {
    
   public void citizenTrans() {
         
         Scanner sc = new Scanner (System.in);
         
         String response;
         
    do{
        System.out.println("Welcome to Grading App");
        System.out.println("-------------------------------------");
        System.out.println("1. ADD");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        System.out.println("-------------------------------------");

        System.out.print("Enter Action: ");
        int act = sc.nextInt();
            citizen ap = new citizen();
        switch (act){
            case 1:
                ap.addCitizen();
            break;
            case 2:
                ap.viewCitizen();
            break;
            case 3:
                ap.viewCitizen();
                ap.updateCitizen();
            break;
            case 4:
                 ap.viewCitizen();
                 ap.deleteCitizen();
                 ap.viewCitizen();
                 break;
            case 5:
                System.out.println("Exiting...");
                
                 
        }
        
        System.out.println("Do you wamt to comtimue? (yes/no): ");
        response = sc.next();
        
    } while(response.equals("yes"));
         System.out.println("Thank you, See you! ");
         
     }
    
    
    
     public void addCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Citizen First Name: ");
        String fname = sc.next();
        System.out.print("Citizen Last Name: ");
        String lname = sc.next();  
        System.out.print("Citizen Contact Num: ");
        String contnum= sc.next();
        System.out.print("Citizen Age : ");
        String age = sc.next();
        System.out.println("Citizen Gender: ");
        String gender = sc.next();
        System.out.print("Citizen Purok : ");
        String purok = sc.next();
       
        

        String sql = "INSERT INTO Citizen (First_Name, Last_Name, ContactNum, Age, Gender, Purok)"
                + " VALUES (?, ?, ?, ?, ?, ?)";


        conf.addCitizen(sql, fname, lname, contnum, age, gender, purok);
        
    }
    private void viewCitizen() {
        String IT2CGEONZONBDCISQuery = "SELECT * FROM citizens";
        String[] IT2CGEONZONBDCISHeaders = {"ID", "First_Name", "Last_Name", "ContNum", "Age", "Gender", "Purok"};
        String[] IT2CGEONZONBDCISColumns = {"id", "fname", "lname", "contnum", "age", "gender", "purok"};

        config conf = new config ();
        conf.viewCitizen(IT2CGEONZONBDCISQuery, IT2CGEONZONBDCISHeaders, IT2CGEONZONBDCISColumns);
    }    
    private void updateCitizen() {
        Scanner sc = new Scanner (System.in);
        
        System.out.println("Enter Citizen ID: ");
        int id = sc.nextInt();
        System.out.println("\n");
        
        System.out.println("Enter new First Name: ");
        String uptfname = sc.next();
        
        System.out.println("Enter new Last Name: ");
        String uptlname = sc.next();
        
        System.out.println("Enter new Contact Num: ");
        String uptcontnum = sc.next();
        
        System.out.println("Enter new Age: ");
        String uptage = sc.next();
        
        System.out.println("Enter new Gender: ");
        String uptgender = sc.next();
        
        System.out.println("Enter new Purok: ");
        String uptpurok = sc.next();
        
        
        String sqlUpdate = "UPDATE students SET name = ? WHERE id = ?";

        config cf = new config();
        cf.updateRecord(sqlUpdate, uptfname, uptlname, uptcontnum, uptage, uptgender, uptpurok);    
      
      
    }
    
    private void deleteCitizen() {
       Scanner sc = new Scanner (System.in);
       
        System.out.println("Enter Citizen ID to delete: ");
        int id = sc.nextInt();
        
        String delete = "DELETE FROM citizen Where id = ";
        config cf = new config();
        cf.deleteCitizen(delete, id);
        
      
    }
    
     
}
