package com.hrm.DAO;

import com.hrm.Models.PTO;
import com.hrm.Models.PTOS;

import java.util.ArrayList;
import java.util.List;

public interface PTOSDAO extends DAO{
    public int addPTOS(PTOS ptos);
    public int getPTOused(int id);
    public int updatePTOS(PTOS ptos);
    public int deletePTOS(int id);
    public PTOS getPTOSById(int id);
    public ArrayList<PTO> getAllPTOS(int id);
    public PTOS getPTOSByContractId(int id);
    public int addPTO(PTO pto);
    public int updatePTO(PTO pto);
    public int deletePTO(int id);
    public PTO getPTOById(int id);
}
