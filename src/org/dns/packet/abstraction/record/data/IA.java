package org.dns.packet.abstraction.record.data;

import org.dns.packet.abstraction.record.IRecordData;

import java.net.Inet4Address;

public interface IA extends IRecordData {
	
	/**
	 * set Inet4Address for the record
	 * @param address - IP address
	 */
	public void setAddress(Inet4Address address);
	
	/**
	 * get Inter4Address of the record
	 * @return - IP address
	 */
	public Inet4Address getAddress();
	
}
