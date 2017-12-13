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
		}
		catch(IOException e) {
			
		}
	}

}
