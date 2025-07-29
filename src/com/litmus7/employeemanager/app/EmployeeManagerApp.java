package com.litmus7.employeemanager.app;

import com.litmus7.employeemanager.controller.*;
import java.util.Scanner;

public class EmployeeManagerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        while (userChoice != 4) {
            System.out.println();
            System.out.println("1. Read data from text file");
            System.out.println("2. Save data to CSV file");
            System.out.println("3. Enter your own data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();
            System.out.println();

            switch (userChoice) {
                case 1:
                    EmployeeController.processTextFile();
                    break;
                case 2:
                    EmployeeController.processCSVFile();
                    break;
                case 3:
                    EmployeeController.processUserInput();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
