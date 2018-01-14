package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;
import bibliotheque.Document;

/**
 * Implémentation concrète de la classe Service pour les retours de documents
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 * @see Service.java
 */
public class ServiceRetour extends Service {
	private static int cptRet = 0; // Compteur du nombre de services retour
	
	/**
	 * Constructeur du service retour
	 * @param sock
	 * @param biblio
	 */
	public ServiceRetour(Socket sock, Bibliotheque biblio) {
		super(sock, biblio, cptRet++);
	}

	@Override
	public void run() {
		Boolean numtrouve = false;
		System.out.println("Connexion retour " + getNumero() + " demarrée");
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(super.getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(super.getSocket().getOutputStream(), true);
			
			// Demande du client
			int numLivre = Integer.parseInt(in.readLine());
			
			// Recherche du document et retour
			for (Document doc : getBiblio().getDocs()) {
				if(doc.numero() == numLivre){
					numtrouve = true;
					doc.retour();
				}
			}
			
			// Réponse au client
			if (!numtrouve){
				out.println("Numéro livre inconnu.");
			}else {
				out.println("Livre rendu");
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Connexion retour " + getNumero() + " terminée");
		try {
			super.getSocket().close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
