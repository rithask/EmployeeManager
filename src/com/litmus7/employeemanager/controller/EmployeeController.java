package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {

    public List<Employee> getDataFromTextFile(String filename) {
        String[] fileContent = TextFileUtil.readDataFromTextFile(filename);
        List<Employee> employees = new ArrayList<>();

        for (String s : fileContent) {

            String[] fields = s.split("\\$");

            int id = Integer.parseInt(fields[0].trim());
            String firstName = fields[1].trim();
            String lastName = fields[2].trim();
            long mobileNo = Long.parseLong(fields[3].trim());
            String email = fields[4].trim();
            LocalDate joiningDate = LocalDate.parse(fields[5].trim());
            boolean active = Boolean.parseBoolean(fields[6].trim());

            Employee emp = new Employee(id, firstName, lastName, mobileNo, email, joiningDate, active);
            employees.add(emp);
        }

        return employees;
    }

    public boolean saveDataFromTextFileToCSV(String inputFile, String outputFile) {
        List<Employee> employees = getDataFromTextFile(inputFile);

        for (Employee emp : employees) {
            if (!saveDataToCSV(outputFile, emp)) {
                return false;
            }
        }

        return true;
    }

    public boolean saveDataToCSV(String outputFile, Employee emp) {
        File f = new File(outputFile);
        boolean fileExists = f.isFile();
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(f, true))) {
            if (!fileExists) {
                pw.println("ID,First Name,Last Name,Mobile Number,Email,Joining Date,Active Status");
            }

            String dataToAppend = emp.getId() + "," + emp.getFirstName() + "," + emp.getLastName() + "," + emp.getMobileNo() + "," + emp.getEmail() + "," + emp.getJoiningDate() + "," + emp.isActive();
            pw.println(dataToAppend);

            return true;
        } catch (Exception e) {
            System.out.println("Saving to file failed");
        }

        return false;
    }    

    public boolean readAndSaveDataFromUser(String outputFile) {
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

        if(saveDataToCSV(outputFile, emp)) {
            System.out.println("Data added to CSV file successfully");
            return false;
        } else {
            System.out.println("Data adding failed");
        }

        return true;
    }
}
