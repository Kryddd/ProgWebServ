package documents.timers;

import java.util.TimerTask;

import documents.Livre;

/**
 * TimerTask de l'emprunt du livre
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public class TimerTaskEmpruntLivre extends TimerTask {
	
	private Livre livreEmprunt;

	/**
	 * Constructeur de la classe TimerTaskEmpruntLivre
	 * @param livre Le livre emprunté
	 */
	public TimerTaskEmpruntLivre(Livre livre) {
		this.livreEmprunt = livre;
	}

	@Override
	public void run() {
		livreEmprunt.setEnRetard();
		System.out.println("Emprunt " + livreEmprunt.numero() + livreEmprunt.getTitre()
		+ " - " + livreEmprunt.getAuteur() + " en retard");
	}

}
