package org.dns.packet.section;

import org.dns.packet.abstraction.IQuestion;
import org.dns.packet.abstraction.record.IRecordClass;
import org.dns.packet.abstraction.record.IRecordType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.dns.packet.section.record.RecordClass;
import org.dns.packet.section.record.RecordType;
import org.dns.util.PacketReader;

public class Question implements IQuestion {
	
	private String QNAME;
	private IRecordType QTYPE;
	private IRecordClass QCLASS;
	
	
	public Question(String qname, IRecordType qtype, IRecordClass qclass){
        this.setQName (qname);
        this.setQType (qtype);
        this.setQClass (qclass);
    }
    
    
    public Question (byte [] data) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream (data);
        DataInputStream dis = new DataInputStream (bis);
        this.readFromInputStream (dis);
    }
    
    public Question (DataInputStream dis) throws IOException{
        this.readFromInputStream (dis);
    }
	
    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#setQName(java.lang.String)
     */
    @Override
    public void setQName (String qname) {
        this.QNAME = qname;
        if(qname.replaceAll(".", "").getBytes().length > 255){
    		throw new IllegalArgumentException();
    	}
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#getQName()
     */
    @Override
    public String getQName () {
        return this.QNAME;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#setQType(int)
     */
    @Override
    public void setQType (IRecordType qtype) {
        this.QTYPE = qtype;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#getQType()
     */
    @Override
    public IRecordType getQType () {
        return this.QTYPE;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#setQClass(int)
     */
    @Override
    public void setQClass (IRecordClass qclass) {
        this.QCLASS = qclass;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#getQClass()
     */
    @Override
    public IRecordClass getQClass () {
        return this.QCLASS;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#toByteArray()
     */
    @Override
    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        DataOutputStream dos = new DataOutputStream (bos);
        
        //write to output stream
        this.writeToOutputStream (dos);
        
        //flush data output stream
        dos.flush ();
        
        return bos.toByteArray ();
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return "QNAME = " + this.getQName ()
                + "\nQTYPE = " + this.getQType ()
                        + "\nQCLASS = " + this.getQClass ();
    }
    
    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#readFromInputStream(java.io.DataInputStream)
     */
    public void readFromInputStream(DataInputStream dis) throws IOException{

        //read QNAME. Format: Length(Byte)/Data(n(length) Bytes)
        /*while(true){
            int length = dis.readByte ();

            //QNAME is terminated by length 0;
            if(length == 0){
                break;
            }

            for(int i = 0; i < length; i++){
                qname.append ((char)dis.readByte ());
            }
            qname.append (".");
        }
        System.out.println(qname);*/

    	this.setQName(PacketReader.getPacketReader().readName(dis));
        
        //set QTYPE
        this.setQType (new RecordType (dis.readUnsignedShort ()));
        
        //set QCLASS
        this.setQClass (new RecordClass (dis.readUnsignedShort ()));
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IQuestion#writeToOutputStream(java.io.DataOutputStream)
     */
    @Override
    public void writeToOutputStream (DataOutputStream dos) throws IOException {
    	//split string by "."
        String [] labels = this.getQName ().split ("\\.");

        //write QNAME in length/Data format.
        //1Byte indicating length of message,
        //follows by that many bytes of data
        for (int i = 0; i < labels.length; i++) {
            byte [] label = labels[i].getBytes ();
            int length = label.length;
            
            //each label cannot exceed 63
            if(length > 63){
                throw new IllegalArgumentException ();
            }
            dos.writeByte (label.length);
            dos.write (label);
        }
        //Terminate QNAME by writing 0x00
        dos.writeByte (0);
        
        //write QTYPE
        dos.writeShort (this.getQType ().getType ());
        
        //write QCLASS
        dos.writeShort (this.getQClass ().getCLass ());
    }
}
