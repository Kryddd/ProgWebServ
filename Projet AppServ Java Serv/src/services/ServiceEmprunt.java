package services;

import java.io.IOException;
import java.net.Socket;

public class ServiceEmprunt implements Runnable{
	private static int cptRes = 0;
	private int numero;
	
	private Socket client;
	
	public ServiceEmprunt(Socket socket) {
		this.client = socket;
		this.numero = cptRes++;
	}
	
	@Override
	public void run() {
		System.out.println("Connexion reservation " + this.numero + " demarrée");
		
		
	}
	
	public void lancer() {
		new Thread(this).start();
	}
	
	protected void finalize() throws IOException {
		client.close();
	}
}
