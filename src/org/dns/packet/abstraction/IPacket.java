package org.dns.packet.abstraction;

import java.io.IOException;



public interface IPacket {
    
    /**
     * Set header of the DNS org.dns.packet
     * @param header - org.dns.packet header
     */
    public void setHeader(IHeader header);
    /**
     * Returns header of the org.dns.packet
     * @return - org.dns.packet header
     */
    public IHeader getHeader();
    
    /**
     * Set DNS query
     * @param question - DNS Query
     */
    public void setQuestion(IQuestion question);
    /**
     * return DNS query
     * @return - DNS Query
     */
    public IQuestion getQuestion();
    
    /**
     * Set resource record answering the query
     * @param answer - resource record
     */
    public void setAnswer(IAnswer answer);
    /**
     * Get resource record answering the query
     * @return - resource record
     */
    public IAnswer getAnswer();
    
    /**
     * Set resource records pointing toward an authority
     * @param authority - resource record
     */
    public void setAuthority(IAuthority authority);
    /**
     * Get resource records pointing toward an authority
     * @return Resource Record
     */
    public IAuthority getAuthority();
    
    /**
     * Set resource records holding additional information
     * @param additional - additional resource records
     */
    public void setAdditional(IAdditional additional);
    /**
     * Get resource records holding additional information
     * @return additional resource records
     */
    public IAdditional getAdditional();
    
    /**
     * Convert org.dns.packet to byte array
     * @return - org.dns.packet in Bytes
     * @throws IOException 
     */
    public byte [] toByteArray() throws IOException;
    
    
    public String toString();

}
