package serveurs;

import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;
import services.ServiceEmprunt;


public class ServeurEmprunt implements Runnable{
	private ServerSocket listenSocket;
	private Bibliotheque biblio;

	public ServeurEmprunt(int port, Bibliotheque biblio) throws IOException {
		listenSocket = new ServerSocket(port);
	}

	@Override
	public void run() {
		try {
			while(true) {
				new ServiceEmprunt(listenSocket.accept(), biblio).lancer();
			}
		}
		catch (IOException e) {
			// Fermeture du socket
			try {
				this.listenSocket.close();
			}
			catch(IOException exc) {
				System.err.println("Probleme sur le port d'ecoute : " + exc);
			}
		}
	}
	
	protected void finalize() {
		try {
			this.listenSocket.close();
		}
		catch(IOException e) {
			
		}
	}
}
