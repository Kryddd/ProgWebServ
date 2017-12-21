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
			}else {
				
				throw new PasLibreException("Erreur : Le livre selectionné est déja reservé");
			}
		}else {
			
			throw new PasLibreException("Erreur : Le livre selectionné est emprunté");
		}
		
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException {
		boolean livreTrouve = false;
		
		if(this.emprunte == false){
			if(this.reserve == false){
				this.emprunte = true;
				ab.addEmprunt(this.numero());
			}else {
				for (Integer i : ab.getReservation()) {
					if (i == this.numero()) {
						this.emprunte = true;
						this.reserve = false;
						ab.delReservation(this.numero());
						ab.addEmprunt(this.numero());
						livreTrouve = true;
					}
				}
				if (livreTrouve == false) {
					throw new PasLibreException("Erreur : Le livre selectionné est reservé par quelqu'un d'autre");
				}
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
