package com.litmus7.employeemanager.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
}
