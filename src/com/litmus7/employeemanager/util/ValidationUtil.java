package com.litmus7.employeemanager.util;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ValidationUtil {

    private static final Set<Integer> usedIds = new HashSet<>();

    public static boolean validateId(int id) {
        if (id <= 0 || usedIds.contains(id)) {
            return false;
        }
        usedIds.add(id);
        return true;
    }

    public static boolean validateName(String name) {
        return name != null && !name.isBlank() && name.matches("^[A-za-z\\s'-]{2,50}$");
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isBlank()) return false;
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|in|org|net)$");
    }

    public static boolean validateMobileNo(String number) {
        if (number == null) return false;
        return number.matches("^[1-9][0-9]{9}$");
    }

    public static boolean validateJoiningDate(LocalDate date) {
        if (date == null) return false;
        LocalDate lowerBound = LocalDate.of(2009, 7, 1);
        LocalDate today = LocalDate.now();
        return date.isAfter(lowerBound) && date.isBefore(today);
    }
}
