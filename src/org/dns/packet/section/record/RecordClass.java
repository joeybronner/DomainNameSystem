package org.dns.packet.section.record;
import org.dns.packet.abstraction.record.IRecordClass;


public class RecordClass implements IRecordClass{
    public final static int QCLASS_IN = 1;
    public final static int QCLASS_CS = 2;
    public final static int QCLASS_CH = 3;
    public final static int QCLASS_HS = 4;
    
    
    private int cLass;
    
    public RecordClass(int cLass) {
        this.setCLass (cLass);
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IRecordClass#setCLass(int)
     */
    @Override
    public void setCLass (int cLass) {
        this.cLass = cLass;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.IRecordClass#getCLass()
     */
    @Override
    public int getCLass () {
        return this.cLass;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        switch (this.getCLass ()) {
        case 1:
            return "IN";
        case 2:
            return "CS";
        case 3:
            return "CH";
        case 4:
            return "HS";
        default:
            return "Unknown Class";
        }
    }
}
