package com.Humain_Ressources_Management.DAO;

import com.Humain_Ressources_Management.Models.Account;
import com.Humain_Ressources_Management.Models.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDataAccessService implements  AccountDAO{
    Employee employee;
    private Account account;
    private String query;
    private Connection connection;

    @Override
    public int AddAccount(String Email, String password, String isAdmin, int id_Emp) {
        try {
            query = "INSERT INTO account (Email, Password, isAdmin, id_Emp) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Email);
            statement.setString(2, password);
            statement.setString(3, isAdmin);
            statement.setInt(4, id_Emp);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int UpdateAccount(int id, String Email, String password, String isAdmin, int id_Emp) {
        try {
            query = "UPDATE account SET Email = ?, Password = ?, isAdmin = ?, id_Emp = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Email);
            statement.setString(2, password);
            statement.setString(3, isAdmin);
            statement.setInt(4, id_Emp);
            statement.setInt(5, id);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int DeleteAccount(int id) {
try {
            query = "DELETE FROM account WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public Account GetAccount(int id) {
        String query= "SELECT * FROM `account` WHERE `id` = '"+id+"'";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                account = new Account(rs.getInt("id"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("isAdmin"),
                        rs.getInt("id_Emp"));
                return account;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int login(String Email, String password) {
        String query= "SELECT * FROM `account` WHERE `Email` = '"+Email+"' AND `Password` = '"+password+"'";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int ChangePassword(int id, String password) {
        try {
            query = "UPDATE account SET Password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, password);
            statement.setInt(2, id);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int ChangeEmail(int id, String Email) {
        try {
            query = "UPDATE account SET Email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Email);
            statement.setInt(2, id);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int signup(Account account, Employee employee) {
        try {
            query = "INSERT INTO employee (Name, Last_Name, Title, Address, Telephone, DateNaissance, Salary, Hiring_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getTitle());
            statement.setString(4, employee.getAddress());
            statement.setString(5, employee.getTelephone());
            statement.setString(6, employee.getDateNaissance());
            statement.setDouble(7, employee.getSalary());
            statement.setString(8, employee.getHiring_date());
            statement.executeUpdate();
            query = "SELECT * FROM `employee` WHERE `Name` = '"+employee.getName()+"' AND `Last_Name` = '"+employee.getLast_name()+"'";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                employee.setId(rs.getInt("id"));
            }
            query = "INSERT INTO account (Email, Password, isAdmin, id_Emp) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getIsAdmin());
            statement.setInt(4, employee.getId());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public void connect() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrm","root","");
    }
}
