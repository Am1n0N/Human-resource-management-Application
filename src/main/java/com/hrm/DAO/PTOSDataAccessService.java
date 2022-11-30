package com.hrm.DAO;

import com.hrm.Models.PTO;
import com.hrm.Models.PTO_Record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PTOSDataAccessService implements PTOSDAO {
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
    public int addPTOS(PTO_Record PTORecord) {
        try {
            query = "INSERT INTO PTO_Record (ContractId, PtoUsed, PtoAvailable) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, PTORecord.getContractId());
            statement.setInt(2, PTORecord.getPtoUsed());
            statement.setInt(3, PTORecord.getPtoAvailable());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int getPTOused(int id) {
        try{
            query = "SELECT COUNT(*) FROM pto WHERE id_PTORecord = ? AND status = 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int updatePTOS(PTO_Record PTORecord) {
        try {
            query = "UPDATE PTO_Record SET ContractId = ?, PtoUsed = ?, PtoAvailable = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, PTORecord.getContractId());
            statement.setInt(2, PTORecord.getPtoUsed());
            statement.setInt(3, PTORecord.getPtoAvailable());
            statement.setInt(4, PTORecord.getId());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int deletePTOS(int id) {
        try {
            query = "DELETE FROM PTO_Record WHERE id = ?";
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
    public PTO_Record getPTOSById(int id) {
        try {
            query = "SELECT * FROM PTO_Record WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                query = "SELECT * FROM pto WHERE id_PTORecord = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, resultSet.getInt("id"));
                ResultSet resultSet1 = statement.executeQuery();
                if (resultSet1.next()) {
                    ArrayList<PTO> ptoList = new ArrayList<>();
                    do {
                        ptoList.add(new PTO(resultSet1.getInt("id"), resultSet1.getInt("id_PTORecord"), resultSet1.getString("description"), resultSet1.getString("startDate"), resultSet1.getString("endDate"), resultSet1.getString("status")));
                    } while (resultSet1.next());
                    return new PTO_Record(resultSet.getInt("id"), resultSet.getInt("ContractId"), resultSet.getInt("PtoUsed"), resultSet.getInt("PtoAvailable"), ptoList);
                }}
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ArrayList<PTO> getAllPTOS(int id) {
        try {
            query = "SELECT * FROM pto WHERE id_PTORecord = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ArrayList<PTO> ptoList = new ArrayList<>();
                do {
                    ptoList.add(new PTO(resultSet.getInt("id"), resultSet.getInt("id_PTORecord"), resultSet.getString("description"), resultSet.getString("startDate"), resultSet.getString("endDate"), resultSet.getString("status")));
                } while (resultSet.next());
                return ptoList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PTO_Record getPTOSByContractId(int id) {
        try {
            query = "SELECT * FROM PTO_Record WHERE ContractId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                query = "SELECT * FROM pto WHERE id_PTORecord = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, resultSet.getInt("id"));
                ResultSet resultSet1 = statement.executeQuery();
                if (resultSet1.next()) {
                    ArrayList<PTO> ptoList = new ArrayList<>();
                    do {
                        ptoList.add(new PTO(resultSet1.getInt("id"), resultSet1.getInt("id_PTORecord"), resultSet1.getString("description"), resultSet1.getString("startDate"), resultSet1.getString("endDate"), resultSet1.getString("status")));
                    } while (resultSet1.next());
                    return new PTO_Record(resultSet.getInt("id"), resultSet.getInt("ContractId"), resultSet.getInt("PtoUsed"), resultSet.getInt("PtoAvailable"), ptoList);
                }}
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int addPTO(PTO pto) {
        try {
            query = "INSERT INTO pto (id, description, startDate, endDate, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pto.getPtoId());
            statement.setString(2, pto.getDescription());
            statement.setString(3, pto.getStartDate());
            statement.setString(4, pto.getEndDate());
            statement.setString(5, pto.getStatus());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int updatePTO(PTO pto) {
        try {
            query = "UPDATE pto SET id = ?, description = ?, startDate = ?, endDate = ?, status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pto.getPtoId());
            statement.setString(2, pto.getDescription());
            statement.setString(3, pto.getStartDate());
            statement.setString(4, pto.getEndDate());
            statement.setString(5, pto.getStatus());
            statement.setInt(7, pto.getId());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int deletePTO(int id) {
        try {
            query = "DELETE FROM pto WHERE id = ?";
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
    public PTO getPTOById(int id) {
        try {
            query = "SELECT * FROM pto WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new PTO(resultSet.getInt("id"), resultSet.getInt("id_PTORecord"), resultSet.getString("description"), resultSet.getString("startDate"), resultSet.getString("endDate"), resultSet.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
