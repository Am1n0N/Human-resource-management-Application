package com.hrm.DAO;

import com.hrm.Models.Account;
import com.hrm.Models.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class EmployeeDataAccessService implements EmployeeDAO {
    private List<Employee> employees;
    private Employee employee;
    private String query;
    private Connection connection;


    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public void connect() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrm","root","");
    }

    @Override
    public int AddEmployee(String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  Double salary, String hiring_date) {
        try {
            query = "INSERT INTO employee (name, last_name, NIN, title, address, telephone, dateNaissance, salary, hiring_date) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, NIN);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, telephone);
            preparedStatement.setString(7, dateNaissance);
            preparedStatement.setDouble(8, salary);
            preparedStatement.setString(9, hiring_date);
            preparedStatement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public Employee getEmployee(int id) {
        String query= "SELECT * FROM `employee` WHERE `id` = '"+id+"'";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                employee = new Employee(rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Last_Name"),
                        rs.getString("NIN"),
                        rs.getString("Title"),
                        rs.getString("Address"),
                        rs.getString("Telephone"),
                        rs.getString("DateNaissance"),
                        rs.getDouble("Salary"),
                        rs.getString("Hiring_date"));
            }
            return employee;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Employee> getEmployees() {
        String query= "SELECT * FROM `employee`";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                employee = new Employee(rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Last_Name"),
                        rs.getString("NIN"),
                        rs.getString("Title"),
                        rs.getString("Address"),
                        rs.getString("Telephone"),
                        rs.getString("DateNaissance"),
                        rs.getDouble("Salary"),
                        rs.getString("Hiring_date"));
                employees.add(employee);
            }
            return employees;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int UpdateEmployee(int id, String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  Double salary, String hiring_date) {
        query = "UPDATE employee SET name = ?, Last_name = ?, NIN = ? ,Title = ?, Address = ?, Telephone = ?, DateNaissance = ?, Hiring_date = ?, Salary = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, last_name);
            statement.setString(3, NIN);
            statement.setString(4, title);
            statement.setString(5, address);
            statement.setString(6, telephone);
            statement.setString(7, dateNaissance);
            statement.setString(8, hiring_date);
            statement.setDouble(9, salary);
            statement.setInt(10, id);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int DeleteEmployee(int id) {
        query = "DELETE FROM `employee` WHERE `employee`.`id` = '"+id+"'";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.executeUpdate();
            return 1;
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public Account getAccount(int id) {

        String query= "SELECT * FROM `account` WHERE `id_Emp` = '"+id+"'";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getInt("id"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("isAdmin"),
                        rs.getInt("id_Emp"));
                return account;
            }
            return null;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Employee SearchEmployee(String name) {
        String query= "SELECT * FROM `employee` WHERE `Name` = '"+name+"'";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                employee = new Employee(rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Last_Name"),
                        rs.getString("NIN"),
                        rs.getString("Title"),
                        rs.getString("Address"),
                        rs.getString("Telephone"),
                        rs.getString("DateNaissance"),
                        rs.getDouble("Salary"),
                        rs.getString("Hiring_date"));
            }
            return employee;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

}
