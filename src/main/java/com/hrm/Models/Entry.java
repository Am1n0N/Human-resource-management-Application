package com.hrm.Models;

public class Entry {
    private int id;
    private int employeeId;
    private String date;
    private String Title;
    private String Description;
    private Boolean isPublic;
    private Boolean allowComments;

    public Entry(int id, int employeeId, String date, String title, String description, Boolean isPublic, Boolean allowComments) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.Title = title;
        this.Description = description;
        this.isPublic = isPublic;
        this.allowComments = allowComments;
    }
    public Entry(int employeeId, String date, String title, String description, Boolean isPublic, Boolean allowComments){
        this.employeeId = employeeId;
        this.date = date;
        this.Title = title;
        this.Description = description;
        this.isPublic = isPublic;
        this.allowComments = allowComments;
    }
    public Entry(int employeeId, String title, String description, Boolean isPublic, Boolean allowComments){
        this.employeeId = employeeId;
        this.Title = title;
        this.Description = description;
        this.isPublic = isPublic;
        this.allowComments = allowComments;
    }
    public Entry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }
}
