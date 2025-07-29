package com.litmus7.employeemanager.util;

import java.time.LocalDate;

public class ValidationUtil {

    // TODO: unique id check
    public boolean validateId(int id) {
        return id > 0;
    }

    public boolean validateName(String name) {
        return !name.isBlank();
    }

    // TODO: basic format check
    public boolean validateEmail(String email) {
        return !email.isBlank();
    }

    public boolean validateMobileNo(int number) {
        return String.valueOf(number).length() == 10 && number > 0;
    }

    public boolean validateJoiningDate(LocalDate date) {
        return date.isAfter(LocalDate.parse("2009-06-30")) && date.isBefore(LocalDate.now());
    }
}
