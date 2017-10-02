package com.rabbit.receiver.enums;

public enum QueueSuffixEnumeration {
	ERROR_SUFFIX(".error"),
	SKIP_SUFFIX(".skip");
	
	private String suffix;
	
	QueueSuffixEnumeration(String Type){
		this.suffix=Type;
	}
	
	public String getSuffix(){
		return suffix;
	}
}
