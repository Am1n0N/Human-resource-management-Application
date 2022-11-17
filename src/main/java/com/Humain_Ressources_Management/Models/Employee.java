package com.Humain_Ressources_Management.Models;




public class Employee {
    private String  name,  Last_name,  Title,  address,  Telephone,  DateNaissance,  Hiring_date;
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

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    public Employee(int id, String name, String Last_name, String Title, String address, String Telephone, String DateNaissance, Double salary, String Hiring_date) {
        this.id = id;
        this.name = name;
        this.Last_name = Last_name;
        this.Title = Title;
        this.address = address;
        this.Telephone = Telephone;
        this.DateNaissance = DateNaissance;
        this.Salary = salary;
        this.Hiring_date = Hiring_date;
    }

    public Employee (String name, String Last_name, String Title, String address, String Telephone, String DateNaissance, Double salary, String Hiring_date) {
        this.name = name;
        this.Last_name = Last_name;
        this.Title = Title;
        this.address = address;
        this.Telephone = Telephone;
        this.DateNaissance = DateNaissance;
        this.Salary = salary;
        this.Hiring_date = Hiring_date;
    }
}
