package com.hrm.Controllers;

import com.hrm.DAO.PTOSDAO;
import com.hrm.Models.PTO;
import com.hrm.Models.PTO_Record;

import java.util.ArrayList;

public class PTOSController {

    private final PTOSDAO ptosDAO;

    public PTOSController(PTOSDAO ptosDAO) {

        this.ptosDAO = ptosDAO;
        try {
            ptosDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void finalize() throws Throwable {
        try {
            ptosDAO.close();
        } finally {
            super.finalize();
        }
    }

    public int AddPTO_Record(PTO_Record PTORecord) {
        return ptosDAO.addPTOS(PTORecord);
    }

    public int UpdatePTO_Record(PTO_Record PTORecord) {
        return ptosDAO.updatePTOS(PTORecord);
    }

    public int DeletePTO_Record(int id) {
        return ptosDAO.deletePTOS(id);
    }

    public PTO_Record getPTO_RecordById(int id) {
        return ptosDAO.getPTOSById(id);
    }

    public PTO_Record getPTO_RecordByContractId(int id) {
        return ptosDAO.getPTOSByContractId(id);
    }

    public int AddPTO(PTO_Record Recordid,PTO pto) {
        return ptosDAO.addPTO(Recordid,pto);
    }

    public int UpdatePTO(PTO pto) {
        return ptosDAO.updatePTO(pto);
    }

    public int DeletePTO(int id) {
        return ptosDAO.deletePTO(id);
    }

    public PTO getPTOById(int id) {
        return ptosDAO.getPTOById(id);
    }

    public int getPTOused(int id) {
        return ptosDAO.getPTOused(id);
    }

    public ArrayList<PTO> getAllPTOS(int id) {
        return ptosDAO.getAllPTOS(id);
    }

    public int ApprovePTO(int Recordid,PTO pto) {
        return ptosDAO.ApprovePTO(Recordid,pto);
    }

    public int RejectPTO(int Recordid,PTO pto) {
        return ptosDAO.RejectPTO(Recordid,pto);
    }

    public PTO_Record getPTO_RecordByContractId(int id, String status) {
        return ptosDAO.getPTOSByContractId(id, status);
    }
    public ArrayList<PTO> getAllPTOSByStatus(String status) {
        return ptosDAO.getAllPTOSByStatus(status);
    }

    public int getPendingPTOsCount() {
        return ptosDAO.getPendingPTOsCount();
    }
}
