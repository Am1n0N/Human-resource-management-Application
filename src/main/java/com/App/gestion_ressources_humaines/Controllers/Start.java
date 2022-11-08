package com.App.gestion_ressources_humaines.Controllers;

public class Start {
    public static void main(String[] args) {
        Tester t = new Tester();

        /* Test Cryptage */
        Boolean c1 = t.Testcrypter();
        if (c1) {
            System.out.println("Test Cryptage: " + c1);
        } else {
            System.out.println("Cryptage Invalide");
        }
        ;

        /* Test Connexion � la BD */
        Boolean c2 = t.Testcnx();
        if (c2) {
            System.out.println("Test Connexion BD: " + c2);
        } else {
            System.out.println("Error du connexion BD");
        }
        ;

        /* Lancer le programme lorsque tous les tests sont termin�s */
        if (c1 && c2) {
           //TODO: App Launch
        }
    }

}
