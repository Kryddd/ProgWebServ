package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Application {
	
	// Port d'emprunt
	private static final int EmpruntPort = 2600;
	// Port de retour
	private static final int RetourPort = 2700;
	private static final String host = "localhost";
	
	public static void main(String[] args) {
		Socket socket = null;
		// Reader du texte
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		String line;
		Boolean inputIsGood = false;
		System.out.println("Bienvenue\n");
		while(inputIsGood==false){
			System.out.println("MENU DES SERVICES\n1 : Emprunt\n2 : Retour");
			try {
				line = clavier.readLine();
				int formatedinput = -1;
				if(line.length() == 1){
					formatedinput = Integer.parseInt(line);
				}
				System.out.println("\ninput :" + line);
				//Le client a selectionné un emprunt
				if(formatedinput == 1){
					inputIsGood = true;
					try {
						socket = new Socket(host, EmpruntPort);
							
						BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
							
							
						// Connecté!
						System.out.println("Connecté au serveur d'emprunt (" + socket.getPort() + ")");
						
						
						// Envois au serveur
						System.out.println("Entrez le numero du livre a emprunter :");
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
				}
					
					
				
				//Le client a selectionné un emprunt
				else if(formatedinput == 2){
					inputIsGood = true;
					try {
						socket = new Socket(host, RetourPort);
							
						BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
							
							
						// Connecté!
						System.out.println("Connecté au serveur de (" + socket.getPort() + ")");
						
						// TODO faire la relation livre Emprunter/Abonne
						// Envois au serveur
						//System.out.println("Entrez votre numero d'abonne :");
						//line = clavier.readLine();
						//sOut.println(line);			
						
						System.out.println("Entrez le numero du livre a retourner :");
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
				}
				else{
					System.out.println("Désolé je n'ai pas compris votre demande.\nVeuillez entrer 1 ou 2\n");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("erreur input clavier");
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
}
