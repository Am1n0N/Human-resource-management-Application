package Models;

public class Ouvrier extends Employee{
	private int id_ov;
	
	public Ouvrier(int id,String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss,
			Float salaire) {
		super(nom, prenom, titre, adress, phoneNum, dateNaiss, salaire);
		this.id_ov = id;
	}
}
