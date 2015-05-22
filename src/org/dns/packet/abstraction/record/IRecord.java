package org.dns.packet.abstraction.record;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IRecord {
    /**
     * @param name
     *            - The domain name that was queried, in the same format as the
     *            QNAME in the questions.
     */
    public void setName(String name);
    
    
    /**
     * @return The domain name that was queried, in the same format as the
     *            QNAME in the questions.
     */
    public String getName();
    
    
    /**
     * @param type
     *            - two octets containing one of the RR type codes. This field
     *            specifies the meaning of the data in the RDATA field.
     *            
     *         TYPE            value and meaning
     *         A               1 a host address
     *         NS              2 an authoritative name server
     *         MD              3 a mail destination (Obsolete - use MX)
     *         MF              4 a mail forwarder (Obsolete - use MX)
     *         CNAME           5 the canonical name for an alias
     *         SOA             6 marks the start of a zone of authority
     *         MB              7 a mailbox domain name (EXPERIMENTAL)
     *         MG              8 a mail group member (EXPERIMENTAL)
     *         MR              9 a mail rename domain name (EXPERIMENTAL)
     *         NULL            10 a null RR (EXPERIMENTAL)
     *         WKS             11 a well known service description
     *         PTR             12 a domain name pointer
     *         HINFO           13 host information
     *         MINFO           14 mailbox or mail list information
     *         MX              15 mail exchange
     *         TXT             16 text strings
     */
    public void setType(IRecordType type);
    
    
    /**
     * @return two octets containing one of the RR type codes. This field
     *         specifies the meaning of the data in the RDATA field.
     *         
     *         TYPE            value and meaning
     *         A               1 a host address
     *         NS              2 an authoritative name server
     *         MD              3 a mail destination (Obsolete - use MX)
     *         MF              4 a mail forwarder (Obsolete - use MX)
     *         CNAME           5 the canonical name for an alias
     *         SOA             6 marks the start of a zone of authority
     *         MB              7 a mailbox domain name (EXPERIMENTAL)
     *         MG              8 a mail group member (EXPERIMENTAL)
     *         MR              9 a mail rename domain name (EXPERIMENTAL)
     *         NULL            10 a null RR (EXPERIMENTAL)
     *         WKS             11 a well known service description
     *         PTR             12 a domain name pointer
     *         HINFO           13 host information
     *         MINFO           14 mailbox or mail list information
     *         MX              15 mail exchange
     *         TXT             16 text strings
     */
    public IRecordType getType();
    
    
    /**
     * @param cLass
     *            - two octets which specify the class of the data in the RDATA
     *            field.
     *            
     *            IN              1 the Internet
     *            CS              2 the CSNET class (Obsolete - used only for examples in some obsolete RFCs)
     *            CH              3 the CHAOS class
     *            HS              4 Hesiod [Dyer 87]
     */
    public void setCLass(IRecordClass cLass);
    
    
    /**
     * @return two octets which specify the class of the data in the RDATA
     *         field.
     *         
     *            IN              1 the Internet
     *            CS              2 the CSNET class (Obsolete - used only for examples in some obsolete RFCs)
     *            CH              3 the CHAOS class
     *            HS              4 Hesiod [Dyer 87]
     */
    public IRecordClass getCLass();
    
    
    /**
     * @param ttl
     *            - a 32 bit unsigned integer that specifies the time interval
     *            (in seconds) that the resource record may be cached before it
     *            should be discarded. Zero values are interpreted to mean that
     *            the RR can only be used for the transaction in progress, and
     *            should not be cached.
     */
    public void setTTL(int ttl);
    
    
    /**
     * @return - a 32 bit unsigned integer that specifies the time interval (in
     *         seconds) that the resource record may be cached before it should
     *         be discarded. Zero values are interpreted to mean that the RR can
     *         only be used for the transaction in progress, and should not be
     *         cached.
     */
    public int getTTL();
    
    
    /**
     * @param rdlength
     *            - an unsigned 16 bit integer that specifies the length in
     *            octets of the RDATA field.
     */
    public void setRDLength (int rdlength);

    
    /**
     * @return an unsigned 16 bit integer that specifies the length in octets of
     *         the RDATA field.
     */
    public int getRDLength ();
    

    /**
     * @param rdata
     *            - a variable length string of octets that describes the
     *            resource. The format of this information varies according to
     *            the TYPE and CLASS of the resource record. For example, the if
     *            the TYPE is A and the CLASS is IN, the RDATA field is a 4
     *            octet ARPA Internet address.
     *
     * @param type 
     *          - Resource record type
     */
    public void setRData (IRecordData rdata);
    
    
    /**
     * @return a variable length string of octets that describes the resource.
     *         The format of this information varies according to the TYPE and
     *         CLASS of the resource record. For example, the if the TYPE is A
     *         and the CLASS is IN, the RDATA field is a 4 octet ARPA Internet
     *         address.
     */
    public IRecordData getRData();
    
    /**
     * Creates a byte array of resource record information
     * @return - resource record s information in Bytes
     * @throws IOException 
     */
    public byte [] toByteArray() throws IOException;
    
    /**
     * Reads response information from the given input stream
     * @param dis - Stream to read response information from
     * @throws IOException 
     */
    public void readFromInputStream(DataInputStream dis) throws IOException;
    
    /**
     * Writes response information to the given stream
     * @param dos - stream to write response information to
     * @throws IOException 
     */
    public void writeToOutputStream (DataOutputStream dos) throws IOException;
    
    public String toString();
}
