package com.App.gestion_ressources_humaines.Models;
import java.sql.*;

public class Employee {
    private String Nom,Prenom,Titre,Adress,PhoneNum,DateNaiss,Hiring_date,Dismissal_date;
    private int id;
    private Float Salaire;
    private String query;
    private Connection con;
    private Connector connection;

    public Employee(String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss, Float salaire, String Hiring_date) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.Titre = titre;
        this.Adress = adress;
        this.PhoneNum = phoneNum;
        this.DateNaiss = dateNaiss;
        this.Salaire = salaire;
        this.Hiring_date=Hiring_date;
    }

    public Employee(int id){
        String query= "SELECT * FROM `employee` WHERE `id` = '"+id+"'";
        connection = new Connector();
        try {
            con = connection.connect();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet rs= preparedStmt.executeQuery();
            if(rs.next()){
                this.id = rs.getInt(1);
                this.Nom = rs.getString(2);
                this.Prenom = rs.getString(3);
                this.Titre = rs.getString(4);
                this.Salaire =  rs.getFloat(5);;
                this.Adress = rs.getString(8);
                this.PhoneNum =  rs.getString(7);;
                this.DateNaiss =  rs.getString(6);;
                this.Hiring_date= rs.getString(9);;
                this.Dismissal_date=rs.getString(10);;
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    protected void Ajouter() {
        query = "insert into `employee` (`Nom`, `Prenom`, `Titre`, `Salaire`, `DateNaiss`, `PhoneNum`, `Adress`,`Hiring_date`)  VALUES ('"+Nom+"','"+Prenom+"','"+Titre+"','"+Salaire+"','"+DateNaiss+"','"+PhoneNum+"','"+Adress+"','"+Hiring_date+"')";
        connection = new Connector();
        try {
            con = connection.connect();
            PreparedStatement preparedStmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
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

    protected void Update(int id) {
        query = "UPDATE `employee` SET `Nom`='"+Nom+"',`Prenom`='"+Prenom+"',`Titre`='"+Titre+"',`Salaire`='"+Salaire+"',`DateNaiss`='"+DateNaiss+"',`PhoneNum`='"+PhoneNum+"',`Adress`='"+Adress+"' WHERE `idTec` = '"+id+"'";
        connection = new Connector();
        try {
            con = connection.connect();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.executeUpdate();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    protected void Supprimer() {}



    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getDateNaiss() {
        return DateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        DateNaiss = dateNaiss;
    }

    public Float getSalaire() {
        return Salaire;
    }

    public void setSalaire(Float salaire) {
        Salaire = salaire;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
