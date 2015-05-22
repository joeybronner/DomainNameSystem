package org.dns.packet.abstraction.record;

public interface IRecordClass {
    /**
     * set record class. 16-Bit
     * @param cLass - record class
     */
    public void setCLass(int cLass);
    
    /**
     * Get record class
     * @return - record cLass
     */
    public int getCLass();
    
    public String toString();
}
