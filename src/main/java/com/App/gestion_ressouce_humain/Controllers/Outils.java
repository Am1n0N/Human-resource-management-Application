package com.App.gestion_ressouce_humain.Controllers;

import javax.swing.*;

public class Outils {

    /*Procedure qui affiche un message d'error*/
    public static void error(String textError) {
        JOptionPane.showMessageDialog(null, textError, " Error", 0);
    }

    /*Procedure qui affiche un message d'information*/

    public static void info(String textInfo) {
        JOptionPane.showMessageDialog(null, textInfo, " Info", JOptionPane.INFORMATION_MESSAGE);
    }

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
            Outils.error("Error au niveau du cryptage");
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
            Outils.error("Error au niveau du decryptage");
            return ("");
        }
    }
}
