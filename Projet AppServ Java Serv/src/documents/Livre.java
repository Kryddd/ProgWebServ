package documents;

import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.PasLibreException;

public class Livre implements Document {
	private static int cptLivre = 0;
	private int numero;
	private String titre;
	private String auteur;
	private Abonne aboEmprunt;
	private Abonne aboReserve;
	
	public Livre(String titre, String auteur) {
		this.numero = cptLivre++;
		this.aboEmprunt = null;
		this.aboReserve = null;
		this.titre = titre;
		this.auteur = auteur;
	}
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public synchronized void reserver(Abonne ab) throws PasLibreException {
		if(this.aboEmprunt == null) {
			if(this.aboReserve == null) {
				this.aboReserve = ab;
			}else {
				throw new PasLibreException("Erreur : Le livre selectionné est déja reservé");
			}
		}else {
			throw new PasLibreException("Erreur : Le livre selectionné est emprunté");
		}
		
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException {		
		if(this.aboEmprunt == null){
			if(this.aboReserve == null || ab == this.aboReserve){
				this.aboEmprunt = ab;
				this.aboReserve = null;
			}else {
				throw new PasLibreException("Erreur : Le livre selectionné est reservé par quelqu'un d'autre");
			}
		}else {			
			throw new PasLibreException("Erreur : Le livre selectionné est déja emprunté");
		}
	}

	@Override
	public synchronized void retour() {
		this.aboEmprunt = null;
	}
	
	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}
}
