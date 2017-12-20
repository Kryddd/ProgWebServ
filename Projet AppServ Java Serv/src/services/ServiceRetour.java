package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;
import bibliotheque.Document;

public class ServiceRetour implements Runnable {
	private static int cptRes = 0;
	private int numero;
	private Bibliotheque biblio;
	private Socket client;
	
	public ServiceRetour(Socket sock, Bibliotheque biblio) {
		this.client = sock;
		this.numero = cptRes++;
		this.biblio = biblio;
	}

	@Override
	public void run() {
		Boolean numtrouve = false;
		System.out.println("Connexion retour " + this.numero + " demarr�e");
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			int numLivre = Integer.parseInt(in.readLine());
			//int numAbonne = Integer.parseInt(in.readLine());
			
			for (Document doc : biblio.getDocs()) {
				if(doc.numero() == numLivre){
					numtrouve = true;
					doc.retour();
				}
			}
			if (numtrouve == false){
				out.println("Numéro livre inconnu.");
			}else {
				out.println("Livre retourné avec succés !");
			}
			
		}
		catch(IOException e) {
			
		}
		
		System.out.println("Connexion reservation " + this.numero + "termin�e");
		try {
			client.close();
		}
		catch (IOException exc) {
			
		}
		
	}

	public void lancer() {
		new Thread(this).start();
	}

	protected void finalize() throws IOException {
		this.client.close();
	}
}
