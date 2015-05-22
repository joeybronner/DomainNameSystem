package org.dns.packet.abstraction.record;

public interface IRecordData {
	/**
	 * returns record data in bytes
	 * @return - Record Data
	 */
	public byte [] toByteArray();
	
	public String toString();
	
	/**
	 * Number of octets that will be used by the record
	 * @return size of the record
	 */
	public int size();
	
}
