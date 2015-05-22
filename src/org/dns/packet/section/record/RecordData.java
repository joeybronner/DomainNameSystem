package org.dns.packet.section.record;

import java.net.UnknownHostException;

import org.dns.packet.abstraction.record.IRecordData;
import org.dns.packet.abstraction.record.IRecordType;
import org.dns.packet.section.record.data.A;
import org.dns.packet.section.record.data.ALL;
import org.dns.packet.section.record.data.AXFR;
import org.dns.packet.section.record.data.CNAME;
import org.dns.packet.section.record.data.HINFO;
import org.dns.packet.section.record.data.MAILA;
import org.dns.packet.section.record.data.MAILB;
import org.dns.packet.section.record.data.MB;
import org.dns.packet.section.record.data.MD;
import org.dns.packet.section.record.data.MF;
import org.dns.packet.section.record.data.MG;
import org.dns.packet.section.record.data.MINFO;
import org.dns.packet.section.record.data.MR;
import org.dns.packet.section.record.data.MX;
import org.dns.packet.section.record.data.NS;
import org.dns.packet.section.record.data.NULL;
import org.dns.packet.section.record.data.PTR;
import org.dns.packet.section.record.data.SOA;
import org.dns.packet.section.record.data.TXT;
import org.dns.packet.section.record.data.WKS;

public class RecordData implements IRecordData{
    
    /**
     * Reads record information from given data and creates an object
     * @param type - type of record
     * @param data - record data
     * @return - RecordData object
     * @throws UnknownHostException 
     */
    public static IRecordData getRData(IRecordType type, byte [] data) throws UnknownHostException{

        switch (type.getType ()) {
        case RecordType.QTYPE_A:
            return new A (data);
        case RecordType.QTYPE_NS:
            return new NS (data);
        case RecordType.QTYPE_MD:
            return new MD(data);
        case RecordType.QTYPE_MF:
            return new MF(data);
        case RecordType.QTYPE_CNAME:
            return new CNAME(data);
        case RecordType.QTYPE_SOA:
            return new SOA(data);
        case RecordType.QTYPE_MB:
            return new MB(data);
        case RecordType.QTYPE_MG:
            return new MG(data);
        case RecordType.QTYPE_MR:
            return new MR(data);
        case RecordType.QTYPE_NULL:
            return new NULL(data);
        case RecordType.QTYPE_WKS:
            return new WKS(data);
        case RecordType.QTYPE_PTR:
            return new PTR(data);
        case RecordType.QTYPE_HINFO:
            return new HINFO(data);
        case RecordType.QTYPE_MINFO:
            return new MINFO(data);
        case RecordType.QTYPE_MX:
            return new MX(data);
        case RecordType.QTYPE_TXT:
            return new TXT(data);
        case RecordType.QTYPE_AXFR:
            return new AXFR(data);
        case RecordType.QTYPE_MAILB:
            return new MAILB(data);
        case RecordType.QTYPE_MAILA:
            return new MAILA(data);
        case RecordType.QTYPE_ALL:
            return new ALL(data);
        default:
            return null;
        }
    }

	/* (non-Javadoc)
	 * @see org.dns.interfaces.org.dns.packet.section.record.IRecordData#toByteArray()
	 */
	@Override
	public byte[] toByteArray(){
		return null;
	}

	@Override
	public int size(){
		return 0;
	}
	
	public String toString(){
		return null;
	}
}
