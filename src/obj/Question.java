package obj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import utils.PacketReader;
import utils.enums.QClass;
import utils.enums.QType;

public class Question {
	
	private String QNAME;
	private int QTYPE;
	private int QCLASS;
	
	public Question(String qname, QType qtype, QClass qclass){
        this.setQName(qname);
        this.setQType(qtype.getQType());
        this.setQClass(qclass.getQClass());
    }
    
    
    public Question (byte [] data) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream (data);
        DataInputStream dis = new DataInputStream (bis);
        this.readFromInputStream (dis);
    }
    
    public Question (DataInputStream dis) throws IOException{
        this.readFromInputStream (dis);
    }
	
    public void setQName (String qname) {
        this.QNAME = qname;
        if(qname.replaceAll(".", "").getBytes().length > 255){
    		throw new IllegalArgumentException();
    	}
    }

    public String getQName () {
        return this.QNAME;
    }

    public void setQType (int qtype) {
        this.QTYPE = qtype;
    }

    public int getQType () {
        return this.QTYPE;
    }

    public void setQClass (int qclass) {
        this.QCLASS = qclass;
    }

    public int getQClass () {
        return this.QCLASS;
    }

    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        DataOutputStream dos = new DataOutputStream (bos);
        
        //write to output stream
        this.writeToOutputStream (dos);
        
        //flush data output stream
        dos.flush ();
        
        return bos.toByteArray ();
    }
    
    public String toString(){
        return 		"QNAME = " 		+ this.getQName () + 
                 	"\nQTYPE = " 	+ this.getQType () +
                    "\nQCLASS = " 	+ this.getQClass ();
    }
    
    public void readFromInputStream(DataInputStream dis) throws IOException {

    	//set QNAME
    	this.setQName(PacketReader.getPacketReader().readName(dis));
        
        //set QTYPE
        this.setQType(dis.readUnsignedShort());
        
        //set QCLASS
        this.setQClass(dis.readUnsignedShort());
    }

    public void writeToOutputStream (DataOutputStream dos) throws IOException {
    	// Split string by "."
        String [] labels = this.getQName ().split ("\\.");

        // Write QNAME in length/Data format.
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
        dos.writeShort(this.getQType());
        
        //write QCLASS
        dos.writeShort(this.getQClass());
    }
}
