package Models;
import java.sql.*;
public class Employee {
    protected String Nom,Prenom,Titre,Adress,PhoneNum,DateNaiss;
    protected Float Salaire;
    protected String query;
    protected Connection con;
    protected Connector connection;

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

    protected void Ajouter() {

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

    protected void Update() {}

    protected void Supprimer() {}



    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getDateNaiss() {
        return DateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        DateNaiss = dateNaiss;
    }

    public Float getSalaire() {
        return Salaire;
    }

    public void setSalaire(Float salaire) {
        Salaire = salaire;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }



}
