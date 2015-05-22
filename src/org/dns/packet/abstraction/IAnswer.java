package org.dns.packet.abstraction;

import org.dns.packet.abstraction.record.IRecord;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IAnswer {
    /**
     * Read resource records from the input stream
     * @param ancount - answer count in header
     * @param dis - input stream to read from
     * @throws IOException
     */
    public void readFromInputStream(int ancount, DataInputStream dis) throws IOException;
    
    /**
     * Writes resource records to output stream
     * @param dos - output stream to write
     * @throws IOException 
     */
    public void writeToOutputStream(DataOutputStream dos) throws IOException;
    
    /**
     * Creates a byte array of resource records
     * @return - resource records in bytes
     * @throws IOException
     */
    public byte[] toByteArray() throws IOException;
    
    /**
     * Set resource records
     * @param records - array of resource records
     */
    public void setRecords(IRecord [] records);
    
    /**
     * Get resource records
     * @return - Array of resource records
     */
    public IRecord [] getRecords();
    
    public String toString();
    
}
