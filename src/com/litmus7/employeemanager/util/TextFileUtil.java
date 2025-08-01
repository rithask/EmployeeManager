package com.litmus7.employeemanager.util;

import com.litmus7.employeemanager.dto.Employee;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TextFileUtil {

    public static String[] readDataFromTextFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Reading failed!");
        }
        return lines.toArray(new String[lines.size()]);
    }

    public static boolean saveToCSV(Employee emp) {
        File file = new File("employees.csv");
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("ID,First Name,Last Name,Mobile Number,Email,Joining Date,Active\n");
            } catch (IOException e) {
                return false;
            }
        }
        return appendToCSV(emp);
    }

    public static boolean appendToCSV(Employee emp) {
        try (FileWriter fw = new FileWriter(new File("employees.csv"), true)) {
            String dataToAppend =
                emp.getId() +
                "," +
                emp.getFirstName() +
                "," +
                emp.getLastName() +
                "," +
                emp.getMobileNo() +
                "," +
                emp.getEmail() +
                "," +
                emp.getJoiningDate() +
                "," +
                emp.isActive() +
                "\n";
            fw.append(dataToAppend);
        } catch (IOException e) {
            System.out.println("Writing to file failed");
            return false;
        }
        return true;
    }
}
