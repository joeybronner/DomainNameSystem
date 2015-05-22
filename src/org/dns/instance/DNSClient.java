package org.dns.instance;
import org.dns.instance.abstraction.IDNSClient;
import org.dns.packet.abstraction.record.IRecordClass;
import org.dns.packet.abstraction.record.IRecordType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;

import org.dns.packet.section.Header;
import org.dns.packet.Packet;
import org.dns.packet.section.Question;


public class DNSClient implements IDNSClient{
	
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
		this.setClass(cLass);
		this.setType(type);
		this.setQuery(query);
		
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#setDNSServer(java.net.InetAddress)
	 */
	@Override
	public void setDNSServer(InetAddress address) {
		this.serverAddress = address;
		
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#getDNSServer()
	 */
	@Override
	public InetAddress getDNSServer() {
		return this.serverAddress;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#setDNSServerPort(int)
	 */
	@Override
	public void setDNSServerPort(int port) {
		this.serverPort = port;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#getDNSServerPort()
	 */
	@Override
	public int getDNSServerPort() {
		return this.serverPort;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#setRecursionDesired(boolean)
	 */
	@Override
	public void setRecursionDesired(boolean rd) {
		this.recursionDesired = rd;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#getRecursionDesired()
	 */
	@Override
	public boolean getRecursionDesired() {
		return this.recursionDesired;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#setOPCODE(int)
	 */
	@Override
	public void setOPCODE(int opcode) {
		this.opcode = opcode;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#getOPCODE()
	 */
	@Override
	public int getOPCODE() {
		return this.opcode;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#setType(org.dns.interfaces.org.dns.packet.section.record.IRecordType)
	 */
	@Override
	public void setType(IRecordType type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#getType()
	 */
	@Override
	public IRecordType getType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#setClass(org.dns.interfaces.org.dns.packet.section.record.IRecordClass)
	 */
	@Override
	public void setClass(IRecordClass clazz) {
		this.cLass = clazz;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#getCLass()
	 */
	@Override
	public IRecordClass getCLass() {
		return this.cLass;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#setQuery(java.lang.String)
	 */
	@Override
	public void setQuery(String query) {
		this.query = query;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#getQuery()
	 */
	@Override
	public String getQuery() {
		return this.query;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.instance.IDNSClient#doLookup()
	 */
	@Override
	public Packet doLookup() throws SocketException,IOException {
		int id = new Random().nextInt(65535);
		boolean qr = false;
		boolean aa = false;
		boolean tc = false;
		boolean ra = false;
		int z = 0;
		int rcode = 0;
		int qdcount = 1;
		int ancount = 0;
		int nscount = 0;
		int arcount = 0;
		
		Header header = new Header(id, qr, this.getOPCODE(), aa, tc,
				this.getRecursionDesired(), ra, z, rcode, qdcount, ancount,
				nscount, arcount);
		
		Question question = new Question(this.getQuery(), this.getType(), this.getCLass());
		Packet dnspacket = new Packet(header, question, null, null,null);
		byte[] data = dnspacket.toByteArray();
		DatagramSocket socket = new DatagramSocket();
	    DatagramPacket packet = new DatagramPacket (data, data.length, this.getDNSServer(), this.serverPort);
	    socket.setSoTimeout(5000);
	    socket.send(packet);
	    packet = new DatagramPacket(new byte [512], 512);
	    socket.receive(packet);
	    byte[] arr = packet.getData();
	    Packet response = new Packet(arr);
	    return response;
	}
}
