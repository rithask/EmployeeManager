package com.litmus7.employeemanager.util;

import java.time.LocalDate;

public class ValidationUtil {

    // TODO: unique id check
    public static boolean validateId(int id) {
        return id > 0;
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
