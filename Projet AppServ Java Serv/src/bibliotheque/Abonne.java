package bibliotheque;

import java.util.Timer;

import bibliotheque.timers.TimerTaskAbInterdit;

public class Abonne {
	private static final long interditDelay = 60000; // 1 mois
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
	
	public boolean estInterdit() {
		return interditEmprunt;
	}
	
	public void interdire() {
		this.interditEmprunt = true;
		setTimerAbInterdit();
	}

	private void setTimerAbInterdit() {
		Timer timerAbInterdit = new Timer("timerReservation" + this.numero());
		timerAbInterdit.schedule(new TimerTaskAbInterdit(this), interditDelay);
	}

	public void annuleInterdit() {
		this.interditEmprunt = false;
		
	}
}
