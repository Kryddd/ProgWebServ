package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;

/**
 * Implémentation concrète de la classe Service pour les reservations de documents
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 * @see Service.java
 */
public class ServiceReservation extends Service {

	private static int cptRes = 0; // Compteur du nombre de services reservation
	
	/**
	 * Constructeur du service reservation
	 * @param socket
	 * @param biblio
	 */
	public ServiceReservation(Socket socket, Bibliotheque biblio) {
		super(socket, biblio, cptRes++);
	}
	
	@Override
	public void run() {
		System.out.println("Connexion reservation " + getNumero() + " demarrÃ©e");
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(super.getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(super.getSocket().getOutputStream(), true);
			
			String numLivre = in.readLine();
			String numAbonne = in.readLine();
		}
		catch(IOException e) {
			
		}
		
		System.out.println("Connexion reservation " + getNumero() + " terminÃ©e");
		try {
			super.getSocket().close();
		}
		catch (IOException exc) {
			
		}
	}

}
