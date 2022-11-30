package com.hrm.Controllers;

import com.hrm.DAO.ContractDAO;
import com.hrm.Models.Contract;

public class ContractController {


    public ContractController(ContractDAO contractDAO) {
        this.contractDAO = contractDAO;
        try {
            contractDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final ContractDAO contractDAO;


    public int AddContract(Contract contract) {
        return contractDAO.addContract(contract);
    }

    public int UpdateContract(Contract contract) {
        return contractDAO.updateContract(contract);
    }

    public int DeleteContract(int id) {
        return contractDAO.deleteContract(id);
    }

    public Contract getContractById(int id) {
        return contractDAO.getContractById(id);
    }

    public Contract GetContractByEmpId(int id_Emp) {
        return contractDAO.getContractByEmployeeId(id_Emp);
    }

}
