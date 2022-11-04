package com.App.gestion_ressouce_humain.Controllers;

import com.App.gestion_ressouce_humain.Views.LoginPage;

public class Start {
    public static void main(String[] args) {
        Tester t = new Tester();

        /* Test Cryptage */
        Boolean c1 = t.Testcrypter();
        if (c1 == true) {
            System.out.println("Test Cryptage: " + c1);
        } else {
            Outils.error("Cryptage Invalide");
        }
        ;

        /* Test Connexion � la BD */
        Boolean c2 = t.Testcnx();
        if (c2 == true) {
            System.out.println("Test Connexion BD: " + c2);
        } else {
            Outils.error("Error du connexion BD");
        }
        ;

        /* Lancer le programme lorsque tous les tests sont termin�s */
        if (c1 && c2) {
           //TODO: App Launch
        }
    }

}
