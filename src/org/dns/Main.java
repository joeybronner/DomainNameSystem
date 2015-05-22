package org.dns;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.dns.packet.section.record.RecordClass;
import org.dns.packet.section.record.RecordType;
import org.dns.instance.DNSClient;

public class Main {
	public static void main(String[] args) throws SocketException, IOException {
		//byte a = 0xc;
		//System.out.println(a);
		byte [] serverAddress = {8,8,8,8}; //DNS Server
		int serverPort = 53;
		boolean recursionDesired = true;
		int opcode = DNSClient.QUERY_STANDARD;
		RecordClass cLass = new RecordClass(RecordClass.QCLASS_IN);
		RecordType type = new RecordType(RecordType.QTYPE_A);
		
		String query = "yahoo.com";
		
		DNSClient client = new DNSClient(
				InetAddress.getByAddress(serverAddress),
				serverPort, 
				recursionDesired, 
				opcode, 
				cLass, 
				type, 
				query
				);
		
		System.out.println(client.doLookup());
	}
}
