package services;

import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;

public class ServiceFactory {
	public static void createService(int port, ServerSocket listenSocket, Bibliotheque biblio) throws IOException {
		switch(port) {
			case 2500: new ServiceReservation(listenSocket.accept(), biblio).lancer();
				break;
				
			case 2600: new ServiceEmprunt(listenSocket.accept(), biblio).lancer();
				break;
				
			case 2700: new ServiceRetour(listenSocket.accept(), biblio).lancer();
				break;
			default: throw new RuntimeException("Service invalide");
		}
	}
}
