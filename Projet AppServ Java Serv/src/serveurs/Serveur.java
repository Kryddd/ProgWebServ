package serveurs;

import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;

/**
 * Classe abstraite repr�sentant un serveur sur lequel les clients se connectent
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public abstract class Serveur implements Runnable {
	private ServerSocket listenSocket; // Socket du serveur
	private Bibliotheque biblio; // biblioth�que que le serveur utilise pour effectuer les requetes
	
	/**
	 * Constructeur du Serveur
	 * @param port Port sur lequel le serveur sera lanc�
	 * @param biblio Biblioth�que utilis�e pour les requ�tes
	 * @throws IOException
	 */
	public Serveur(int port, Bibliotheque biblio) throws IOException {
		listenSocket = new ServerSocket(port);
		this.biblio = biblio;
	}
	
	
	/**
	 * Getter du socket serveur
	 * @return ServerSocket
	 */
	public ServerSocket getListenSocket() {
		return listenSocket;
	}
	
	/**
	 * Getter de biblioth�que
	 * @return biblioth�que
	 */
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
