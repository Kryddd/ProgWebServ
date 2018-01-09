package serveurs;

import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;

public abstract class Serveur implements Runnable {
	private ServerSocket listenSocket;
	private Bibliotheque biblio;
	
	public Serveur(int port, Bibliotheque biblio) throws IOException {
		listenSocket = new ServerSocket(port);
		this.biblio = biblio;
	}
	
	public ServerSocket getListenSocket() {
		return listenSocket;
	}
	
	public Bibliotheque getBiblio() {
		return biblio;
	}
	
	public abstract void run();
	
	protected void finalize() {
		try {
			this.listenSocket.close();
		}
		catch(IOException e) {
			
		}
	}
}
