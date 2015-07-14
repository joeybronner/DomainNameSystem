package org.dns;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.Scanner;

import org.dns.instance.DNSClient;
import org.dns.packet.Packet;
import org.dns.packet.section.Header;
import org.dns.packet.section.Question;
import org.dns.packet.section.record.RecordClass;
import org.dns.packet.section.record.RecordType;

import utils.Utils;

public class Main {

	// DNS Public Google 
	static byte [] SERVER_ADDRESS = {8,8,8,8}; 
	static int SERVER_PORT = 53;
	static String HOTE = null;
	static boolean RECURSION = true;
	static int OPCODE = DNSClient.QUERY_STANDARD;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SocketException, IOException {

		System.out.println("------------------------------------------------");
		System.out.println("------------- DNS Client Resolver --------------");
		System.out.println("------------------------------------------------");

		System.out.println("@project: Distributed and concurrent architecture project");
		System.out.println("@school : ESGI (Paris, 12th)");
		System.out.println("@github : https://github.com/joeybronner/DomainNameSystem");

		Utils.pause();

		HOTE = getHote();

		
		
		RecordClass cLass = new RecordClass(RecordClass.QCLASS_IN);
		System.out.println("classval : " + cLass);
		RecordType type = new RecordType(RecordType.QTYPE_A);
	

		DNSClient client = new DNSClient(
				InetAddress.getByAddress(SERVER_ADDRESS),
				SERVER_PORT, 
				RECURSION, 
				OPCODE, 
				cLass, 
				type, 
				HOTE
				);

		// Lookup
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

		Header header = new Header(
				id, 
				qr, 
				client.getOPCODE(),
				aa, 
				tc,
				client.getRecursionDesired(),
				ra, 
				z, 
				rcode, 
				qdcount, 
				ancount,
				nscount, 
				arcount);

		Question question = new Question(
				client.getQuery(), 					// query
				client.getType(), 					// type
				client.getCLass());					// class



		Packet dnspacket = new Packet(header, question/*, null, null,null*/);
		byte[] data = dnspacket.toByteArray();


		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket (data, 
				data.length, 
				InetAddress.getByAddress(SERVER_ADDRESS), 
				SERVER_PORT);


		socket.setSoTimeout(5000);
		socket.send(packet);


		packet = new DatagramPacket(new byte [512], 512);
		socket.receive(packet);
		byte[] arr = packet.getData();
		Packet response = new Packet(arr);


		System.out.println(response);
	}

	@SuppressWarnings("resource")
	private static String getHote() throws IOException {
        System.out.println("Please enter an hote address (like <google.fr>):");
        Scanner in = new Scanner(System.in);
        return in.next();
	}

}
