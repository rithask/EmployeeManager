package com.litmus7.employeemanager.dao;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private static final String INSERT_EMPLOYEE =
        "INSERT INTO employees (id, first_name, last_name, mobile_number, email, joining_date, active_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_EMPLOYEES =
        "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employees";
    private static final String SELECT_ONE_EMPLOYEE =
        "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employees WHERE id = ?";
    private static final String UPDATE_EMPLOYEE =
        "UPDATE employees SET first_name = ?, last_name = ?, mobile_number = ?, email = ?, joining_date = ?, active_status = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";

    public static boolean createEmployee(Employee emp) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)
        ) {
            preparedStatement.setInt(1, emp.getId());
            preparedStatement.setString(2, emp.getFirstName());
            preparedStatement.setString(3, emp.getLastName());
            preparedStatement.setString(4, emp.getMobileNo());
            preparedStatement.setString(5, emp.getEmail());
            preparedStatement.setObject(6, emp.getJoiningDate());
            preparedStatement.setBoolean(7, emp.isActive());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String mobile_number = rs.getString("mobile_number");
                String email = rs.getString("email");
                LocalDate joining_date = rs.getObject("joining_date", LocalDate.class);
                boolean active_status = rs.getBoolean("active_status");
                employees.add(
                    new Employee(id, first_name, last_name, mobile_number, email, joining_date, active_status)
                );
            }
            System.out.println("here");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employees;
    }

    public static Employee getEmployeeById(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_EMPLOYEE)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("mobile_number"),
                    rs.getString("email"),
                    rs.getObject("joining_date", LocalDate.class),
                    rs.getBoolean("active_status")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean updateEmployee(Employee emp) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)
        ) {
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getMobileNo());
            preparedStatement.setString(4, emp.getEmail());
            preparedStatement.setObject(5, emp.getJoiningDate());
            preparedStatement.setBoolean(6, emp.isActive());
            preparedStatement.setInt(7, emp.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean deleteEmployee(int id) {
        try (
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
