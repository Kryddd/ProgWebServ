package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;

public class ServiceRetour implements Runnable {
	private static int cptRes = 0;
	private int numero;
	private Bibliotheque biblio;
	private Socket client;
	
	public ServiceRetour(Socket sock, Bibliotheque biblio) {
		this.client = sock;
		this.numero = cptRes++;
	}

	@Override
	public void run() {
		System.out.println("Connexion retour " + this.numero + " demarrée");
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			String numLivre = in.readLine();
			String numAbonne = in.readLine();
		}
		catch(IOException e) {
			
		}
		
		System.out.println("Connexion reservation " + this.numero + "terminée");
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
