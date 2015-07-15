package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class DNSServer {

	private Map<String, Map<String, String>> domains;
	
	public DNSServer() {
        handleConfig();
		run();
	}

    public void handleConfig() {
        // TODO définir les domaines .avec le config
        domains = new HashMap<String, Map<String,String>>();
        // TODO:
    }

	public void run() {
		ServerSocket server = null;
		Socket client = null;
		
		// Creation socket serveur  recuperer Workers  et Port 
		
		//TODO 
		try {
			server = new ServerSocket();
            // server = new ServerSocket(Integer.parseInt(conf.get(Config.Type.worker.toString(), Config.PORT)));
            while(true) {
                try {
                    client = server.accept();
                    addJobToPool(client);
                } catch (Exception e) {
                }
            }
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

	public void addJobToPool(Socket client) {
        //		pool.addJob(new DNSServerTask(this, client));
    }

}
