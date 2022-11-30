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

    public int AddPTOS(PTO_Record PTORecord) {
        return ptosDAO.addPTOS(PTORecord);
    }

    public int UpdatePTOS(PTO_Record PTORecord) {
        return ptosDAO.updatePTOS(PTORecord);
    }

    public int DeletePTOS(int id) {
        return ptosDAO.deletePTOS(id);
    }

    public PTO_Record getPTOSById(int id) {
        return ptosDAO.getPTOSById(id);
    }

    public PTO_Record getPTOSByContractId(int id) {
        return ptosDAO.getPTOSByContractId(id);
    }

    public int AddPTO(PTO pto) {
        return ptosDAO.addPTO(pto);
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


}
