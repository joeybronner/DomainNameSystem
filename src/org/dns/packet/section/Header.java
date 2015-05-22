package org.dns.packet.section;

import org.dns.packet.abstraction.IHeader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Header implements IHeader {
    private int ID;
    private boolean QR;
    private int OPCODE;
    private boolean AA;
    private boolean TC;
    private boolean RD;
    private boolean RA;
    private int Z;
    private int RCODE;
    private int QDCOUNT;
    private int ANCOUNT;
    private int NSCOUNT;
    private int ARCOUNT;
    public static int QUERY_STANDARD = 0;
    public static int QUERY_INVERSE = 1;
    public static int QUERY_SERVER_STATUS = 2;
    
    

    public Header(int id, boolean qr, int opcode, boolean aa, boolean tc,
            boolean rd, boolean ra, int z, int rcode, int qdcount, int ancount,
            int nscount, int arcount) {
        
        this.setID (id);
        this.setQR (qr);
        this.setOpcode (opcode);
        this.setAA (aa);
        this.setTC (tc);
        this.setRD (rd);
        this.setRA (ra);
        this.setZ (z);
        this.setRCode (rcode);
        this.setQDCount (qdcount);
        this.setANCount (ancount);
        this.setNSCount (nscount);
        this.setARCount (arcount);
    }
    
    public Header(byte [] header) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream (header);
        DataInputStream dis = new DataInputStream (bis);
        this.readFromInputStream (dis);
    }
    
    public Header(DataInputStream dis) throws IOException{
        this.readFromInputStream (dis);
    }
    
    
    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setID(int)
     * 
     * Must be 16-Bit, so it cannot exceed 255
     */
    @Override
    public void setID (int id) {
        if(id < 0 || id > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.ID = id;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getID()
     */
    @Override
    public int getID () {
        return this.ID;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setQR(boolean)
     */
    @Override
    public void setQR (boolean isResponse) {
        this.QR = isResponse;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getQR()
     */
    @Override
    public boolean getQR () {
        return this.QR;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#opcode(int)
     */
    @Override
    public void setOpcode (int code) {
        //range 0 - 15
        if(code > 15){
            throw new IllegalArgumentException ();
        }else{
            this.OPCODE = code;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getOpcode()
     */
    @Override
    public int getOpcode () {
        return this.OPCODE;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setAA(boolean)
     */
    @Override
    public void setAA (boolean aa) {
        this.AA = aa;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getAA()
     */
    @Override
    public boolean getAA () {
        return this.AA;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setTC(boolean)
     */
    @Override
    public void setTC (boolean tc) {
        this.TC = tc;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getTC()
     */
    @Override
    public boolean getTC () {
        return this.TC;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setRD(boolean)
     */
    @Override
    public void setRD (boolean rd) {
        this.RD = rd;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getRD()
     */
    @Override
    public boolean getRD () {
        return this.RD;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setRA(boolean)
     */
    @Override
    public void setRA (boolean ra) {
        this.RA = ra;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getRA()
     */
    @Override
    public boolean getRA () {
        return this.RA;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setZ(int)
     */
    @Override
    public void setZ (int z) {
        this.Z = z;
        
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getZ()
     */
    @Override
    public int getZ () {
        return this.Z;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setRCode(int)
     */
    @Override
    public void setRCode (int rcode) {
        //range 0 - 15
        if(rcode > 15){
            throw new IllegalArgumentException ();
        }else{
            this.RCODE = rcode;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getRCode()
     */
    @Override
    public int getRCode () {
        return this.RCODE;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setQDCount(int)
     */
    @Override
    public void setQDCount (int qdCount) {
        //range 0 - 65535
        if(qdCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.QDCOUNT = qdCount;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getQDCount()
     */
    @Override
    public int getQDCount () {
        return this.QDCOUNT;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setANCount(int)
     */
    @Override
    public void setANCount (int anCount) {
        //range 0 - 65535
        if(anCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.ANCOUNT = anCount;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getANCount()
     */
    @Override
    public int getANCount () {
        return this.ANCOUNT;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#setNSCount(int)
     */
    @Override
    public void setNSCount (int nsCount) {
        //range 0 - 65535
        if(nsCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.NSCOUNT = nsCount;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getNSCount()
     */
    @Override
    public int getNSCount () {
        return this.NSCOUNT;
    }

    /**
     * @param arCount
     */
    @Override
    public void setARCount (int arCount) {
        //range 0 - 65535
        if(arCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.ARCOUNT = arCount;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#getARCount()
     */
    @Override
    public int getARCount () {
        return this.ARCOUNT;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#toByteArray()
     */
    @Override
    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream (6);
        DataOutputStream dos = new DataOutputStream (bos);
        
        //write data to output stream
        this.writeToOutputStream (dos);
        
        //flush output stream
        dos.flush ();
        
        //return header in byte array
        return bos.toByteArray ();
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return "ID = " + this.getID()
                + "\nQR = " + this.getQR () 
                + "\nOPCODE = " + this.getOpcode () 
                + "\nAA = " + this.getAA ()
                + "\nTC = " + this.getTC ()
                + "\nRD = " + this.getRD ()
                + "\nRA = " + this.getRA () 
                + "\nZ = " + this.getZ () 
                + "\nRCODE = " + this.getRCode () 
                + "\nQDCOUNT = " + this.getQDCount ()
                + "\nANCOUNT = " + this.getANCount () 
                + "\nNSCOUNT = " + this.getNSCount ()
                + "\nARCOUNT = " + this.getARCount ();
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#readFromInputStream(java.io.DataInputStream)
     */
    public void readFromInputStream(DataInputStream dis) throws IOException {
        //read ID
        this.setID (dis.readUnsignedShort ());
        //read 3rd and 4th octets
        int header_octet34 = dis.readUnsignedShort ();
        //read QR
        this.setQR (((header_octet34 >> 15) & 1) == 1);
        //read OPCODE
        this.setOpcode ((header_octet34 >> 11) & 15);
        //read AA
        this.setAA (((header_octet34 >> 10) & 1) == 1);
        //read TC
        this.setTC (((header_octet34 >> 9) & 1) == 1);
        //read RD
        this.setRD (((header_octet34 >> 8) & 1) == 1);
        //read RA
        this.setRA (((header_octet34 >> 7) & 1) == 1);
        //read Z
        this.setZ ((header_octet34 >> 4) & 7);
        //read RCODE
        this.setRCode (header_octet34 & 15);
        //read QDCOUNT
        this.setQDCount (dis.readUnsignedShort ());
        //read ANCOUNT
        this.setANCount (dis.readUnsignedShort ());
        //read NSCOUNT
        this.setNSCount (dis.readUnsignedShort ());
        //read ARCOUNT
        this.setARCount (dis.readUnsignedShort ());
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IHeader#writeToOutputStream(java.io.DataOutputStream)
     */
    @Override
    public void writeToOutputStream (DataOutputStream dos) throws IOException {
    	//write ID
        dos.writeShort (this.getID ());
        
        int octet_34 = 0;
        //set QR
        if(this.getQR ()){
            octet_34 += 1 << 15;
        }
        
        //set Opcode 
        octet_34 += this.getOpcode () << 11;
        
        //set AA
        if(this.getAA ()){
            octet_34 += 1 << 10;
        }
        
        //set TC
        if(this.getTC ()){
            octet_34 += 1 << 9;
        }
        
        //set RD
        if(this.getRD ()){
            octet_34 += 1 << 8;
        }
        
        //set RA
        if(this.getRA ()){
            octet_34 += 1 << 7;
        }
        
        //set Z 
        octet_34 += this.getZ () << 4;
        
        //set RCODE
        octet_34 += this.getRCode ();
        
        //write octet 3 and 4 to the output stream
        dos.writeShort (octet_34);
        
        //write QDCOUNT
        dos.writeShort (this.getQDCount ());
        
        //write ANCOUNT
        dos.writeShort (this.getANCount ());
        
        //write NSCOUNT
        dos.writeShort (this.getNSCount ());
        
        //write ARCOUNT
        dos.writeShort (this.getARCount ());
    }
}
