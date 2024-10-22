package it2c.geonzon.bdcism;

import CITIZEN.citizen;
import static com.oracle.util.Checksums.update;
import java.util.Scanner;

public class IT2CGEONZONBDCIS {
    public static void main(String[] args) {
      
        Scanner sc = new Scanner (System.in);
        String response;
         
    do{
        System.out.println("Welcome to Citizen");
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
               
            break;
            case 3:
                
            break;
            case 4:
                
                 break;
            case 5:
                System.out.println("Exiting...");
                
                 
        }
        
        System.out.println("Do you want to continue? (yes/no): ");
        response = sc.next();
        
    } while(response.equals("yes"));
         System.out.println("Thank you, See you! ");
         
     }
        
        
        
        
    }
  
    






