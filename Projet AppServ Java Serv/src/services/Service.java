package services;

import java.io.IOException;
import java.net.Socket;

import bibliotheque.Bibliotheque;

/**
 * Classe abstraite représentant un service utilisé par un serveur
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public abstract class Service implements Runnable {
	private int numero; // Numéro du service
	private Bibliotheque biblio; // Bibliothèque utilisée par le service
	private Socket client; // socket de communication avec le client
	
	/**
	 * Constructeur du service
	 * @param socket
	 * @param biblio
	 * @param numero
	 */
	public Service(Socket socket, Bibliotheque biblio, int numero) {
		this.client = socket;
		this.biblio = biblio;
		this.numero = numero;
	}
	
	/**
	 * Getter du numéro
	 * @return Numéro du service
	 */
	public int getNumero() {
		return this.numero;
	}
	
	/**
	 * Getter de la bibliothèque
	 * @return blibliothèque
	 */
	public Bibliotheque getBiblio() {
		return this.biblio;
	}
	
	/**
	 * Getter du socket
	 * @return socket client
	 */
	public Socket getSocket() {
		return this.getSocket();
	}
	
	public abstract void run();
	
	/**
	 * Lance le thread du service
	 */
	public void lancer() {
		new Thread(this).start();
	}
	
	protected void finalize() throws IOException {
		client.close();
	}
}
