package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
		System.out.println("Connexion reservation " + this.numero + "demarrée");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			String numLivre = in.readLine();
			
			
			String numAbonne = in.readLine();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void lancer() {
		new Thread(this).start();
	}
	
	protected void finalize() throws IOException {
		client.close();
	}
}
