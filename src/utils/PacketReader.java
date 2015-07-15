package utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PacketReader {
	
	private static PacketReader reader;
	private byte [] data;
	
	
	public static PacketReader getPacketReader(){
		if(reader == null){
			reader = new PacketReader();
		}
		return reader;
	}
	
	public void setData(byte [] data){
		this.data = data;
	}
	
	public String readName(DataInputStream dis) throws IOException {
		DataInputStream diss = dis;
		StringBuilder name = new StringBuilder();
		int length = diss.readByte();
		
		while(length != 0){
			if(length < 0){
				diss = new DataInputStream(new ByteArrayInputStream(data, diss.readByte(), 255));
				length = diss.readByte();
				if(length == 0) break;
			}
			for(int i = 0; i < length; i++){
                name.append ((char)diss.readByte ());
            }
            length = diss.readByte();
            name.append (".");
		}
		return name.toString();
	}
}
