package com.App.gestion_ressources_humaines.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.App.gestion_ressources_humaines.Controllers.Outils.crypter;
import static com.App.gestion_ressources_humaines.Controllers.Outils.decrypter;

public class Compte {
    private String Email,Password,isAdmin;
    private int id,id_Emp;

    private Connection con;
    private Connector connection;


    public Compte(String Email, String Password, String isAdmin, int id_Emp) {
        this.Email = Email;
        this.Password = Password;
        this.isAdmin = isAdmin;
        this.id_Emp = id_Emp;
    }

    public Compte(String Email){
        String query= "SELECT * FROM `compte` WHERE `Email` = '"+Email+"'";
        connection = new Connector();
        try {
            con = connection.connect();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                this.id = rs.getInt(1);
                this.Email = rs.getString(2);
                this.Password = decrypter(rs.getString(3));
                this.isAdmin = rs.getString(4);
                this.id_Emp = rs.getInt(5);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    protected void Ajouter() {
        String query= "INSERT INTO `compte`(`Email`, `Password`, `isAdmin`, `id_emp`) VALUES ('"+Email+"','"+crypter(Password)+"','"+isAdmin+"','"+id_Emp+"')";
        connection = new Connector();
        try {
            con = connection.connect();
            PreparedStatement preparedStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.executeUpdate();
            ResultSet rs=preparedStmt.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
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
