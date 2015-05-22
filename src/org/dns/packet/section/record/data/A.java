package org.dns.packet.section.record.data;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.dns.packet.abstraction.record.data.IA;


public class A implements IA {    
    private Inet4Address address;
    
    public A(byte [] a) throws UnknownHostException{
        this.setAddress((Inet4Address) Inet4Address.getByAddress(a));
    }
    
    public A (Inet4Address address){
    	this.setAddress(address);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return address.getHostAddress();
    }

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.packet.section.record.data.IA#setAddress(java.net.Inet4Address)
	 */
	@Override
	public void setAddress(Inet4Address address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.packet.section.record.data.IA#getAddress()
	 */
	@Override
	public Inet4Address getAddress() {
		return this.address;
	}

	
	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.packet.section.record.IRecordData#size()
	 */
	@Override
	public int size() {
		return 4;
	}
	
	/* (non-Javadoc)
	 * @see org.dns.packet.section.record.RecordData#toByteArray()
	 */
	public byte[] toByteArray(){
		return this.getAddress ().getAddress ();
	}
}
