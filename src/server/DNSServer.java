package server;


import interfaces.IServer;

import java.awt.ItemSelectable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class DNSServer implements x{

	private Map<String, Map<String, String>> domains;
	
	public static void main(String[] args) {
		// TODO Server
	}

	@Override
	public void run() {
		ServerSocket server = null;
		Socket client = null;
		
		// Creation socket serveur  recuperer Workers  et Port 
		
		//TODO 
		try {
			server = new ServerSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Définir le port avec config.ini
		
		while(true)
		{
			try {
				client = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	@Override
	public boolean init() {
		// TODO définir les domaines .avec le config
				domains = new HashMap<String, Map<String,String>>();
				
				
				
		return false;
	}

}
