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
public class request {
      config db = new config();
      Scanner sc = new Scanner(System.in);
    public void req(){
    
        boolean res = true;
        
        do{
            
            System.out.println("Request");
            System.out.println("-------------------------------------");
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");
            System.out.println("-------------------------------------");
            
            System.out.print("Enter Action: ");
            int action = sc.nextInt();
                request reqs = new request();
                citizen ap = new citizen();
            switch(action){
                case 1:
                    ap.viewCitizen();
                    reqs.addreq();
                    break;
                case 2:
                    reqs.viewreq();    
                    break;
                case 3:
                    reqs.viewreq();
                    reqs.updatereq();
                    break;
                case 4:
                    reqs.viewreq();
                    reqs.reqdelete();
                    break;
                case 5:
                    res = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            
        }while(res);
        
    }
    
    public void addreq(){
       
        System.out.println("\n");
        
        String ctid = "";
        boolean idexist = false;
        
        while(!idexist){
            System.out.print("\nEnter Cituzen ID:");
            ctid = sc.next();
            
            if(db.citIdExists(ctid)){
                idexist = true;
            }else{
                System.out.println("Invalid ID or ID not Existed");
            }
        }
        
        System.out.print("Enter Document Request: ");
        sc.nextLine(); 
        String docreq = sc.nextLine();
        
        String sql = "INSERT INTO tbl_request (ctzn_id,doc_type,req_date,req_status)"
                + "VALUES (?,?,current_timestamp,'Pending')";
        
        db.add(sql, ctid, docreq);
    }
    
    public void viewreq() {
    String viewquery = "SELECT r.req_id, z.f_name,z.l_name, r.doc_type, r.req_date, r.req_status FROM tbl_request r INNER JOIN tbl_citizen z ON r.ctzn_id = z.ctzn_id";
    String[] Header = {"ID", "Citizen Name","Citizen Last Name", "Document Type", "Date and Time", "Status"};
    String[] Column = {"req_id", "f_name","l_name", "doc_type", "req_date", "req_status"}; 

    db.view(viewquery, Header, Column);
    }
    
   private void updatereq(){
       
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
        
        System.out.print("Enter New Document Request:");
        sc.nextLine();
        String nwdoc = sc.nextLine();
        
        String query = "UPDATE tbl_request SET doc_type = ? WHERE req_id = ?";
        
        db.update(query, nwdoc, reid); 
   }
   
   private void reqdelete(){
    
        System.out.print("Enter Citizen ID to delete: ");
        int id = sc.nextInt();
        
        String delete = "DELETE FROM tbl_request Where req_id = ? ";
        db.delete(delete, id);
        
   }

}
