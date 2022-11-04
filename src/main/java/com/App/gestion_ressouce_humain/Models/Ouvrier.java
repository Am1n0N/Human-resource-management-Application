package com.App.gestion_ressouce_humain.Models;

public class Ouvrier extends Employee{
    private int id_ov;

    public Ouvrier(String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss,
                   Float salaire) {
        super(nom, prenom, titre, adress, phoneNum, dateNaiss, salaire);
    }
}
