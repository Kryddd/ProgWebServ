package app;
import java.io.IOException;

import serveurs.*;

public class Application {

	public static void main(String[] args) {
		// Ajout d'abonnés
		
		// Ajout de livres
		
		try {
			new Thread(new ServeurReservation(2500)).start();
			new Thread(new ServeurEmprunt(2600)).start();
			new Thread(new ServeurRetour(2700)).start();
		} catch (IOException e) {
			System.err.println("Probleme lors de la creation d'un serveur :" + e);
		}
	}

}
