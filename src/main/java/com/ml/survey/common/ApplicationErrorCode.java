package com.ml.survey.common;

import java.util.HashMap;
import java.util.Map;

public enum ApplicationErrorCode {
	
	SYSTEM_EXCEPTION_CODE("SYSEXP001"),
	RESOURCE_NOTFOUND_EXCEPTION_CODE("BUSEXP001");
	
	private static final Map<String, ApplicationErrorCode> BY_LABEL = new HashMap<>();
	
    static {
        for (ApplicationErrorCode e : values()) {
            BY_LABEL.put(e.label, e);
        }
    }
	
	public final String label;

	private ApplicationErrorCode(String label) {
		this.label = label;
	}
	
	public static ApplicationErrorCode valueOfLabel(String label) {
	    return BY_LABEL.get(label);
	}
	

}
