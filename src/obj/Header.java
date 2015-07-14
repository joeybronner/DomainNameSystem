package obj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Header {
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

    public void setID (int id) {
        if(id < 0 || id > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.ID = id;
        }
    }

    public int getID () {
        return this.ID;
    }

    public void setQR (boolean isResponse) {
        this.QR = isResponse;
    }

    public boolean getQR () {
        return this.QR;
    }

    public void setOpcode (int code) {
        //range 0 - 15
        if(code > 15){
            throw new IllegalArgumentException ();
        }else{
            this.OPCODE = code;
        }
    }

    public int getOpcode () {
        return this.OPCODE;
    }

    public void setAA (boolean aa) {
        this.AA = aa;
    }

    public boolean getAA () {
        return this.AA;
    }

    public void setTC (boolean tc) {
        this.TC = tc;
    }

    public boolean getTC () {
        return this.TC;
    }

    public void setRD (boolean rd) {
        this.RD = rd;
    }

    public boolean getRD () {
        return this.RD;
    }

    public void setRA (boolean ra) {
        this.RA = ra;
    }

    public boolean getRA () {
        return this.RA;
    }

    public void setZ (int z) {
        this.Z = z;
        
    }

    public int getZ () {
        return this.Z;
    }

    public void setRCode (int rcode) {
        //range 0 - 15
        if(rcode > 15){
            throw new IllegalArgumentException ();
        }else{
            this.RCODE = rcode;
        }
    }

    public int getRCode () {
        return this.RCODE;
    }

    public void setQDCount (int qdCount) {
        //range 0 - 65535
        if(qdCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.QDCOUNT = qdCount;
        }
    }

    public int getQDCount () {
        return this.QDCOUNT;
    }

    public void setANCount (int anCount) {
        //range 0 - 65535
        if(anCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.ANCOUNT = anCount;
        }
    }

    public int getANCount () {
        return this.ANCOUNT;
    }
    
    public void setNSCount (int nsCount) {
        //range 0 - 65535
        if(nsCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.NSCOUNT = nsCount;
        }
    }

    public int getNSCount () {
        return this.NSCOUNT;
    }

    public void setARCount (int arCount) {
        //range 0 - 65535
        if(arCount > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.ARCOUNT = arCount;
        }
    }

    public int getARCount () {
        return this.ARCOUNT;
    }

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
