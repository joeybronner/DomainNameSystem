package org.dns.instance.abstraction;

import org.dns.packet.abstraction.record.IRecordClass;
import org.dns.packet.abstraction.record.IRecordType;
import org.dns.packet.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

public interface IDNSClient {
	/**
	 * Set DNS server's IP address
	 * @param address - DNS Server address
	 */
	public void setDNSServer(InetAddress address);
	/**
	 * Get DNS server's IP address
	 * @return - DNS Server address
	 */
	public InetAddress getDNSServer();
	
	/**
	 * DNS server's port
	 * @param port - Port to send query
	 */
	public void setDNSServerPort(int port);
	/**
	 * Get DNS server's port
	 * @return - DNS server's port
	 */
	public int getDNSServerPort();
	
	/**
	 * Should the query be recurse?
	 * @param rd - recursion desired
	 */
	public void setRecursionDesired(boolean rd);
	/**
	 * Should the query be recursive
	 * @return - recursion desired
	 */
	public boolean getRecursionDesired();
	
	/**
	 * @param - opcode A four bit field that specifies kind of query in this
     *               message.  This value is set by the originator of a query
     *               and copied into the response.  The values are:
     *               
     *               0               a standard query (QUERY)
     *               1               an inverse query (IQUERY)
     *               2               a server status request (STATUS)
     *               3-15            reserved for future use
	 */
	public void setOPCODE(int opcode);
	
	/**
	 * @return - A four bit field that specifies kind of query in this
     *               message.  This value is set by the originator of a query
     *               and copied into the response.  The values are:
     *               
     *               0               a standard query (QUERY)
     *               1               an inverse query (IQUERY)
     *               2               a server status request (STATUS)
     *               3-15            reserved for future use
	 */
	public int getOPCODE();
	
	/**
	 * @param type - a two octet code which specifies the type of the query. The
     *         values for this field include all codes valid for a TYPE field,
     *         together with some more general codes which can match more than
     *         one type of RR.
     *         
     *         AXFR            252 A request for a transfer of an entire zone
     *         MAILB           253 A request for mailbox-related records (MB, MG or MR)
     *         MAILA           254 A request for mail agent RRs (Obsolete - see MX)
     *         *               255 A request for all records
	 */
	public void setType(IRecordType type);
	/**
	 * @return a two octet code which specifies the type of the query. The
     *         values for this field include all codes valid for a TYPE field,
     *         together with some more general codes which can match more than
     *         one type of RR.
     *         
     *         AXFR            252 A request for a transfer of an entire zone
     *         MAILB           253 A request for mailbox-related records (MB, MG or MR)
     *         MAILA           254 A request for mail agent RRs (Obsolete - see MX)
     *         *               255 A request for all records
	 */
	public IRecordType getType();
	
	/**
	 * @param clazz - a two octet (16-Bit) code that specifies the class of the query. For
     *            example, the QCLASS field is IN for the Internet.
	 */
	public void setClass(IRecordClass clazz);
	/**
	 * @return a two octet (16-Bit) code that specifies the class of the query. For
     *            example, the QCLASS field is IN for the Internet.
	 */
	public IRecordClass getCLass();
	
	/**
	 * @param query - query to lookup
	 */
	public void setQuery(String query);
	/**
	 * @return Query
	 */
	public String getQuery();
	
	/**
	 * Sends query to the DNS server and prints the response org.dns.packet
	 * @throws SocketException
	 * @throws IOException
	 */
	public Packet doLookup() throws SocketException, IOException;
	
}
