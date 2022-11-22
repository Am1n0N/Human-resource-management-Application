package com.hrm.DAO;

import com.hrm.Models.Contract;

import java.util.List;

public interface ContractDAO {
    public int addContract(Contract contract);
    public int updateContract(Contract contract);
    public int deleteContract(int id);
    public Contract getContractById(int id);
    public Contract getContractByEmployeeId(int id);
}
