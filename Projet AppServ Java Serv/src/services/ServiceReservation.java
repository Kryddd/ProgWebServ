package services;

import java.net.Socket;

public class ServiceReservation implements Runnable {

	private Socket client;
	
	public ServiceReservation(Socket socket) {
		this.client = socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void lancer() {
		new Thread(this).start();
	}

}
