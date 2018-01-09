package serveurs;

import java.io.IOException;
import bibliotheque.Bibliotheque;
import services.ServiceReservation;

public class ServeurReservation extends Serveur {
	
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
