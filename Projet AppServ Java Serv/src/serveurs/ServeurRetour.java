package serveurs;

import java.io.IOException;
import bibliotheque.Bibliotheque;
import services.ServiceRetour;

public class ServeurRetour extends Serveur {
	
	public ServeurRetour(int port, Bibliotheque biblio) throws IOException {
		super(port, biblio);
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				new ServiceRetour(getListenSocket().accept(), getBiblio()).lancer();
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
	
	protected void finalize() {
		try {
			getListenSocket().close();
		}
		catch(IOException e) {
			
		}
	}

}
