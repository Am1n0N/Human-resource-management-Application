package Models;

public class Technicien extends Employee {
    private int id_Tec;

    public Technicien(int id,String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss,
                      Float salaire) {
        super(nom, prenom, titre, adress, phoneNum, dateNaiss, salaire);
        this.id_Tec = id;
    }


}
