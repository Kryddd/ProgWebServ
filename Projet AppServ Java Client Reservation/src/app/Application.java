package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Application {

	// Port de reservation
	private static final int port = 2500;
	private static final String host = "localhost";
	
	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			socket = new Socket(host, port);
			
			BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
			
			// Reader du texte
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			
			// Connecté!
			System.out.println("Connecté au serveur de reservation (" + socket.getPort() + ")");
			
			String line;
			
			// Envois au serveur
			System.out.println("Entrez le numero du livre a reserver :");
			line = clavier.readLine();
			sOut.println(line);
			System.out.println("Entrez votre numero d'abonne :");
			line = clavier.readLine();
			sOut.println(line);
			
			// Réponse du serveur
			line = sIn.readLine();
			
			// Affiche la reponse du serveur
			System.out.println(line);
		}
		catch(IOException e) {
			System.err.println("Erreur du client : " + e);
		}
		
		// Ferme le socket
		try {
			if(socket != null) {
				socket.close();
			}
		}
		catch(IOException exc) {
			
		}
	}

}
