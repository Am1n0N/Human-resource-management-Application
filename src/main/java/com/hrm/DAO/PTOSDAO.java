package com.hrm.DAO;

import com.hrm.Models.PTO;
import com.hrm.Models.PTO_Record;

import java.util.ArrayList;

public interface PTOSDAO extends DAO{
    public int addPTOS(PTO_Record PTORecord);
    public int getPTOused(int id);
    public int updatePTOS(PTO_Record PTORecord);
    public int deletePTOS(int id);
    public PTO_Record getPTOSById(int id);
    public ArrayList<PTO> getAllPTOS(int id);
    public PTO_Record getPTOSByContractId(int id);
    public int addPTO(PTO pto);
    public int updatePTO(PTO pto);
    public int deletePTO(int id);
    public PTO getPTOById(int id);
}
