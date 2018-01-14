package bibliotheque;

import java.util.Timer;

import bibliotheque.timers.TimerTaskAbInterdit;

public class Abonne {
	private static final long interditDelay = 1000 * 3600 * 24 * 7 * 4; // 1 mois
	private static int cptAbonne = 0;
	private int numero;
	private String prenom;
	private String nom;
	private boolean interditEmprunt;
	
	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public Abonne(String prenom, String nom) {
		this.numero = cptAbonne++;
		this.prenom = prenom;
		this.nom = nom;
		this.interditEmprunt = false;
	}
	
	public int numero() {
		return this.numero;
	}
	
	/**
	 * Indique si l'utilisateur à le droit d'emprunter
	 * @return
	 */
	public boolean estInterdit() {
		return interditEmprunt;
	}
	
	/**
	 * Empeche l'abonné de réserver ou d'emprunter un livre jusqu'à ce qu'il
	 * ai rendu ses livres en retard et subi la pénalité de 1 mois
	 */
	public void interdire() {
		this.interditEmprunt = true;
	}

	/**
	 * Commence le délai d'interdiction d'emprunt de 1 mois
	 */
	public void setTimerAbInterdit() {
		Timer timerAbInterdit = new Timer("timerReservation" + this.numero());
		timerAbInterdit.schedule(new TimerTaskAbInterdit(this), interditDelay);
	}

	/**
	 * Annule l'interdiction d'emprunter
	 */
	public void annuleInterdit() {
		this.interditEmprunt = false;
		
	}
}
