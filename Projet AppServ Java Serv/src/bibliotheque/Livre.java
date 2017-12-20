package bibliotheque;

public class Livre implements Document {
	private static int cptLivre = 0;
	private int numero;
	private boolean reserve;
	private boolean emprunte;
	private String titre;
	private String auteur;
	
	public Livre(String titre, String auteur) {
		this.numero = cptLivre++;
		this.reserve = false;
		this.emprunte = false;
		this.titre = titre;
		this.auteur = auteur;
	}
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public synchronized void reserver(Abonne ab) throws PasLibreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void retour() {
		// TODO Auto-generated method stub
		
	}
	
	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}
}
