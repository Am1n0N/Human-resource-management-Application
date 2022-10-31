package Models;

public class Ingénieur extends Employee{
    private int id_ing;

    public Ingénieur(int id,String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss,
                     Float salaire) {
        super(nom, prenom, titre, adress, phoneNum, dateNaiss, salaire);
        this.id_ing = id;
    }


}
