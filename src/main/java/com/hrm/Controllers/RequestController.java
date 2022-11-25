package com.hrm.Controllers;

import com.hrm.DAO.RequestDAO;
import com.hrm.Models.Request;

public class RequestController {
    private RequestDAO requestDAO;
    public RequestController(RequestDAO requestDAO){
        this.requestDAO = requestDAO;
    }
    public void addRequest(Request request){
        requestDAO.addRequest(request);
    }
}

