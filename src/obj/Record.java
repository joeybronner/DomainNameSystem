package obj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import utils.PacketReader;
import utils.enums.QClass;
import utils.enums.QType;

public class Record {
    
    private String NAME;
    private QType TYPE;
    private int CLASS;
    private int TTL;
    private int RDLENGTH;
    private String RDATA;

    public Record(DataInputStream dis) throws IOException{
        this.readFromInputStream (dis);
    }
    
    public Record (byte [] data) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream (data);
        DataInputStream dis = new DataInputStream (bis);
        this.readFromInputStream (dis);
    }
    
    public Record(String name, QType type, QClass cLass, int ttl,
            int rdlength, String rdata) {
        //set Name
        this.setName (name);
        //set TYPE
        this.setType (type);
        //set CLASS
        this.setCLass(cLass.getQClass());
        //set TTL
        this.setTTL (ttl);
        //set RDLENGTH
        this.setRDLength(rdlength);
        //set RDATA
        this.setRData(rdata);
    }

    public void setName (String name) {
        this.NAME = name;
    }

    public String getName () {
        return this.NAME;
    }

    public void setType(QType type) {
        this.TYPE = type;
    }

    public QType getType () {
        return this.TYPE;
    }

    public void setCLass (int cLass) {
        this.CLASS = cLass;
    }

    public int getCLass() {
        return this.CLASS;
    }
    
    public String getCLassToString() {
        return QClass.getValue(this.CLASS).name();
    }

    public void setTTL (int ttl) {
        if(ttl > 2147483647){
            throw new IllegalArgumentException ();
        }else{
            this.TTL = ttl;
        }
    }

    public int getTTL () {
        return this.TTL;
    }

    public void setRDLength (int rdlength) {
        if(rdlength > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.RDLENGTH = rdlength;
        }
    }

    public int getRDLength () {
        return this.RDLENGTH;
    }

    public void setRData (String rdata) {
        this.RDATA = rdata;
    }

    public String getRData () {
        return this.RDATA;
    }

    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        DataOutputStream dos = new DataOutputStream (bos);
        
        //write data to output stream
        this.writeToOutputStream (dos);
        dos.flush ();
        return bos.toByteArray ();
    }

    public void readFromInputStream (DataInputStream dis) throws IOException {
    	this.setName(PacketReader.getPacketReader().readName(dis));
        this.setType (QType.getValue(dis.readUnsignedShort()));
        this.setCLass (dis.readUnsignedShort());
        this.setTTL (dis.readInt());
        this.setRDLength (dis.readUnsignedShort());
        byte [] data = new byte [this.getRDLength()];
        dis.readFully(data);
        String rdata = new String(data, StandardCharsets.UTF_8);
        this.setRData(rdata);
    }

    public void writeToOutputStream (DataOutputStream dos) throws IOException {
  
        String [] labels = this.getName ().split ("\\.");
        for (int i = 0; i < labels.length; i++) {
            byte [] label = labels[i].getBytes ();
            int length = label.length;
            
            //a label cannot exceed 254 octets
            if(length > 254){
                throw new IllegalArgumentException ();
            }
            dos.writeByte (label.length);
            dos.write (label);
        }
        
        //Terminate NAME with 0x00
        dos.writeByte (0);
        
        //write TYPE
        dos.writeShort(this.getType().ordinal());
        
        //write CLASS
        dos.writeChars(QClass.getValue(CLASS).name());
        
        //write TTL
        dos.writeInt(this.getTTL());
        
        //write RDLENGTH
        dos.writeShort(this.getRDLength());
        
        //write RDATA
        dos.write(this.getRData().toString().getBytes());
    }

    public String toString(){
        return "NAME = " + this.getName ()
                + "\nTYPE = " + this.getType ()
                + "\nCLASS = " + this.getCLassToString()
                + "\nTTL = " + this.getTTL ()
                + "\nRDLENGTH = " + this.getRDLength () 
                + "\nRDATA = " + this.getRData ();
    }
}
