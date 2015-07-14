package utils.enums;

public enum QClass { 
    IN (1), 
    CS (2), 
    CH (3),
    HS (4);

    private int qclass;

    QClass(int qc) {
        this.qclass = qc;
    }

    public int getQClass() { 
        return qclass;
    }
}