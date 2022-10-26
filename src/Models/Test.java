package Models;

public class Test {
	 static Employee emp; 
	public static void main(String[]args) {
		emp = new Employee("amine", "rouissi", "ingénieur", "ben arous", "10241092", "1999", (float) 1000);
		emp.Ajouter();
	}
}
