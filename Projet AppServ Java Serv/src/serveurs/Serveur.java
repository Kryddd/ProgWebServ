package serveurs;

import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;
import services.ServiceFactory;


public class Serveur implements Runnable{
	private ServerSocket listenSocket;
	private Bibliotheque biblio;
	private int port;

	public Serveur(int port, Bibliotheque biblio) throws IOException {
		listenSocket = new ServerSocket(port);
		this.biblio = biblio;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			while(true) {
				ServiceFactory.createService(port, listenSocket, biblio);
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