package documents;

import java.util.Timer;

import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.PasLibreException;
import timers.TimerTaskReservLivre;

public class Livre implements Document {
	private static int cptLivre = 0;
	private static final long reservationDelay = 1000 * 2 * 3600;
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
				setTimerReservation();
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
	
	public synchronized void supprReservation() {
		this.aboReserve = null;
	}
	
	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}
	
	private void setTimerReservation(){
		Timer timerReserv = new Timer("timerReservation" + this.numero());
		timerReserv.schedule(new TimerTaskReservLivre(this), reservationDelay);
	}
}
