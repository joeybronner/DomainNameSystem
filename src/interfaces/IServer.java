package interfaces;


import java.net.Socket;

public interface IServer {

	void run();
	boolean init();
    void handle(Socket client);
    // void manageSession(Request request);
	// void execute(Request request);
}
