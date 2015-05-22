package org.dns.packet.section.record;

import org.dns.packet.abstraction.record.IRecord;
import org.dns.packet.abstraction.record.IRecordClass;
import org.dns.packet.abstraction.record.IRecordData;
import org.dns.packet.abstraction.record.IRecordType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.dns.util.PacketReader;

public class Record implements IRecord {
    
    private String NAME;
    private IRecordType TYPE;
    private IRecordClass CLASS;
    private int TTL;
    private int RDLENGTH;
    private IRecordData RDATA;

    public Record(DataInputStream dis) throws IOException{
        this.readFromInputStream (dis);
    }
    
    public Record (byte [] data) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream (data);
        DataInputStream dis = new DataInputStream (bis);
        this.readFromInputStream (dis);
    }
    
    public Record(String name, IRecordType type, IRecordClass cLass, int ttl,
            int rdlength, IRecordData rdata) {
        //set Name
        this.setName (name);
        //set TYPE
        this.setType (type);
        //set CLASS
        this.setCLass (cLass);
        //set TTL
        this.setTTL (ttl);
        //set RDLENGTH
        this.setRDLength(rdlength);
        //set RDATA
        this.setRData (rdata);
    }
    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#setName(java.lang.String)
     */
    @Override
    public void setName (String name) {
        this.NAME = name;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#getName()
     */
    @Override
    public String getName () {
        return this.NAME;
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#setType(org.dns.interfaces.org.dns.packet.section.record.IRecordType)
     */
    @Override
    public void setType (IRecordType type) {
        this.TYPE = type;
    }


    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#getType()
     */
    @Override
    public IRecordType getType () {
        return this.TYPE;
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#setCLass(org.dns.interfaces.org.dns.packet.section.record.IRecordClass)
     */
    @Override
    public void setCLass (IRecordClass cLass) {
        this.CLASS = cLass;
    }

   
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#getCLass()
     */
    @Override
    public IRecordClass getCLass () {
        return this.CLASS;
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#setTTL(int)
     */
    @Override
    public void setTTL (int ttl) {
        //range 0 - 2147483647
        if(ttl > 2147483647){
            throw new IllegalArgumentException ();
        }else{
            this.TTL = ttl;
        }
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#getTTL()
     */
    @Override
    public int getTTL () {
        return this.TTL;
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#setRDLength(int)
     */
    @Override
    public void setRDLength (int rdlength) {
        //range 0 - 65535
        if(rdlength > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.RDLENGTH = rdlength;
        }
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#getRDLength()
     */
    @Override
    public int getRDLength () {
        return this.RDLENGTH;
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#setRData(org.dns.interfaces.org.dns.packet.section.record.IRecordData)
     */
    @Override
    public void setRData (IRecordData rdata) {
        this.RDATA = rdata;
    }
   
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#getRData()
     */
    @Override
    public IRecordData getRData () {
        return this.RDATA;
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#toByteArray()
     */
    @Override
    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        DataOutputStream dos = new DataOutputStream (bos);
        
        //write data to output stream
        this.writeToOutputStream (dos);
        dos.flush ();
        return bos.toByteArray ();
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#readFromInputStream(java.io.DataInputStream)
     */
    @Override
    public void readFromInputStream (DataInputStream dis) throws IOException {
        //this.setName (dis.readUnsignedShort () + ""); //pointer to Name in Question
    	this.setName(PacketReader.getPacketReader().readName(dis));
        this.setType (new RecordType (dis.readUnsignedShort ()));
        this.setCLass (new RecordClass (dis.readUnsignedShort ()));
        this.setTTL (dis.readInt ());
        this.setRDLength (dis.readUnsignedShort ());
        byte [] data = new byte [this.getRDLength ()];
        dis.readFully (data);
        IRecordData rdata = RecordData.getRData (this.getType (), data);
        this.setRData (rdata);        
    }

    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.section.record.IRecord#writeToOutputStream(java.io.DataOutputStream)
     */
    @Override
    public void writeToOutputStream (DataOutputStream dos) throws IOException {
    	//write name to the output stream
    	//split string by "."
        String [] labels = this.getName ().split ("\\.");
        
        //write QNAME in length/Data format.
        //1Byte indicating length of message,
        //follows by that many bytes of data
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
        //Terminate NAME by writing 0x00
        dos.writeByte (0);
        
        //write TYPE
        dos.writeShort(this.getType().getType());
        
        //write CLASS
        dos.writeShort(this.getCLass().getCLass());
        
        //write TTL
        dos.writeInt(this.getTTL());
        
        //write RDLENGTH
        dos.writeShort(this.getRDLength());
        
        //write RDATA
        dos.write(this.getRData().toByteArray());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return "NAME = " + this.getName ()
                + "\nTYPE = " + this.getType ()
                + "\nCLASS = " + this.getCLass ()
                + "\nTTL = " + this.getTTL ()
                + "\nRDLENGTH = " + this.getRDLength () 
                + "\nRDATA = " + this.getRData ();
    }
}
