package Controllers;

import Models.Connector;

import java.sql.Connection;

public class Tester {
    private Connection con;
    private Connector connection;
    /* Fonction qui test le cryptage. */
    public Boolean Testcrypter() {
        try {
            if (Outils.crypter("motdepasse").equals("rtyijufxxj")
                    && Outils.decrypter("rtyijufxxj").equals("motdepasse")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /* Fonction qui test la connexion. */

    public Boolean Testcnx() {
        connection = new Connector();
        try {
            con = connection.connect();
            return (con.isValid(60));
        } catch (Exception e) {
            return false;
        }
    }
}
