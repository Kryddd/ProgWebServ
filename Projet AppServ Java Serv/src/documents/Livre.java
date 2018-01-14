package documents;

import java.util.ArrayList;
import java.util.Timer;

import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.PasLibreException;
import documents.timers.TimerTaskEmpruntLivre;
import documents.timers.TimerTaskReservLivre;
import mail.Mail;

public class Livre implements Document {
	private static int cptLivre = 0;
	private static final long reservationDelay = 1000 * 2 * 3600; // 2 heures
	private static final long empruntDelay = 1000 * 3600 * 24 * 7 * 2; // 2 semaines
	private static final double probaDegradation = 0.1; // 10% de chance de d�gradation au retour
	
	private int numero;
	private String titre;
	private String auteur;
	private Abonne aboEmprunt;
	private Abonne aboReserve;
	private ArrayList<Abonne> enAttente;
	private boolean enRetard;
	
	/**
	 * Constructeur de la classe Livre
	 * @param titre Titre du livre
	 * @param auteur Auteur du livre
	 */
	public Livre(String titre, String auteur) {
		this.numero = cptLivre++;
		this.aboEmprunt = null;
		this.aboReserve = null;
		this.titre = titre;
		this.auteur = auteur;
		this.enAttente = new ArrayList<>();
		this.enRetard = false;
	}
	
	/**
	 * Methode appel�e quand le livre est en retard
	 * Il interdit l'abonn� d'emprunter et de r�server un
	 * autre livre.
	 */
	public synchronized void setEnRetard() {
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
					throw new PasLibreException("Erreur : Abonn� est interdit de bibliotheque");
				}
			}
			else {
				throw new PasLibreException("Erreur : Le livre selectionn� est d�ja reserv�");
			}
		}
		else {
			throw new PasLibreException("Erreur : Le livre selectionn� est emprunt�");
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
					throw new PasLibreException("Erreur : Abonn� interdit de bibliotheque");
				}
			}
			else {
				throw new PasLibreException("Erreur : Le livre selectionn� est reserv� par quelqu'un d'autre");
			}
		}
		else {
			this.enAttente.add(ab);
			throw new PasLibreException("Erreur : Le livre selectionn� est d�ja emprunt�."
					+ " Un mail vous sera envoy� lorsqu'il sera disponible");
		}
	}

	@Override
	public synchronized void retour() {
		
		// Determine si le livre est d�grad�
		boolean degrade = Math.random() <= probaDegradation;
		
		// Commence l'interdiction en cas de retard ou de d�gradation
		if(enRetard || degrade) {
			aboEmprunt.setTimerAbInterdit();
		}
		
		this.aboEmprunt = null;
		this.enRetard = false;
		Mail.send(enAttente, this);
	}
	
	/**
	 * Annule la r�servation d'un abonn�
	 */
	public synchronized void supprReservation() {
		this.aboReserve = null;
	}
	
	/**
	 * Donne le titre du livre
	 * @return Le titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Donne l'auteur du livre
	 * @return L'auteur
	 */
	public String getAuteur() {
		return auteur;
	}
	
	/**
	 * Lance le timer de la r�servation
	 */
	private void setTimerReservation(){
		Timer timerReserv = new Timer("timerReservation" + this.numero());
		timerReserv.schedule(new TimerTaskReservLivre(this), reservationDelay);
	}
	
	/**
	 * Lance le timer de l'emprunt
	 */
	private void setTimerEmprunt() {
		Timer timerEmprunt = new Timer("timerEmprunt"+this.numero());
		timerEmprunt.schedule(new TimerTaskEmpruntLivre(this), empruntDelay);
	}
}
