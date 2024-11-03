/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CITIZEN;

import it2c.geonzon.bdcism.config;
import java.util.Scanner;

/**
 *
 * @author ENTAC
 */
public class approval {
        config db = new config();
        Scanner sc = new Scanner(System.in);
    public void approve(){
        boolean res = true;
        
        do{
            System.out.println("Approval");
            System.out.println("-------------------------------------");
            System.out.println("1. View Request");
            System.out.println("2. Approval");
            System.out.println("3. Exit");
            System.out.println("-------------------------------------");
            
            System.out.println("Enter Action: ");
            int choice = sc.nextInt();
               request reqs = new request();
               approval apv = new approval();
            switch(choice){
                case 1:
                    reqs.viewreq();
                    break;
                case 2:
                    reqs.viewreq();
                    apv.apvl();
                    break;
                case 3:
                    res = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            
            
            
        }while(res);
    }
    
    private void apvl(){
        
        System.out.println("\n");
        
        String reid = "";
        boolean idexist = false;
        
        while(!idexist){
            System.out.print("\nEnter Request ID:");
            reid = sc.next();
            
            if(db.reqIdExists(reid)){
                idexist = true;
            }else{
                System.out.println("Invalid ID or ID not Existed");
            }
        }
        
        System.out.println("Approval Status(Approve/Declined/Pending):");
        String stat = sc.next();
     
        db.upnotif(reid,stat);
        
        
    }
}
