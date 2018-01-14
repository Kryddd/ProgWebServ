package bibliotheque;

import java.util.Timer;

import bibliotheque.timers.TimerTaskAbInterdit;

/**
 * Classe repr�sentant l'abonn� de la biblioth�que
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
	 * @param prenom Le pr�nom de l'abonn�
	 * @param nom Le nom de l'abonn�
	 * @param email L'adresse mail de l'abonn�
	 */
	public Abonne(String prenom, String nom, String email) {
		this.numero = cptAbonne++;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.interditEmprunt = false;
	}
	
	/**
	 * Donne le pr�nom de l'abonn�
	 * @return Le pr�nom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Donne le nom de l'abonn�
	 * @return Le nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Donne l'adresse mail de l'abonn�
	 * @return L'adresse mail
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Donne le num�ro de l'abonn�
	 * @return Le num�ro
	 */
	public int numero() {
		return this.numero;
	}
	
	/**
	 * Indique si l'utilisateur � le droit d'emprunter
	 * @return
	 */
	public boolean estInterdit() {
		return interditEmprunt;
	}
	
	/**
	 * Empeche l'abonn� de r�server ou d'emprunter un livre jusqu'� ce qu'il
	 * ai rendu ses livres en retard et subi la p�nalit� de 1 mois
	 */
	public void interdire() {
		this.interditEmprunt = true;
	}

	/**
	 * Commence le d�lai d'interdiction d'emprunt de 1 mois
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
