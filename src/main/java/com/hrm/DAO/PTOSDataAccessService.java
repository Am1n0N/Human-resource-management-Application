package com.hrm.DAO;

import com.hrm.Models.PTO;
import com.hrm.Models.PTO_Record;

import java.sql.*;
import java.time.LocalDate;
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
                        ptoList.add(new PTO(resultSet1.getInt("id"), resultSet1.getInt("id_PTORecord"), resultSet1.getString("description"), LocalDate.parse(resultSet1.getString("startDate").toString()), LocalDate.parse(resultSet1.getString("endDate").toString()), resultSet1.getString("status")));
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
                    ptoList.add(new PTO(resultSet.getInt("id"), resultSet.getInt("id_PTORecord"), resultSet.getString("description"), LocalDate.parse(resultSet.getDate("startDate").toString()), LocalDate.parse(resultSet.getString("endDate").toString()), resultSet.getString("status")));
                } while (resultSet.next());
                return ptoList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ArrayList<PTO> getAllPTOSByStatus(String status) {
        try {
            query = "SELECT * FROM pto WHERE status = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ArrayList<PTO> ptoList = new ArrayList<>();
                do {
                    ptoList.add(new PTO(resultSet.getInt("id"), resultSet.getInt("id_PTORecord"), resultSet.getString("description"), LocalDate.parse(resultSet.getString("startDate").toString()), LocalDate.parse(resultSet.getString("endDate").toString()), resultSet.getString("status")));
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
        ArrayList<PTO> ptoList = new ArrayList<>();
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
                    do {
                        ptoList.add(new PTO(resultSet1.getInt("id"), resultSet1.getInt("id_PTORecord"), resultSet1.getString("description"), LocalDate.parse(resultSet1.getDate("startDate").toString()), LocalDate.parse(resultSet1.getString("endDate").toString()), resultSet1.getString("status")));
                    } while (resultSet1.next());
                    System.out.println("here"+ptoList);
                }
                return new PTO_Record(resultSet.getInt("id"), resultSet.getInt("ContractId"), resultSet.getInt("PtoUsed"), resultSet.getInt("PtoAvailable"), ptoList);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PTO_Record getPTOSByContractId(int id, String status) {
        ArrayList<PTO> ptoList = new ArrayList<>();
        try {
            query = "SELECT * FROM PTO_Record WHERE ContractId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                query = "SELECT * FROM pto WHERE id_PTORecord = ? AND status = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, resultSet.getInt("id"));
                statement.setString(2, status);
                ResultSet resultSet1 = statement.executeQuery();
                if (resultSet1.next()) {
                    do {
                        ptoList.add(new PTO(resultSet1.getInt("id"), resultSet1.getInt("id_PTORecord"), resultSet1.getString("description"), LocalDate.parse(resultSet1.getDate("startDate").toString()),LocalDate.parse(resultSet1.getString("endDate").toString()), resultSet1.getString("status")));
                    } while (resultSet1.next());
                }
                    return new PTO_Record(resultSet.getInt("id"), resultSet.getInt("ContractId"), resultSet.getInt("PtoUsed"), resultSet.getInt("PtoAvailable"), ptoList);
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int addPTO(PTO_Record Recordid,PTO pto) {
        //update PTO_Record and add PTO
        try {
            query = "INSERT INTO pto (id_PTORecord,description,startDate,endDate,status) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Recordid.getId());
            statement.setString(2, pto.getDescription());
            statement.setString(3, pto.getStartDate().toString());
            statement.setString(4, pto.getEndDate().toString());
            statement.setString(5, pto.getStatus());
            statement.executeUpdate();
            query = "UPDATE pto_record SET PtoUsed = ?, PtoAvailable=? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Recordid.getPtoUsed() + pto.getDays());
            statement.setInt(2, Recordid.getPtoAvailable() - pto.getDays());
            statement.setInt(3, Recordid.getId());
            statement.executeUpdate();
            return 1;
            } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int updatePTO(PTO pto) {
        try {
            query = "UPDATE pto SET id = ?, description = ?, startDate = ?, endDate = ?, status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pto.getPtoId());
            statement.setString(2, pto.getDescription());
            statement.setDate(3, Date.valueOf(pto.getStartDate()));
            statement.setDate(4, Date.valueOf(pto.getEndDate()));
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
                return new PTO(resultSet.getInt("id"), resultSet.getInt("id_PTORecord"), resultSet.getString("description"), LocalDate.parse(resultSet.getDate("startDate").toString()), LocalDate.parse(resultSet.getString("endDate").toString()), resultSet.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int ApprovePTO(int Recordid,PTO pto) {
        try {
            //update PTO_Record
            query = "SELECT * FROM pto_record WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Recordid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ptoUsed = resultSet.getInt("PtoUsed");
                int ptoAvailable = resultSet.getInt("PtoAvailable");
                query = "UPDATE pto_record SET PtoUsed = ?, PtoAvailable = ? WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, ptoUsed + pto.getDays());
                statement.setInt(2, ptoAvailable - pto.getDays());
                statement.setInt(3, Recordid);
                statement.executeUpdate();
            }
            //update PTO
            query = "UPDATE pto SET status = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, "Approved");
            statement.setInt(2, pto.getId());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int RejectPTO(int Recordid,PTO pto) {
        try {
            //update PTO
            query = "UPDATE pto SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Rejected");
            statement.setInt(2, pto.getPtoId());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
            return -1;
    }

    @Override
    public int getPendingPTOsCount() {
        try {
            query = "SELECT COUNT(*) FROM pto WHERE status = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Pending");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
