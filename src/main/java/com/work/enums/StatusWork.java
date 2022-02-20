package com.work.enums;

public enum StatusWork {
	PLANNING("001", "Planning"),
	DOING("002", "Doing"),
	COMPLETE("003", "Complete");

	private String code;
	private String message;
	
	StatusWork(final String code, final String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
