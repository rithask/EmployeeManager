package com.litmus7.employeemanager.app;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.ValidationUtil;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagerApp {

    public static void main(String[] args) {
        String inputFile;
        String outputFile;

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

            try {
                userChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            System.out.println();

            switch (userChoice) {
                case 1:
                    System.out.print("Enter the name of input file: ");
                    inputFile = scanner.nextLine();

                    List<Employee> employees = controller.getDataFromTextFile(inputFile);
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
                    System.out.print("Enter the name of input file: ");
                    inputFile = scanner.nextLine();
                    System.out.print("Enter the name of output file: ");
                    outputFile = scanner.nextLine();

                    if (controller.saveDataFromTextFileToCSV(inputFile, outputFile)) {
                        System.out.println("Employee data saved to CSV file successfully");
                    } else {
                        System.out.println("Failed to save employee data");
                    }
                    break;
                case 3:
                    System.out.print("Enter the name of output file: ");
                    outputFile = scanner.nextLine();

                    Employee emp = readDataFromUser(scanner);
                    if (controller.saveDataToCSV(outputFile, emp)) {
                        System.out.println("Employee data saved to CSV file successfully");
                    } else {
                        System.out.println("Failed to save employee data");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        scanner.close();
    }

    private static Employee readDataFromUser(Scanner sc) {
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

        String mobileNo = null;
        do {
            System.out.print("Enter the 10-digit mobile number: ");
            try {
                mobileNo = sc.nextLine();
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

        Boolean activeStatus = null;
        while (activeStatus == null) {
            System.out.print("Is the employee active (true/false): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true")) activeStatus = true;
            else if (input.equals("false")) activeStatus = false;
            else System.out.println("Invalid input. Please enter true or false.");
        }

        return new Employee(id, firstName, lastName, mobileNo, email, joiningDate, activeStatus);
    }
}
