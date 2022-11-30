package com.hrm.Models;

import java.util.ArrayList;

public class PTO_Record {
    private int id;
    private int ContractId;
    private int PtoUsed;
    private int PtoAvailable;
    private ArrayList<PTO> PTOs;

    public PTO_Record(int id, int contractId, int ptoUsed, int ptoAvailable, ArrayList<PTO> PTOs) {
        this.id = id;
        ContractId = contractId;
        PtoUsed = ptoUsed;
        PtoAvailable = ptoAvailable;
        this.PTOs = PTOs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractId() {
        return ContractId;
    }

    public void setContractId(int contractId) {
        ContractId = contractId;
    }

    public int getPtoUsed() {
        return PtoUsed;
    }

    public void setPtoUsed(int ptoUsed) {
        PtoUsed = ptoUsed;
    }

    public int getPtoAvailable() {
        return PtoAvailable;
    }

    public void setPtoAvailable(int ptoAvailable) {
        PtoAvailable = ptoAvailable;
    }

    public ArrayList<PTO> getPTOs() {
        return PTOs;
    }

    public void setPTOs(ArrayList<PTO> PTOs) {
        this.PTOs = PTOs;
    }
}
