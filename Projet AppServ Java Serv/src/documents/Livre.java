package documents;

import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.PasLibreException;

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
		if(this.emprunte == false) {
			if(this.reserve == false) {
				this.reserve = true;
			}
		}else {
			throw new PasLibreException("Erreur : Le livre selectionné est déja emprunté");
		}
		
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException {
		if(this.emprunte == false){
			if(this.reserve == false){
				this.emprunte = true;
			}else {
				throw new PasLibreException("Erreur : Le livre selectionné est reservé");
			}
		}else {
			throw new PasLibreException("Erreur : Le livre selectionné est déja emprunté");
		}
		
	}

	@Override
	public synchronized void retour() {
		this.emprunte = false;
		this.reserve = false;	
	}
	
	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}
}
