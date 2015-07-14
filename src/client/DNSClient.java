package client;
import java.net.InetAddress;

import utils.enums.QClass;
import utils.enums.QType;


public class DNSClient {
	
	public static int QUERY_STANDARD = 0;
    public static int QUERY_INVERSE = 1;
    public static int QUERY_SERVER_STATUS = 2;
    
	private InetAddress SERVER_ADDRESS;
	private int SERVER_PORT;
	private boolean RECURSION;
	private int OPCODE;
	private QClass CLASS;
	private QType TYPE;
	private String QUERY;
    
	/* Client constructor */
	public DNSClient(InetAddress serverAddress, int serverPort, boolean recursionDesired, 
			int opcode, QClass cLass, QType type, String query) {
		this.setDNSServer(serverAddress);
		this.setDNSServerPort(serverPort);
		this.setRecursionDesired(recursionDesired);
		this.setOPCODE(opcode);
		this.setClass(cLass);
		this.setType(type);
		this.setQuery(query);
	}
	
	/* Getters & Setters */

	public void setDNSServer(InetAddress address) {
		this.SERVER_ADDRESS = address;
	}

	public InetAddress getDNSServer() {
		return this.SERVER_ADDRESS;
	}

	public void setDNSServerPort(int port) {
		this.SERVER_PORT = port;
	}

	public int getDNSServerPort() {
		return this.SERVER_PORT;
	}

	public void setRecursionDesired(boolean rd) {
		this.RECURSION = rd;
	}

	public boolean getRecursionDesired() {
		return this.RECURSION;
	}

	public void setOPCODE(int opcode) {
		this.OPCODE = opcode;
	}

	public int getOPCODE() {
		return this.OPCODE;
	}

	public void setType(QType type) {
		this.TYPE = type;
	}

	public QType getType() {
		return this.TYPE;
	}

	public void setClass(QClass clazz) {
		this.CLASS = clazz;
	}

	public QClass getCLass() {
		return this.CLASS;
	}

	public void setQuery(String query) {
		this.QUERY = query;
	}
	
	public String getQuery() {
		return this.QUERY;
	}
}
