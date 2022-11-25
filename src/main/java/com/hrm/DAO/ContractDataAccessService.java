package com.hrm.DAO;

import com.hrm.Models.Contract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContractDataAccessService implements ContractDAO {

    private Contract contract;
    private String query;
    private Connection connection;


    @Override
    public int addContract(Contract contract) {
        try {
            query="INSERT INTO contract (File,Signdate,PtoLimit,EmployeeId) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBinaryStream(1, new FileInputStream(contract.getPdf()),(int) contract.getPdf().length());
            statement.setString(2, contract.getSigndate());
            statement.setInt(3, contract.getPtoLimit());
            statement.setInt(4, contract.getEmployeeId());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
        }

    @Override
    public int updateContract(Contract contract) {
        try {
            query="UPDATE contract SET File = ?, Signdate = ?, PtoLimit = ?, EmployeeId = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBinaryStream(1, new FileInputStream(contract.getPdf()),(int) contract.getPdf().length());
            statement.setString(2, contract.getSigndate());
            statement.setInt(3, contract.getPtoLimit());
            statement.setInt(4, contract.getEmployeeId());
            statement.setInt(5, contract.getId());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int deleteContract(int id) {
        try {
            query="DELETE FROM contract WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }


    @Override
    public Contract getContractById(int id) {
        try {
            query = "SELECT * FROM contract WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                contract = new Contract();
                contract.setId(rs.getInt("id"));
                File file = new File("C:\\Users\\User\\Documents\\Contract"+id+".pdf");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(rs.getBytes("File"));
                contract.setSigndate(rs.getString("Signdate"));
                contract.setPtoLimit(rs.getInt("PtoLimit"));
                contract.setEmployeeId(rs.getInt("EmployeeId"));
                return contract;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    @Override
    public Contract getContractByEmployeeId(int id_Emp) {
        try {
            query = "SELECT * FROM contract WHERE EmployeeId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_Emp);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                contract = new Contract();
                contract.setId(rs.getInt("id"));
                File file = new File("C:\\Users\\User\\Documents\\Contract"+id_Emp+".pdf");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(rs.getBytes("File"));
                contract.setSigndate(rs.getString("Signdate"));
                contract.setPtoLimit(rs.getInt("PtoLimit"));
                contract.setEmployeeId(rs.getInt("EmployeeId"));
                return contract;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
}

