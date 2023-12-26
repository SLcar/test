package org.example;

import java.util.Scanner;
import java.util.logging.Level;

import static org.example.Registration.logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {
    static Registration ob=new Registration();
    public static void menu() {
        SignIn ob1 = new SignIn();
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """
            
            \u001B[35m------ Welcome to Home Page ------
            |                                |
            |          1. Sign Up            |
            |          2. Login              |
            |          3. Exit               |
            |                                |
            ----------------------------------
            """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            signupMenu();
            menu();
        } else if (choice == 2) {
            ob1.loginMenu();
            menu();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m\n");
            menu();
        }

    }
    public static void signupMenu(){

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        logger.log(Level.INFO,"\u001B[35m"+"----- Welcome to signup Page -----");
        logger.log(Level.INFO,"Enter your name:");
        ob.setName(scanner.next());
        logger.log(Level.INFO,"Enter your Email:");
        ob.setEmail(scanner.next());
        logger.log(Level.INFO,"Enter your ID:");
        ob.setId(scanner.nextInt());
        logger.log(Level.INFO,"Enter your PhoneNumber:");
        ob.setPhone(scanner.nextInt());
        logger.log(Level.INFO,"Enter your Address e.g(Nablus/Palestine Street/Al Quds Bakery)");
        ob.setAddress(scanner1.nextLine());
        logger.log(Level.INFO,"Enter your Password:");
        ob.setPassword(scanner.next());
        logger.log(Level.INFO,"ConFirmed your Password:");
        ob.setComPassword(scanner.next());
        ob.searchIfEmailIsAlreadyExist(ob.getEmail());
        if(ob.isIfEmailFound()){
            logger.log(Level.WARNING,"This email is already exist");
            ob.returnEnterEmail();

        }
        else if(!ob.truepass(ob.getPassword(),ob.getComPassword())){
           ob.returnEnterPass(ob);
        }
        else {
            String dD =ob.getName()+"," +ob.getEmail() + "," + ob.getPassword() + "," + ob.getAddress() + "," + ob.getId()+","+ob.getPhone()+"\n";
            ob.storeDataToFile(dD);
            logger.log(Level.INFO, "The registration process was completed successfully");

        }
    }

    public static void main(String[] args) {
        menu();
    }}
