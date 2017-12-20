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

public class ServiceEmprunt implements Runnable{
	private static int cptRes = 0;
	private int numero;
	private Bibliotheque biblio;
	private Socket client;
	
	public ServiceEmprunt(Socket socket,Bibliotheque biblio) {
		this.client = socket;
		this.numero = cptRes++;
	}
	
	@Override
	public void run() {
		String err = "";
		Boolean numtrouve = false;
		Boolean abotrouve = false;
		Boolean pasLibre = false;
		System.out.println("Connexion reservation " + this.numero + " demarrée");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			int numLivre = Integer.parseInt(in.readLine());
			int numAbonne = Integer.parseInt(in.readLine());
		
			for (Document doc : biblio.getDocs()) {
				if(doc.numero() == numLivre){
					numtrouve = true;
					for (Abonne abo : biblio.getAbonnes()){
						if(abo.numero() == numAbonne){
							abotrouve = true;
							try {
								doc.emprunter(abo);
							} catch (PasLibreException e) {
								pasLibre = true;
							}
						} 						
					}
				}	
			} 
			
			if (numtrouve == false || abotrouve == false){
				if (numtrouve == false){
					err +="Numéro livre inconnu. ";
				}
				if (abotrouve == false){
					err+="Numéro abonné inconnu.";
				}
				out.println(err);
			}else{
				if(pasLibre = true){
					out.println("Livre deja reservé par un abonné !");
				}else{
					out.println("Livre emprunté avec succes !");
				}
			}
						
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("Connexion reservation " + this.numero + " terminée");

		
		
	}
	
	public void lancer() {
		new Thread(this).start();
	}
	
	protected void finalize() throws IOException {
		client.close();
	}
}
