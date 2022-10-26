package Models;
import java.sql.*; 
public class Employee {
	private String Nom,Prenom,Titre,Adress,PhoneNum,DateNaiss;
	private Float Salaire;
	private String query;
	private Connection con;
	private Connector connection;
	
	public Employee(String nom, String prenom, String titre, String adress, String phoneNum, String dateNaiss,
			Float salaire) {
		this.Nom = nom;
		this.Prenom = prenom;
		this.Titre = titre;
		this.Adress = adress;
		this.PhoneNum = phoneNum;
		this.DateNaiss = dateNaiss;
		this.Salaire = salaire;
	}
	
	public void Ajouter() {
		if(Titre.equals("technicien")) {
			query = "insert into technicien(`Nom`, `Prenom`, `Titre`, `Salaire`, `DateNaiss`, `PhoneNum`, `Adress`)  VALUES ('"+Nom+"','"+Prenom+"','"+Titre+"','"+Salaire+"','"+DateNaiss+"','"+PhoneNum+"','"+Adress+"')";
		}else if(Titre.equals("ingénieur")) {
			query = "insert into ingénieur(`Nom`, `Prenom`, `Titre`, `Salaire`, `DateNaiss`, `PhoneNum`, `Adress`)  VALUES ('"+Nom+"','"+Prenom+"','"+Titre+"','"+Salaire+"','"+DateNaiss+"','"+PhoneNum+"','"+Adress+"')";
		}
		else {
			query = "insert into ouvrier(`Nom`, `Prenom`, `Titre`, `Salaire`, `DateNaiss`, `PhoneNum`, `Adress`)  VALUES ('"+Nom+"','"+Prenom+"','"+Titre+"','"+Salaire+"','"+DateNaiss+"','"+PhoneNum+"','"+Adress+"')";
		}

		connection = new Connector();
		try {
		con = connection.connect();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.execute();
		con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	
	
	
}
