package bibliotheque;

public class Abonne {
	private static int cptAbonne = 0;
	private int numero;
	private String prenom;
	private String nom;
	
	public Abonne(String prenom, String nom) {
		this.numero = cptAbonne++;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public int numero() {
		return this.numero;
	}
}
