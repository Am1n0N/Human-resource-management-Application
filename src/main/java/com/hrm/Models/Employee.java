package com.hrm.Models;

public class Employee {
    private String  name,  Last_name, NIN, Title,  address,  Telephone,  DateNaissance,  Hiring_date;
    private int id;
    private Double Salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getNIN() {
        return NIN;
    }

    public void setNIN(String NIN) {
        this.NIN = NIN;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        DateNaissance = dateNaissance;
    }

    public String getHiring_date() {
        return Hiring_date;
    }

    public void setHiring_date(String hiring_date) {
        Hiring_date = hiring_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Employee(int id, String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  String hiring_date) {
        this.name = name;
        this.Last_name = last_name;
        this.NIN = NIN;
        this.Title = title;
        this.address = address;
        this.Telephone = telephone;
        this.DateNaissance = dateNaissance;
        this.Hiring_date = hiring_date;
        this.id = id;
    }

    public Employee(String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  String hiring_date) {
        this.name = name;
        this.Last_name = last_name;
        this.NIN = NIN;
        this.Title = title;
        this.address = address;
        this.Telephone = telephone;
        this.DateNaissance = dateNaissance;
        this.Hiring_date = hiring_date;
    }
}
