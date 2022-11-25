package com.hrm.DAO;

import com.hrm.Models.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RequestDataAccessObject implements RequestDAO {
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
    public void addRequest(Request request) {
        query = "INSERT INTO request (RequesterId, Title, Description) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, request.getRequesterId());
            statement.setString(2, request.getTitle());
            statement.setString(3, request.getDescription());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateRequest(Request request) {

    }

    @Override
    public void deleteRequest(Request request) {

    }

    @Override
    public void getRequest(Request request) {

    }
}
