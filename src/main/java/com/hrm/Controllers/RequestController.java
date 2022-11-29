package com.hrm.Controllers;

import com.hrm.DAO.EntryDAO;
import com.hrm.DAO.RequestDAO;
import com.hrm.Models.Request;

import java.util.ArrayList;

public class RequestController {

    private RequestDAO requestDAO;
    public RequestController(RequestDAO requestDAO){
        this.requestDAO = requestDAO;
        try {
            requestDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addRequest(Request request){
        requestDAO.addRequest(request);
    }

    public void updateRequest(Request request){
        requestDAO.updateRequest(request);
    }

    public void deleteRequest(Request request){
        requestDAO.deleteRequest(request);
    }

    public ArrayList<Request> getRequests(){
        return requestDAO.getRequests();
    }
}

