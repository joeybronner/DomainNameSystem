package org.dns.instance;
import java.net.InetAddress;

import org.dns.packet.abstraction.record.IRecordClass;
import org.dns.packet.abstraction.record.IRecordType;


public class DNSClient {
	
	public static int QUERY_STANDARD = 0;
    public static int QUERY_INVERSE = 1;
    public static int QUERY_SERVER_STATUS = 2;
    
	private InetAddress serverAddress;
	private int serverPort;
	private boolean recursionDesired;
	private int opcode;
	private IRecordClass cLass;
	private IRecordType type;
	private String query;
    
	public DNSClient(InetAddress serverAddress, int serverPort,
			boolean recursionDesired, int opcode, IRecordClass cLass,
			IRecordType type, String query) {

		this.setDNSServer(serverAddress);
		this.setDNSServerPort(serverPort);
		this.setRecursionDesired(recursionDesired);
		this.setOPCODE(opcode);
		System.out.println("classval : " + cLass);
		this.setClass(cLass);
		this.setType(type);
		this.setQuery(query);
		
	}

	public void setDNSServer(InetAddress address) {
		this.serverAddress = address;
		
	}

	public InetAddress getDNSServer() {
		return this.serverAddress;
	}

	public void setDNSServerPort(int port) {
		this.serverPort = port;
	}

	public int getDNSServerPort() {
		return this.serverPort;
	}

	public void setRecursionDesired(boolean rd) {
		this.recursionDesired = rd;
	}

	public boolean getRecursionDesired() {
		return this.recursionDesired;
	}

	public void setOPCODE(int opcode) {
		this.opcode = opcode;
	}

	public int getOPCODE() {
		return this.opcode;
	}

	public void setType(IRecordType type) {
		this.type = type;
	}

	public IRecordType getType() {
		return this.type;
	}

	public void setClass(IRecordClass clazz) {
		this.cLass = clazz;
	}

	public IRecordClass getCLass() {
		return this.cLass;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return this.query;
	}
}
