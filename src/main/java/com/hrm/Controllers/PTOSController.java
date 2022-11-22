package com.hrm.Controllers;

import com.hrm.DAO.PTOSDAO;
import com.hrm.Models.PTO;
import com.hrm.Models.PTOS;

import java.util.ArrayList;

public class PTOSController {

    private final PTOSDAO ptosDAO;

    PTOSController(PTOSDAO ptosDAO) {
        this.ptosDAO = ptosDAO;
    }

    public int AddPTOS(PTOS ptos) {
        return ptosDAO.addPTOS(ptos);
    }

    public int UpdatePTOS(PTOS ptos) {
        return ptosDAO.updatePTOS(ptos);
    }

    public int DeletePTOS(int id) {
        return ptosDAO.deletePTOS(id);
    }

    public PTOS getPTOSById(int id) {
        return ptosDAO.getPTOSById(id);
    }

    public PTOS getPTOSByContractId(int id) {
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
