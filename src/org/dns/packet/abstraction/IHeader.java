package org.dns.packet.abstraction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IHeader {
    
    /**
     * @param id
     *            - A 16 bit identifier assigned by the program that generates
     *            any kind of query. This identifier is copied the corresponding
     *            reply and can be used by the requester to match up replies to
     *            outstanding queries.
     */
    public void setID(int id);
    
    /**
     * @return A 16 bit identifier assigned by the program that generates any
     *         kind of query. This identifier is copied the corresponding reply
     *         and can be used by the requester to match up replies to
     *         outstanding queries.
     */
    public int getID();
    
    
    /**
     * @param isQuery
     *            - A one bit field that specifies whether this message is a
     *            query (0), or a response (1).
     */
    public void setQR(boolean isQuery);
    
    /**
     * @return - A one bit field that specifies whether this message is a query
     *         (0), or a response (1).
     */
    public boolean getQR();
    
    
    /**
     * @param code - A four bit field that specifies kind of query in this
     *               message.  This value is set by the originator of a query
     *               and copied into the response.  The values are:
     *               
     *               0               a standard query (QUERY)
     *               1               an inverse query (IQUERY)
     *               2               a server status request (STATUS)
     *               3-15            reserved for future use
     */
    public void setOpcode(int code);
    
    /**
     * @return A four bit field that specifies kind of query in this
     *               message.  This value is set by the originator of a query
     *               and copied into the response.  The values are:
     *               
     *               0               a standard query (QUERY)
     *               1               an inverse query (IQUERY)
     *               2               a server status request (STATUS)
     *               3-15            reserved for future use
     */
    public int getOpcode();
    
    /**
     * @param aa
     *            - Authoritative Answer - this bit is valid in responses,
     *            and specifies that the responding name server is an
     *            authority for the domain name in question section.
     *            
     *            Note that the contents of the answer section may have
     *            multiple owner names because of aliases.  The AA bit
     *            corresponds to the name which matches the query name, or
     *            the first owner name in the answer section.
     */
    public void setAA(boolean aa);
    

    /**
     * @return 
     *          - Authoritative Answer - this bit is valid in responses,
     *            and specifies that the responding name server is an
     *            authority for the domain name in question section.
     *            
     *            Note that the contents of the answer section may have
     *            multiple owner names because of aliases.  The AA bit
     *            corresponds to the name which matches the query name, or
     *            the first owner name in the answer section.
     */
    public boolean getAA();
    
    
    /**
     * @param tc
     *            - TrunCation - this bit specifies that this message was
     *            truncated due to length greater than that permitted on the
     *            transmission channel.
     */
    public void setTC(boolean tc);
    
    
    /**
     * @return TrunCation - specifies that this message was truncated due to
     *         length greater than that permitted on the transmission channel.
     */
    public boolean getTC();
    
    
    /**
     * @param rd
     *            - Recursion Desired - this bit may be set in a query and is
     *            copied into the response. If RD is set, it directs the name
     *            server to pursue the query recursively. Recursive query
     *            support is optional.
     */
    public void setRD(boolean rd);
    
    
    /**
     * @return Recursion Desired - this bit may be set in a query and is copied
     *         into the response. If RD is set, it directs the name server to
     *         pursue the query recursively. Recursive query support is
     *         optional.
     */
    public boolean getRD();
    
    
    /**
     * @param rd
     *            - Recursion Available - this bit is set or cleared in a
     *            response, and denotes whether recursive query support is
     *            available in the name server.
     */
    public void setRA(boolean ra);
    
    
    /**
     * @return Recursion Available - this bit is set or cleared in a response,
     *         and denotes whether recursive query support is available in the
     *         name server.
     */
    public boolean getRA();
    
    
    /**
     * @param z
     *            - Reserved for future use. Must be zero in all queries and
     *            responses.
     */
    public void setZ(int z);
    
    
    /**
     * @return Reserved for future use. Must be zero in all queries and
     *         responses.
     */
    public int getZ();
    
    
    /**
     * @param rcode - 
     *          Response code - this 4 bit field is set as part of
     *          responses.  The values have the following
     *          interpretation:
     *          
     *          0               No error condition
     *          
     *          1               Format error - The name server was
     *                          unable to interpret the query.
     *                          
     *          2               Server failure - The name server was
     *                          unable to process this query due to a
     *                          problem with the name server.
     *          
     *          3               Name Error - Meaningful only for
     *                          responses from an authoritative name
     *                          server, this code signifies that the
     *                          domain name referenced in the query does
     *                          not exist.
     *                          
     *          4               Not Implemented - The name server does
     *                          not support the requested kind of query.
     *                          
     *          5               Refused - The name server refuses to
     *                          perform the specified operation for
     *                          policy reasons.  For example, a name
     *                          server may not wish to provide the
     *                          information to the particular requester,
     *                          or a name server may not wish to perform
     *                          a particular operation (e.g., zone
     *                          transfer) for particular data.
     *                          
     *                          6-15            Reserved for future use.
     */
    public void setRCode(int rcode);
    
    
    /**
     * @return
     *      Response code - this 4 bit field is set as part of
     *          responses.  The values have the following
     *          interpretation:
     *          
     *          0               No error condition
     *          
     *          1               Format error - The name server was
     *                          unable to interpret the query.
     *                          
     *          2               Server failure - The name server was
     *                          unable to process this query due to a
     *                          problem with the name server.
     *          
     *          3               Name Error - Meaningful only for
     *                          responses from an authoritative name
     *                          server, this code signifies that the
     *                          domain name referenced in the query does
     *                          not exist.
     *                          
     *          4               Not Implemented - The name server does
     *                          not support the requested kind of query.
     *                          
     *          5               Refused - The name server refuses to
     *                          perform the specified operation for
     *                          policy reasons.  For example, a name
     *                          server may not wish to provide the
     *                          information to the particular requester,
     *                          or a name server may not wish to perform
     *                          a particular operation (e.g., zone
     *                          transfer) for particular data.
     *                          
     *                          6-15            Reserved for future use.
     */
    public int getRCode();
    
    
    /**
     * @param qdCount
     *            - an unsigned 16 bit integer specifying the number of entries
     *            in the question section.
     */
    public void setQDCount(int qdCount);
    
    
    /**
     * @return an unsigned 16 bit integer specifying the number of entries in
     *         the question section.
     */
    public int getQDCount();
    
    
    /**
     * @param anCount
     *            - an unsigned 16 bit integer specifying the number of resource
     *            records in the answer section.
     */
    public void setANCount(int anCount);
    
    
    /**
     * @return an unsigned 16 bit integer specifying the number of resource
     *         records in the answer section.
     */
    public int getANCount();
    
    
    /**
     * @param nsCount
     *            - an unsigned 16 bit integer specifying the number of name
     *            server resource records in the authority records section.
     */
    public void setNSCount(int nsCount);
    
    
    /**
     * @return an unsigned 16 bit integer specifying the number of name server
     *         resource records in the authority records section.
     */
    public int getNSCount();
    
    
    /**
     * @param arCount
     *            - an unsigned 16 bit integer specifying the number of resource
     *            records in the additional records section.
     */
    public void setARCount(int arCount);
    
    
    /**
     * @return an unsigned 16 bit integer specifying the number of resource
     *         records in the additional records section.
     */
    public int getARCount();
    
    /**
     * @return Header in bytes
     * @throws IOException 
     */
    public byte[] toByteArray() throws IOException;
    
    /**
     * Reads header information from the given input stream
     * @param dis - Stream to read header information from
     * @throws IOException 
     */
    public void readFromInputStream(DataInputStream dis) throws IOException;
    
    /**
     * Writes header information to the given stream
     * @param dos - stream to write header information to
     * @throws IOException 
     */
    public void writeToOutputStream (DataOutputStream dos) throws IOException;
    
    public String toString();
}
