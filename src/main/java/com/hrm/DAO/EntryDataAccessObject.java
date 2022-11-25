package com.hrm.DAO;

import com.hrm.Models.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryDataAccessObject implements EntryDAO {
    ArrayList<Entry> entries = new ArrayList<>();
    private String query;
    private Connection connection;
    @Override
    public void addEntry(Entry entry) {
    query = "INSERT INTO entry (employeeId, Title, Description, isPublic, allowComments,date) VALUES (?, ?,  ?, ?, ? , ?)";
    try {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, entry.getEmployeeId());;
        statement.setString(2, entry.getTitle());
        statement.setString(3, entry.getDescription());
        statement.setBoolean(4, entry.getPublic());
        statement.setBoolean(5, entry.getAllowComments());
        statement.setString(6, new Timestamp(System.currentTimeMillis()).toString());
        statement.executeUpdate();
    } catch (Exception e) {
        System.out.println(e);
    }

    }

    @Override
    public ArrayList<Entry> getEntries() {
        query = "SELECT * FROM entry";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            while (rs.next()) {
                Entry entry = new Entry();
                entry.setId(rs.getInt("id"));
                entry.setEmployeeId(rs.getInt("employeeId"));
                entry.setTitle(rs.getString("Title"));
                entry.setDescription(rs.getString("Description"));
                entry.setPublic(rs.getBoolean("isPublic"));
                entry.setAllowComments(rs.getBoolean("allowComments"));
                entry.setDate(rs.getString("date"));
                entries.add(entry);
            }
            return entries;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Entry getEntry(int id) {
        query = "SELECT * FROM entry WHERE id = ?";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs= preparedStmt.executeQuery();
            while (rs.next()) {
                Entry entry = new Entry();
                entry.setId(rs.getInt("id"));
                entry.setEmployeeId(rs.getInt("employeeId"));
                entry.setTitle(rs.getString("Title"));
                entry.setDescription(rs.getString("Description"));
                entry.setPublic(rs.getBoolean("isPublic"));
                entry.setAllowComments(rs.getBoolean("allowComments"));
                return entry;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void updateEntry(Entry entry) {

    }

    @Override
    public void deleteEntry(Entry entry) {

    }

    @Override
    public void getEntry(Entry entry) {

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
