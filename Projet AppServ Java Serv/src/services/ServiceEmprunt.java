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
		Boolean numtrouve = false;
		Boolean abotrouve = false;
		System.out.println("Connexion emprunt " + getNumero() + " demarrée");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(super.getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(super.getSocket().getOutputStream(), true);
			
			int numLivre = Integer.parseInt(in.readLine());
			int numAbonne = Integer.parseInt(in.readLine());
		
			for (Document doc : getBiblio().getDocs()) {
				if(doc.numero() == numLivre){
					numtrouve = true;
					for (Abonne abo : getBiblio().getAbonnes()){
						if(abo.numero() == numAbonne){
							abotrouve = true;
							try {
								doc.emprunter(abo);
							} catch (PasLibreException e) {
								err += e.getMessage();

							}
						}
					}
				}
			} 
			
			if (numtrouve == false) {
				err +="Numéro livre inconnu. ";
			}
			if (abotrouve == false) {
				err+="Numéro abonné inconnu.";
			}
			out.println(err);			
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
