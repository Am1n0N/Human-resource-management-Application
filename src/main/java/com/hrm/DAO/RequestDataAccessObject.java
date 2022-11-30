package com.hrm.DAO;

import com.hrm.Models.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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
        query = "INSERT INTO request (RequesterId, Title, Description, IsClosed) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, request.getRequesterId());
            statement.setString(2, request.getTitle());
            statement.setString(3, request.getDescription());
            statement.setBoolean(4, request.getClosed());
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
        query = "DELETE FROM request WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, request.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Override
    public void getRequest(Request request) {


    }

    @Override
    public ArrayList<Request> getRequests(int id) {
        query = "SELECT * FROM request WHERE RequesterId = ?";
        ArrayList<Request> requests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            var result = statement.executeQuery();
            while (result.next()) {
                requests.add(new Request(result.getInt("id"), result.getInt("RequesterId"),result.getString("Title"), result.getString("Description"), result.getBoolean("IsClosed")));
            }
            return requests;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
