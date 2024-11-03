package it2c.geonzon.bdcism;

import CITIZEN.approval;
import CITIZEN.citizen;
import CITIZEN.request;
import static com.oracle.util.Checksums.update;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IT2CGEONZONBDCIS {
    public static void main(String[] args) {
      
        Scanner sc = new Scanner (System.in);
        String response;
         
    do{
        System.out.println("Welcome to Barangay Citizen");
        System.out.println("-------------------------------------");
        System.out.println("1. CITIZEN");
        System.out.println("2. REQUEST");
        System.out.println("3. APPROVAL ");
        System.out.println("4. NOTIFICATIONS ");
        System.out.println("5. EXIT");
        System.out.println("-------------------------------------");

        System.out.print("Enter Action: ");
        int act = sc.nextInt();
            
        switch (act){
            case 1:
               citizen ap = new citizen();
               ap.citizenTrans();
            break;
            case 2:
               request req = new request();
               req.req();
            break;
            case 3:
                approval apv = new approval();
                apv.approve();
            break;
            case 4:
                IT2CGEONZONBDCIS mn = new IT2CGEONZONBDCIS();
                mn.notif();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default :
                System.out.println("Invalid Action");
                break;
                 
        }
        
        System.out.print("Do you want to continue? (yes/no): ");
        response = sc.next();
        
    } while(response.equals("yes"));
         System.out.println("Thank you, See you! ");
         
         
    }
    
    private void notif(){
        config db = new config();
        
        try{
            
            ResultSet res = db.getData("SELECT * FROM tbl_notif");
            while(res.next()){
                System.out.println(""+res.getString("logs"));
            }
            System.out.println("\n\n");
                  
        }catch(SQLException e){
            System.out.println(""+e);
        }
    }
        
        
        
        
}
  
    






