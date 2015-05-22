package org.dns.packet.section.record.data;

import org.dns.packet.abstraction.record.data.INULL;

public class NULL implements INULL{

   public NULL (byte [] data){
       
   }

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
