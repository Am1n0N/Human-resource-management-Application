package com.hrm.Models;

public class Account {
    private String Email,Password,isAdmin;
    private int id,id_Emp;

    public Account(int id, String Email, String Password, String isAdmin, int id_Emp) {
        this.id = id;
        this.Email = Email;
        this.Password = Password;
        this.isAdmin = isAdmin;
        this.id_Emp = id_Emp;
    }

    public Account(String Email, String Password, String isAdmin, int id_Emp) {
        this.Email = Email;
        this.Password = Password;
        this.isAdmin = isAdmin;
        this.id_Emp = id_Emp;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId_Emp() {
        return id_Emp;
    }

    public void setId_Emp(int id_Emp) {
        this.id_Emp = id_Emp;
    }
}
