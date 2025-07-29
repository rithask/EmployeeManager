package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {

    public static void processTextFile() {
        String[] fileContent = TextFileUtil.readFromTextFile("employees.txt");
        List<Employee> employees = new ArrayList<>();

        for (String s : fileContent) {
            employees.add(TextFileUtil.sanitizeDataFromTextFile(s));
        }

        for (Employee emp : employees) {
            TextFileUtil.printEmployeeData(emp);
            System.out.println();
        }
    }

    public static void processCSVFile() {
        String[] fileContent = TextFileUtil.readFromTextFile("employees.txt");
        List<Employee> employees = new ArrayList<>();

        for (String s : fileContent) {
            Employee emp = TextFileUtil.sanitizeDataFromTextFile(s);

            if (TextFileUtil.saveToCSV(emp)) {
                System.out.println("Added to CSV file successfully");
            } else {
                System.out.println("Adding failed");
            }
        }
    }

    public static void processUserInput() {
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
        if (TextFileUtil.saveToCSV(emp)) {
            System.out.println("Added to CSV file successfully");
        } else {
            System.out.println("Adding failed");
        }
    }
}
