package com.App.gestion_ressources_humaines.Controllers;


public class Outils {


    /*Fonction pour crypter le mdp*/
    public static String crypter(String texte) {
        try {
            String textechiffr= "";
            for (int i = 0; i < texte.length(); i++) {
                int c = texte.charAt(i);
                c += 5;
                textechiffr += (char) c;
            }
            return (textechiffr);
        } catch (Exception e) {
            System.out.println("Error au niveau du cryptage");
            return ("");
        }
    }

    /*Fonction pour decrypter le mdp*/
    public static String decrypter(String texte) {
        try {
            String textechiffr = "";
            for (int i = 0; i < texte.length(); i++) {
                int c = texte.charAt(i);
                c -= 5;
                textechiffr += (char) c;
            }
            return (textechiffr);
        } catch (Exception e) {
            System.out.println("Error au niveau du decryptage");
            return ("");
        }
    }
}
