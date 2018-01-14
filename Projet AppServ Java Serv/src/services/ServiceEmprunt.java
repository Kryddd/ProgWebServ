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
 * Implémentation concrète de la classe Service pour les emprunts de documents
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 * @see Service.java
 */
public class ServiceEmprunt extends Service{
	private static int cptEmpr = 0; // Compteur du nombre de services emprunt
	
	/**
	 * Constructeur du service emprunt
	 * @param socket
	 * @param biblio
	 */
	public ServiceEmprunt(Socket socket,Bibliotheque biblio) {
		super(socket, biblio, cptEmpr++);
	}
	
	@Override
	public void run() {
		String err = "";
		Boolean docTrouve = false;
		Boolean aboTrouve = false;
		Document docEmprunt = null;
		System.out.println("Connexion emprunt " + getNumero() + " demarrée");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(super.getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(super.getSocket().getOutputStream(), true);
			
			// Demande du client
			int numDocument = Integer.parseInt(in.readLine());
			int numAbonne = Integer.parseInt(in.readLine());
		
			// Recherche du document
			for (Document doc : getBiblio().getDocs()) {
				if(doc.numero() == numDocument){
					docTrouve = true;
					docEmprunt = doc;
				}
			} 
			
			// Recherche de l'abonné et emprunt si le doc est dispo
			for (Abonne abo : getBiblio().getAbonnes()){
				if(abo.numero() == numAbonne){
					aboTrouve = true;
					
					if(docTrouve) {
						try {
							docEmprunt.emprunter(abo);
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
			
			// Réponse au client
			if(err.equals("")) {
				out.println("Emprunt du document numero " + numDocument + " effectuée");
			}
			else {
				out.println(err);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			super.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Connexion emprunt " + getNumero() + " terminée");
	}
}
