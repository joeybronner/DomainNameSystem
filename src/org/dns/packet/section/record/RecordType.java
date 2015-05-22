package org.dns.packet.section.record;

import org.dns.packet.abstraction.record.IRecordType;

public class RecordType implements IRecordType{
    public final static int QTYPE_A = 1;
    public final static int QTYPE_NS = 2;
    public final static int QTYPE_MD = 3;
    public final static int QTYPE_MF = 4;
    public final static int QTYPE_CNAME = 5;
    public final static int QTYPE_SOA = 6;
    public final static int QTYPE_MB = 7;
    public final static int QTYPE_MG = 8;
    public final static int QTYPE_MR = 9;
    public final static int QTYPE_NULL = 10;
    public final static int QTYPE_WKS = 11;
    public final static int QTYPE_PTR = 12;
    public final static int QTYPE_HINFO = 13;
    public final static int QTYPE_MINFO = 14;
    public final static int QTYPE_MX = 15;
    public final static int QTYPE_TXT = 16;
    public final static int QTYPE_AXFR = 252;
    public final static int QTYPE_MAILB = 253;
    public final static int QTYPE_MAILA = 254;
    public final static int QTYPE_ALL = 255;
    
    private int type;
    public RecordType(int type){
        this.setType (type);
    }
    
    /* (non-Javadoc)
     * @see org.dns.interfaces.records.IRecordType#setType(int)
     */
    public void setType(int type){
        if(type > 65535){
            throw new IllegalArgumentException ();
        }else{
            this.type = type;
        }
    }
    
    /* (non-Javadoc)
     * @see org.dns.interfaces.records.IRecordType#getType()
     */
    public int getType(){
        return this.type;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        switch (this.getType ()) {
        case 1:
            return "A";
        case 2:
            return "NS";
        case 3:
            return "MD";
        case 4:
            return "MF";
        case 5:
            return "CNAME";
        case 6:
            return "SOA";
        case 7:
            return "MB";
        case 8:
            return "MG";
        case 9:
            return "MR";
        case 10:
            return "NULL";
        case 11:
            return "WKS";
        case 12:
            return "PTR";
        case 13:
            return "HINFO";
        case 14:
            return "MINFO";
        case 15:
            return "MX";
        case 16:
            return "TXT";
        case 252:
            return "AXFR";
        case 253:
            return "MAILB";
        case 254:
            return "MAILA";
        case 255:
            return "ALL";
        default:
            return "Unused Type";
        }
    }
}
