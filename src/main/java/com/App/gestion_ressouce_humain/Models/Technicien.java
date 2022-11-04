package Models;

public class Technicien extends Employee {
    private int id_Tec;

    public Technicien(String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss,
                      Float salaire) {
        super(nom, prenom, titre, adress, phoneNum, dateNaiss, salaire);
    }



}
