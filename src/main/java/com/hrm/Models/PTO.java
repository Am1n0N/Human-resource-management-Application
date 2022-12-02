package com.hrm.Models;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class PTO {
    private int id;
    private int PtoId;
    private String Description;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private String Status;


    public PTO(int id, int ptoId, String description, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.PtoId = ptoId;
        this.Description = description;
        this.StartDate = startDate;
        this.EndDate = endDate;
        this.Status = status;

    }
    public PTO(){}
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

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getDays(){
        return (int) DAYS.between(getStartDate(),getEndDate());
    }

}
