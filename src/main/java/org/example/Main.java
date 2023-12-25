package org.example;

import java.util.Scanner;
import java.util.logging.Level;

import static org.example.Registration.logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void menu() {
        Registration ob = new Registration();
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
            ob.signupMenu();

        } else if (choice == 2) {
            ob.loginMenu();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m\n");
            menu();
        }

    }

    public static void main(String[] args) {
        menu();
    }}
