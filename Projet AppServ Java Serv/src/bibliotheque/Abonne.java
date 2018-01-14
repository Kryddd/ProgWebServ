package bibliotheque;

import java.util.Timer;

import bibliotheque.timers.TimerTaskAbInterdit;

/**
 * Classe représentant l'abonné de la bibliothèque
 * @author Jacques COUDERC, Arthur CAYET, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public class Abonne {
	private static final long interditDelay = 1000 * 3600 * 24 * 7 * 4; // 1 mois
	private static int cptAbonne = 0;
	private int numero;
	private String prenom;
	private String nom;
	private String email;
	private boolean interditEmprunt;
	
	/**
	 * Constructeur de la classe Abonne
	 * @param prenom Le prénom de l'abonné
	 * @param nom Le nom de l'abonné
	 * @param email L'adresse mail de l'abonné
	 */
	public Abonne(String prenom, String nom, String email) {
		this.numero = cptAbonne++;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.interditEmprunt = false;
	}
	
	/**
	 * Donne le prénom de l'abonné
	 * @return Le prénom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Donne le nom de l'abonné
	 * @return Le nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Donne l'adresse mail de l'abonné
	 * @return L'adresse mail
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Donne le numéro de l'abonné
	 * @return Le numéro
	 */
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
