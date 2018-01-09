package serveurs;

import java.io.IOException;
import bibliotheque.Bibliotheque;
import services.ServiceEmprunt;


/**
 * Impl�mentation concr�te de la classe Serveur li�e au service d'emprunt de documents
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 * @see Serveur.java
 */
public class ServeurEmprunt extends Serveur{
	
	/**
	 * Constructeur du ServeurEmprunt
	 * @param port Port sur lequel le serveur sera lanc�
	 * @param biblio Biblioth�que utilis�e pour les requ�tes
	 * @throws IOException
	 */
	public ServeurEmprunt(int port, Bibliotheque biblio) throws IOException {
		super(port, biblio);
	}

	@Override
	public void run() {
		try {
			while(true) {
				new ServiceEmprunt(super.getListenSocket().accept(), super.getBiblio()).lancer();
			}
		}
		catch (IOException e) {
			// Fermeture du socket
			try {
				super.getListenSocket().close();
			}
			catch(IOException exc) {
				System.err.println("Probleme sur le port d'ecoute : " + exc);
			}
		}
	}
}
