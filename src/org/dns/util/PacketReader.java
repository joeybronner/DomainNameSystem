package org.dns.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * This class needs to be refactored. readName() is intended to read name from an offset to support compression
 * Need to come up with a better implementation for it.
 */


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
	
	public String readName(DataInputStream dis) throws IOException{
		DataInputStream diss = dis;
		StringBuilder name = new StringBuilder();
		int length = diss.readByte();
		
		while(length != 0){
			if(length < 0){
				diss = new DataInputStream(new ByteArrayInputStream(data, diss.readByte(), 255));
				length = diss.readByte(); //read new length
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
	
	private String readNameFromOffset(int offset) throws IOException{
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data, offset, 255));
		StringBuilder name = new StringBuilder();
		int length = dis.readByte();
		
		while(length != 0){
			if(length < 0){
				return readNameFromOffset(dis.readByte());
			}else{
				for(int i = 0; i < length; i++){
	                name.append ((char)dis.readByte ());
	            }
	            length = dis.readByte();
	            name.append (".");
			}
		}
		return name.toString();
	}

}
