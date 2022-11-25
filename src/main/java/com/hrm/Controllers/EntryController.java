package com.hrm.Controllers;

import com.hrm.DAO.EntryDAO;
import com.hrm.Models.Entry;

import java.util.ArrayList;

public class EntryController {
    private EntryDAO entryDAO;
    public EntryController(EntryDAO entryDAO){
        this.entryDAO = entryDAO;
        try {
            entryDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addEntry(Entry entry){
        entryDAO.addEntry(entry);
    }
    public void updateEntry(Entry entry){
        entryDAO.updateEntry(entry);
    }
    public void deleteEntry(Entry entry){
        entryDAO.deleteEntry(entry);
    }
    public void getEntry(Entry entry){
        entryDAO.getEntry(entry);
    }
    public ArrayList<Entry> getEntries(){
        return entryDAO.getEntries();
    }
    public Entry getEntry(int id){
        return entryDAO.getEntry(id);
    }
}
