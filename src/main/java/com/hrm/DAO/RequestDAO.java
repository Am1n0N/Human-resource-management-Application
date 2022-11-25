package com.hrm.DAO;

import com.hrm.Models.Request;

public interface RequestDAO extends DAO {
    public void addRequest(Request request);
    public void updateRequest(Request request);
    public void deleteRequest(Request request);
    public void getRequest(Request request);
}

