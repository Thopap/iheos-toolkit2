package gov.nist.toolkit.fhir.simulators.sim.reg.store;


import gov.nist.toolkit.xdsexception.client.XdsException;

import java.io.Serializable;

public class Fol extends PatientObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public int version;
	public String lid;
	public String lastUpdateTime;
	public String[] codeList;

	public String getType() {
		return "Folder";
	}
	
	public Fol clone() {
		Fol f = new Fol();
		
		f.pid = pid;
		f.lastUpdateTime = lastUpdateTime;
		f.version = version;
		f.lid = lid;
		
		int codeListSize = codeList.length;
		f.codeList = new String[codeListSize];
		for (int i=0; i<codeListSize; i++)
			f.codeList[i] = codeList[i];
		
		f.id = id;
		f.uid = uid;
		f.pathToMetadata = pathToMetadata;
		f.setAvailabilityStatus(getAvailabilityStatus());
		
		return f;
	}
	
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	public void setLastUpdateTime(String time) throws XdsException {
		if (!hasSecondResolution(time))
			throw new XdsException("Folder.lastUpdateTime must include second resolution - received value " + time, null);
		lastUpdateTime = time;
	}

	private boolean hasSecondResolution(String time) {
		int minSize = 14; // seconds included
		if (time == null) return false;
		return time.length() >= minSize;
	}
	
}
