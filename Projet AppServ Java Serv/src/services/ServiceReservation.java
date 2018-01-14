package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Abonne;
import bibliotheque.Bibliotheque;
import bibliotheque.Document;
import bibliotheque.PasLibreException;

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
		System.out.println("Connexion reservation " + getNumero() + " demarrée");
		String err = "";
		Boolean docTrouve = false;
		Boolean aboTrouve = false;
		Document docReserv = null;
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(super.getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(super.getSocket().getOutputStream(), true);
			
			int numDocument = Integer.parseInt(in.readLine());
			int numAbonne = Integer.parseInt(in.readLine());
			
			// Recherche du document
			for (Document doc : getBiblio().getDocs()) {
				if(doc.numero() == numDocument){
					docTrouve = true;
					docReserv = doc;
				}
			}
			
			// Recherche de l'abonné et reservation si le doc est dispo
			for (Abonne abo : getBiblio().getAbonnes()){
				if(abo.numero() == numAbonne){
					aboTrouve = true;
					
					if(docReserv != null) {
						try {
							docReserv.reserver(abo);
						} 
						catch (PasLibreException e) {
							err += e.getMessage();
						}
					}
				}
			}
			
			if (docTrouve == false) {
				err +="Numéro livre inconnu. ";
			}
			if (aboTrouve == false) {
				err+="Numéro abonné inconnu.";
			}
			
			if(err.equals("")) {
				out.println("Reservation du document numero " + numDocument + " effectuée");
			}
			else {
				out.println(err);
			}
		}
		catch(IOException e) {
			e.printStackTrace();	
		}
		System.out.println("Connexion reservation " + getNumero() + " terminée");
		try {
			super.getSocket().close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
