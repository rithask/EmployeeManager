package com.litmus7.employeemanager.app;

import com.litmus7.employeemanager.controller.*;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeController controller = new EmployeeController();
        int userChoice = 0;
        
        while (userChoice != 4) {
            System.out.println();
            System.out.println("1. Read data from text file and print");
            System.out.println("2. Save data to CSV file");
            System.out.println("3. Enter your own data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();
            System.out.println();

            switch (userChoice) {
                case 1:
                    List<Employee> employees = controller.getDataFromTextFile("employees.txt");
                    for (Employee emp : employees) {
                        System.out.println("ID:\t\t" + emp.getId());
                        System.out.println("First Name:\t" + emp.getFirstName());
                        System.out.println("Last Name:\t" + emp.getLastName());
                        System.out.println("Mobile Number:\t" + emp.getMobileNo());
                        System.out.println("Email Address:\t" + emp.getEmail());
                        System.out.println("Joining Date:\t" + emp.getJoiningDate());
                        System.out.println("Active Status:\t" + emp.isActive());
                        System.out.println("------------------------------------");
                    }
                    break;
                case 2:
                    if (controller.saveDataFromTextFileToCSV("employees.txt", "employees.csv")) {
                        System.out.println("Successfully added to CSV file");
                    }
                    break;
                case 3:
                    Employee emp = readDataFromUser();
                    controller.saveDataToCSV("employees.csv", emp);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }

    private static Employee readDataFromUser() {
        Scanner sc = new Scanner(System.in);

        int id = 0;
        do {
            System.out.print("Enter the ID: ");
            try {
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID should be a number");
                continue;
            }
        } while (!ValidationUtil.validateId(id));

        String firstName;
        do {
            System.out.print("Enter the first name: ");
            firstName = sc.nextLine();
        } while (!ValidationUtil.validateName(firstName));

        String lastName;
        do {
            System.out.print("Enter the last name: ");
            lastName = sc.nextLine();
        } while (!ValidationUtil.validateName(lastName));

        Long mobileNo = 0L;
        do {
            System.out.print("Enter the 10-digit mobile number: ");
            try {
                mobileNo = Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Mobile number should only consist of digits");
                continue;
            }
        } while (!ValidationUtil.validateMobileNo(mobileNo));

        String email;
        do {
            System.out.print("Enter the email: ");
            email = sc.nextLine();
        } while (!ValidationUtil.validateEmail(email));

        LocalDate joiningDate = null;
        do {
            System.out.print("Enter the joining date (YYYY-MM-DD): ");
            try {
                joiningDate = LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date");
                continue;
            }
        } while (!ValidationUtil.validateJoiningDate(joiningDate));

        System.out.print("Is the employee active (true/false): ");
        boolean activeStatus = Boolean.parseBoolean(sc.nextLine());

        Employee emp = new Employee(id, firstName, lastName, mobileNo, email, joiningDate, activeStatus);

        return emp;
    }
}
