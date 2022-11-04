package com.App.gestion_ressouce_humain.Models;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ingénieur extends Employee{
    private int id_ing;
    private Connection con;
    private Connector connection;

    public Ingénieur(String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss,
                     Float salaire) {
        super(nom, prenom, titre, adress, phoneNum, dateNaiss, salaire);
    }


}
