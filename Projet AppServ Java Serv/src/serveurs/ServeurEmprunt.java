package serveurs;

import java.io.IOException;
import bibliotheque.Bibliotheque;
import services.ServiceEmprunt;


public class ServeurEmprunt extends Serveur{
	
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
