package com.App.gestion_ressources_humaines.Models;

public class Test {
    static Ingénieur emp;
    static Compte compte;

    public static void main(String[]args) {

        compte = new Compte("AAA");
        System.out.println(compte.getPassword()+compte.getId_Emp());
    }
}
