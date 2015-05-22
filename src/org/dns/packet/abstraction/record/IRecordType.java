package org.dns.packet.abstraction.record;

public interface IRecordType {
    /**
     * set record type. 16-Bit
     * @param type - record type
     */
    public void setType(int type);
    
    /**
     * Get record type
     * @return - record type
     */
    public int getType();
    
    public String toString();
}
