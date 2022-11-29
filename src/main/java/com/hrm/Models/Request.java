package com.hrm.Models;

public class Request {
    private int id;
    private int RequesterId;
    private int ApproverId;
    private String Title;
    private String Description;
    private Boolean IsClosed;

    public Request(int id, int requesterId, String title, String description, Boolean isClosed) {
        this.id = id;
        RequesterId = requesterId;
        Title = title;
        Description = description;
        IsClosed = isClosed;
    }
    public Request(int requesterId, String title, String description) {
        RequesterId = requesterId;
        Title = title;
        Description = description;
    }
    public Request(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequesterId() {
        return RequesterId;
    }

    public void setRequesterId(int requesterId) {
        RequesterId = requesterId;
    }

    public int getApproverId() {
        return ApproverId;
    }

    public void setApproverId(int approverId) {
        ApproverId = approverId;
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

    public Boolean getClosed() {
        return IsClosed;
    }

    public void setClosed(Boolean closed) {
        IsClosed = closed;
    }
}
