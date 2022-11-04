package Models;

public class Test {
    static Ingénieur emp;
    public static void main(String[]args) {
        emp = new Ingénieur("amine", "rouissi", "technicien", "ben arous", "10241092", "1999", (float) 1000);
        emp.Ajouter();
    }
}
