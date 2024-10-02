package it2c.geonzon.bdcis;

import java.util.Scanner;

public class IT2CGEONZONBDCIS {

  
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
        
     public static void main (String [] args) {
         
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
            
        
        switch (act){
            case 1:
                IT2CGEONZONBDCIS ap = new IT2CGEONZONBDCIS();
                ap.addCitizen();
            break;
        }
        
        System.out.println("Do you wamt to comtimue? (yes/no): ");
        response = sc.next();
        
    } while(response.equals("yes"));
         System.out.println("Thank you, See you! ");
         
     }
         


}



