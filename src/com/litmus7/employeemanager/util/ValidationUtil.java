package com.litmus7.employeemanager.util;

import java.time.LocalDate;
import java.util.ArrayList;

public class ValidationUtil {

    private static ArrayList<Integer> ids = new ArrayList<>();

    public static boolean validateId(int id) {
        boolean duplicate = ids.contains(id);
        if (!duplicate) {
            ids.add(id);
        }

        return id > 0 && !duplicate;
    }

    public static boolean validateName(String name) {
        return !name.isBlank();
    }

    public static boolean validateEmail(String email) {
        return email.matches("^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(.com)$") && !email.isBlank();
    }

    public static boolean validateMobileNo(Long number) {
        return String.valueOf(number).length() == 10 && number > 0;
    }

    public static boolean validateJoiningDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.parse("2009-06-30")) && date.isBefore(LocalDate.now());
    }
}
