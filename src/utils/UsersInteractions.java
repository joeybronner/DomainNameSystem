package utils;

import java.io.IOException;
import java.util.Scanner;

import utils.enums.QClass;
import utils.enums.QType;

public class UsersInteractions {

	@SuppressWarnings("resource")
	public static String getUserInputHote() throws IOException {
		System.out.println("- HOTE -----------------------------------------");
		System.out.println("\nPlease enter an hote address (like <google.fr>):");
		Scanner in = new Scanner(System.in);
		return in.next();
	}

	@SuppressWarnings("resource")
	public static QClass getUserInputQClass() throws IOException {
		System.out.println("\n- QCLASS ---------------------------------------");
		System.out.println("\nQClass possibilities:");
		System.out.println("	> Enter '1' for IN (Internet)");
		System.out.println("	> Enter '2' for CS (CSNET, obsolete!!)");
		System.out.println("	> Enter '3' for CH (CHOAS)");
		System.out.println("	> Enter '4' for HS (HESIOD)");

		System.out.println("Please enter the QClass corresponding to your hote:");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		switch (Integer.parseInt(s)) { 
		case 1: 
			return QClass.IN;
		case 2: 
			return QClass.CS;
		case 3: 
			return QClass.CH;
		case 4: 
			return QClass.HS;
		default:
			return QClass.IN;
		}
	}

	@SuppressWarnings({ "resource" })
	public static QType getUserInputQType() throws IOException {
		System.out.println("\n- QTYPE ----------------------------------------");
		System.out.println("\nQClass possibilities:");
		System.out.println("	> Enter '1' for an host address");
		System.out.println("	> Enter '2' for an authoritative name server");
		System.out.println("	> Enter '3' for an email destination");
		System.out.println("	> Enter '4' for an email forwarder");
		System.out.println("	> Enter '5' for the canonical name for an alias");
		System.out.println("	> Enter '6' for the start of a zone of authority");
		System.out.println("	> Enter '7' for a mailbox domain name (EXPERIMENTAL)");
		System.out.println("	> Enter '8' for a mail group member (EXPERIMENTAL)");
		System.out.println("	> Enter '9' for a mail rename domain name (EXPERIMENTAL)");
		System.out.println("	> Enter '10' for a null RR (EXPERIMENTAL)");
		System.out.println("	> Enter '11' for a well known service description");
		System.out.println("	> Enter '12' for a domain name pointer");
		System.out.println("	> Enter '13' for an host information");
		System.out.println("	> Enter '14' for a mailbox or mail list information");
		System.out.println("	> Enter '15' for a mail exchange");
		System.out.println("	> Enter '16' for a text strings");

		System.out.println("Please enter the QType corresponding to your hote:");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();


		switch (Integer.parseInt(s)) { 
		case 1: 
			return QType.A;
		case 2: 
			return QType.NS;
		case 3: 
			return QType.MD;
		case 4: 
			return QType.MF;
		case 5: 
			return QType.CNAME;
		case 6: 
			return QType.SOA;
		case 7: 
			return QType.MB;
		case 8: 
			return QType.MG;
		case 9: 
			return QType.MR;
		case 10: 
			return QType.NULL;
		case 11: 
			return QType.WKS;
		case 12: 
			return QType.PTR;
		case 13: 
			return QType.HINFO;
		case 14: 
			return QType.MINFO;
		case 15: 
			return QType.MX;
		case 16: 
			return QType.TXT;
		default:
			return QType.A;
		}
	}
}
