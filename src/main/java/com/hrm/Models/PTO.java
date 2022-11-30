package com.hrm.Models;

public class PTO {
    private int id;
    private int PtoId;
    private String Description;
    private String StartDate;
    private String EndDate;
    private String Status;


    public PTO(int id, int ptoId, String description, String startDate, String endDate, String status) {
        this.id = id;
        this.PtoId = ptoId;
        this.Description = description;
        this.StartDate = startDate;
        this.EndDate = endDate;
        this.Status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPtoId() {
        return PtoId;
    }

    public void setPtoId(int ptoId) {
        PtoId = ptoId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


}
