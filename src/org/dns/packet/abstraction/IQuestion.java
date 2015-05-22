package org.dns.packet.abstraction;

import org.dns.packet.abstraction.record.IRecordClass;
import org.dns.packet.abstraction.record.IRecordType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IQuestion {
    
    /**
     * @param qname
     *            - a domain name represented as a sequence of labels, where
     *            each label consists of a length octet followed by that number
     *            of octets. The domain name terminates with the zero length
     *            octet for the null label of the root. Note that this field may
     *            be an odd number of octets; no padding is used.
     */
    public void setQName(String qname);
    
    
    /**
     * @return a domain name represented as a sequence of labels, where each
     *         label consists of a length octet followed by that number of
     *         octets. The domain name terminates with the zero length octet for
     *         the null label of the root. Note that this field may be an odd
     *         number of octets; no padding is used.
     */
    public String getQName();
    
    
    /**
     * @param qtype
     *            - a two octet (16-bit) code which specifies the type of the
     *            query. The values for this field include all codes valid for a
     *            TYPE field, together with some more general codes which can
     *            match more than one type of RR.
     *            
     *            AXFR            252 A request for a transfer of an entire zone
     *            MAILB           253 A request for mailbox-related records (MB, MG or MR)
     *            MAILA           254 A request for mail agent RRs (Obsolete - see MX)
     *            *               255 A request for all records
     */
    public void setQType(IRecordType qtype);
    
    
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
    public IRecordType getQType();
    
    
    /**
     * @param qclass
     *            - a two octet (16-Bit) code that specifies the class of the query. For
     *            example, the QCLASS field is IN for the Internet.
     */
    public void setQClass(IRecordClass qclass);
    
    
    /**
     * @return a two octet (16-Bit) code that specifies the class of the query. For
     *         example, the QCLASS field is IN for the Internet.
     */
    public IRecordClass getQClass();
    
    /**
     * @return Question in bytes
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
     * Writes query information to the given stream
     * @param dos - stream to write query information to
     * @throws IOException 
     */
    public void writeToOutputStream (DataOutputStream dos) throws IOException;
    
    public String toString();
}
