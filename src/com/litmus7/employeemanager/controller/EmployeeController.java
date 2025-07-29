package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
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
        Employee emp = new Employee();

        System.out.print("Enter the ID: ");
        emp.setId(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter the first name: ");
        emp.setFirstName(sc.nextLine());
        System.out.print("Enter the last name: ");
        emp.setLastName(sc.nextLine());
        System.out.print("Enter the mobile number: ");
        emp.setMobileNo(Long.parseLong(sc.nextLine()));
        System.out.print("Enter the email: ");
        emp.setEmail(sc.nextLine());
        System.out.print("Enter the joining date (YYYY-MM-DD): ");
        emp.setJoiningDate(LocalDate.parse(sc.nextLine()));
        System.out.print("Is the employee active (true/false): ");
        emp.setActive(Boolean.parseBoolean(sc.nextLine()));

        if (TextFileUtil.saveToCSV(emp)) {
            System.out.println("Added to CSV file successfully");
        } else {
            System.out.println("Adding failed");
        }
    }
}
