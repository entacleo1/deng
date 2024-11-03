
package CITIZEN;

import it2c.geonzon.bdcism.IT2CGEONZONBDCIS;
import it2c.geonzon.bdcism.config;
import java.util.Scanner;


public class citizen {
       Scanner sc = new Scanner (System.in);
       config conf = new config();
   public void citizenTrans() {
         
        boolean res = true;
         
    do{
        System.out.println("Citizen");
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
               res = false;
                break;
            default:
                System.out.println("INVALID OPTION");
                break;
        }
        
    } while(res);
     }
    
     public void addCitizen(){
      
        System.out.print("Citizen First Name: ");
        String fname = sc.next();
        
        System.out.print("Citizen Last Name: ");
        String lname = sc.next(); 
        
        String contnum;
        while (true) {
            System.out.print("Citizen Contact Num (11 digits): ");
            contnum = sc.next();
            if (contnum.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("Invalid contact number. Must be 11 digits and numeric.");
            }
        }

        String age;
        while (true) {
            System.out.print("Citizen Age: ");
            age = sc.next();
            if (age.matches("\\d+")) { 
                break;
            } else {
                System.out.println("Invalid age. Must be a number.");
            }
        }
        System.out.print("Citizen Gender: ");
        String gender = sc.next();
        
        System.out.print("Citizen Purok : ");
        String purok = sc.next();
       
        String sql = "INSERT INTO tbl_citizen (f_name,l_name,contact,age,gender,purok)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        conf.add(sql, fname, lname, contnum, age, gender, purok);
       
    }
     
    public void viewCitizen() {
        String IT2CGEONZONBDCISQuery = "SELECT * FROM tbl_citizen";
        String[] IT2CGEONZONBDCISHeaders = {"ID", "First_Name", "Last_Name", "ContNum", "Age", "Gender", "Purok"};
        String[] IT2CGEONZONBDCISColumns = {"ctzn_id", "f_name", "l_name", "contact", "age", "gender", "purok"};

        conf.view(IT2CGEONZONBDCISQuery, IT2CGEONZONBDCISHeaders, IT2CGEONZONBDCISColumns);
    }    
    
    private void updateCitizen() {

        System.out.print("Enter Citizen ID: ");
        int id = sc.nextInt();
        System.out.println("\n");
        
        System.out.print("Enter new First Name: ");
        String uptfname = sc.next();
        
        System.out.print("Enter new Last Name: ");
        String uptlname = sc.next();
        
       String uptcontnum;
        while (true) {
            System.out.print("Enter New Citizen Contact Num (11 digits): ");
            uptcontnum = sc.next();
            if (uptcontnum.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("Invalid contact number. Must be 11 digits and numeric.");
            }
        }

        String uptage;
        while (true) {
            System.out.print("Enter New Citizen Age: ");
            uptage = sc.next();
            if (uptage.matches("\\d+")) { 
                break;
            } else {
                System.out.println("Invalid age. Must be a number.");
            }
        }
        
        System.out.print("Enter new Gender: ");
        String uptgender = sc.next();
        
        System.out.print("Enter new Purok: ");
        String uptpurok = sc.next();
        
        
        String sqlUpdate = "UPDATE tbl_citizen SET f_name = ?, l_name = ?,contact = ?, age = ?,gender = ?,purok = ? WHERE ctzn_id = ?";

        conf.update(sqlUpdate, uptfname, uptlname, uptcontnum, uptage, uptgender, uptpurok, id);
        
    }
    
    private void deleteCitizen() {
       Scanner sc = new Scanner (System.in);
       
        System.out.print("Enter Citizen ID to delete: ");
        int id = sc.nextInt();
        
        String delete = "DELETE FROM tbl_citizen Where ctzn_id = ? ";
        conf.delete(delete, id);
        
    }
    
     
}
