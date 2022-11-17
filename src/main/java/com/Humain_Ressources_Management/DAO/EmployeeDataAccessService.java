package com.Humain_Ressources_Management.DAO;

import com.Humain_Ressources_Management.Models.Employee;


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
    public int AddEmployee(String name, String Last_name, String Title, String Address, String Telephone, String DateNaissance, String Hiring_date, double Salary) {
        try {
            query = "INSERT INTO employee (name, Last_name, Title, Address, Telephone, DateNaissance, Hiring_date, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, Last_name);
            statement.setString(3, Title);
            statement.setString(4, Address);
            statement.setString(5, Telephone);
            statement.setString(6, DateNaissance);
            statement.setString(7, Hiring_date);
            statement.setDouble(8, Salary);
            statement.executeUpdate();
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
    public int UpdateEmployee(int id, String name, String Last_name, String Title, String Address, String Telephone, String DateNaissance, String Hiring_date, double Salary) {
        query = "UPDATE `employee` SET `Name` = '"+name+"', `Last_Name` = '"+Last_name+"', `Title` = '"+Title+"', `Salary` = '"+Salary+"', `DateNaissance` = '"+DateNaissance+"', `Telephone` = '"+Telephone+"', `Address` = '"+Address+"', `Hiring_date` = '"+Hiring_date+"' WHERE `employee`.`id` = '"+id+"'";
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

}
