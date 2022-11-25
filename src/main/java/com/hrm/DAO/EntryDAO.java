package com.hrm.DAO;

import com.hrm.Models.Entry;

import java.util.ArrayList;
public interface EntryDAO extends DAO {
    public void addEntry(Entry entry);

    public ArrayList<Entry> getEntries();

    public Entry getEntry(int id);
    public void updateEntry(Entry entry);
    public void deleteEntry(Entry entry);
    public void getEntry(Entry entry);
}

