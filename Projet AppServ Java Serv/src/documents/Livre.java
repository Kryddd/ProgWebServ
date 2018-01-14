package documents;

import java.util.Timer;

import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.PasLibreException;
import documents.timers.TimerTaskEmpruntLivre;
import documents.timers.TimerTaskReservLivre;

public class Livre implements Document {
	private static int cptLivre = 0;
	private static final long reservationDelay = 1000 * 2 * 3600; // 2 heures
	private static final long empruntDelay = 1000 * 3600 * 24 * 7 * 2; // 2 semaines
	private static final double probaDegradation = 0.1;
	
	private int numero;
	private String titre;
	private String auteur;
	private Abonne aboEmprunt;
	private Abonne aboReserve;
	
	private boolean enRetard;
	
	public Livre(String titre, String auteur) {
		this.numero = cptLivre++;
		this.aboEmprunt = null;
		this.aboReserve = null;
		this.titre = titre;
		this.auteur = auteur;
		this.enRetard = false;
	}
	
	public void setEnRetard() {
		this.enRetard = true;
		aboEmprunt.interdire();
	}

	@Override
	public int numero() {
		return numero;
	}

	@Override
	public synchronized void reserver(Abonne ab) throws PasLibreException {
		if(this.aboEmprunt == null) {
			if(this.aboReserve == null) {
				if(!ab.estInterdit()) {
					this.aboReserve = ab;
					setTimerReservation();
				}
				else {
					throw new PasLibreException("Erreur : Abonné est interdit de bibliotheque");
				}
			}
			else {
				throw new PasLibreException("Erreur : Le livre selectionné est déja reservé");
			}
		}
		else {
			throw new PasLibreException("Erreur : Le livre selectionné est emprunté");
		}
		
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException {		
		if(this.aboEmprunt == null){
			if(this.aboReserve == null || ab == this.aboReserve) {
				if(!ab.estInterdit()) {
					this.aboEmprunt = ab;
					this.aboReserve = null;
					setTimerEmprunt();
				}
				else {
					throw new PasLibreException("Erreur : Abonné interdit de bibliotheque");
				}
			}
			else {
				throw new PasLibreException("Erreur : Le livre selectionné est reservé par quelqu'un d'autre");
			}
		}
		else {			
			throw new PasLibreException("Erreur : Le livre selectionné est déja emprunté");
		}
	}

	@Override
	public synchronized void retour() {
		
		// Determine si le livre est dégradé
		boolean degrade = Math.random() <= probaDegradation;
		
		// Commence l'interdiction en cas de retard ou de dégradation
		if(enRetard || degrade) {
			aboEmprunt.setTimerAbInterdit();
		}
		
		this.aboEmprunt = null;
		this.enRetard = false;
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
	
	private void setTimerEmprunt() {
		Timer timerEmprunt = new Timer("timerEmprunt"+this.numero());
		timerEmprunt.schedule(new TimerTaskEmpruntLivre(this), empruntDelay);
	}
}
