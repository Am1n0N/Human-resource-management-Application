package com.App.gestion_ressources_humaines.Models;



import java.sql.Connection;

public class Ingénieur extends Employee{
    private Connection con;
    private Connector connection;

    public Ingénieur(String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss, Float salaire, String Hiring_date) {
        super(nom, prenom, titre, adress, phoneNum, dateNaiss, salaire, Hiring_date);
    }


}
