package com.hrm.Models;

import java.io.File;

public class Contract {
    private int id;
    private File pdf;
    private String Signdate;
    private int PtoLimit;
    private int EmployeeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getPdf() {
        return pdf;
    }

    public void setPdf(File pdf) {
        this.pdf = pdf;
    }

    public String getSigndate() {
        return Signdate;
    }

    public void setSigndate(String signdate) {
        Signdate = signdate;
    }

    public int getPtoLimit() {
        return PtoLimit;
    }

    public void setPtoLimit(int ptoLimit) {
        PtoLimit = ptoLimit;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public Contract(int id, File pdf, String Signdate, int PtoLimit, int EmployeeId) {
        this.id = id;
        this.pdf = pdf;
        this.Signdate = Signdate;
        this.PtoLimit = PtoLimit;
        this.EmployeeId = EmployeeId;
    }
    public Contract(File pdf, String Signdate, int PtoLimit, int EmployeeId){
        this.pdf = pdf;
        this.Signdate = Signdate;
        this.PtoLimit = PtoLimit;
        this.EmployeeId = EmployeeId;
    }
    public Contract(){}
}
