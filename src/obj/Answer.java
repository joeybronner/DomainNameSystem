package obj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Answer {
    
    private Record [] records;
    
    public Answer(int ancount, DataInputStream dis) throws IOException{
        readFromInputStream (ancount, dis);
    }
    
    public Answer(int ancount, byte [] data) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream (bis);
        readFromInputStream (ancount, dis);
    }
    
    public Answer(Record [] records) {
    	this.setRecords(records);
    }
        
    public void readFromInputStream(int ancount, DataInputStream dis) throws IOException{
        Record [] recs = new Record [ancount];
        for(int i = 0; i < ancount; i++){
            recs[i] = new Record (dis);
        }
        this.setRecords(recs);
    }

    public void writeToOutputStream (DataOutputStream dos) throws IOException {
    	Record [] recs = (Record[]) this.getRecords();
        for(int i = 0; i < recs.length; i++){
            dos.write (recs[i].toByteArray ());
        }
    }

    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        DataOutputStream dos = new DataOutputStream (bos);
        this.writeToOutputStream (dos);
        dos.flush ();
        return bos.toByteArray ();
    }
    
    public String toString(){
    	Record [] recs = (Record[]) this.getRecords();
        StringBuilder sb = new StringBuilder ();
        for(int i = 0; i < recs.length; i++){
            sb.append ("\n");
            sb.append ("Record " + (i+1) + ":\n");
            sb.append (recs[i].toString () + "\n");
        }
        return sb.toString ();
    }

	public void setRecords(Record[] records2) {
		this.records = (Record[]) records2;
	}

	public Record[] getRecords() {
		return this.records;
	}
}
