package com.litmus7.employeemanager.dto;

import java.time.LocalDate;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String email;
    private LocalDate joiningDate;
    private boolean active;

    public Employee() {}

    public Employee(
        int id,
        String firstName,
        String lastName,
        String mobileNo,
        String email,
        LocalDate joiningDate,
        boolean active
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.joiningDate = joiningDate;
        this.active = active;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getJoiningDate() {
        return this.joiningDate;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
