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

	public static QClass getValue(int value) {
		for(QClass e: QClass.values()) {
			if(e.qclass == value) {
				return e;
			}
		}
		return null;
	}
}