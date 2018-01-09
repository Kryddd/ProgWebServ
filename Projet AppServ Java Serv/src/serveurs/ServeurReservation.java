package serveurs;

import java.io.IOException;
import bibliotheque.Bibliotheque;
import services.ServiceReservation;

/**
 * Implémentation concrète de la classe Serveur liée au service de réservation de documents
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 * @see Serveur.java
 */
public class ServeurReservation extends Serveur {
	
	/**
	 * Constructeur du ServeurReservation
	 * @param port Port sur lequel le serveur sera lancé
	 * @param biblio Bibliothèque utilisée pour les requêtes
	 * @throws IOException
	 */
	public ServeurReservation(int port, Bibliotheque biblio) throws IOException {
		super(port, biblio);
	}

	@Override
	public void run() {
		try {
			while(true) {
				new ServiceReservation(getListenSocket().accept(), getBiblio()).lancer();
			}
		}
		catch (IOException e) {
			// Fermeture du socket
			try {
				getListenSocket().close();
			}
			catch(IOException exc) {
				System.err.println("Probleme sur le port d'ecoute : " + exc);
			}
		}
	}
}
