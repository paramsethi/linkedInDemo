package com.ln.utility;

/**
 * LnDemo Exception
 * 
 * Application specific exception object
 * 
 * @author parampreetsethi
 * 
 */
public class LnDemoException extends Exception {
	private String status;
	private String message;

	public LnDemoException(String status) {
		this.status = status;
	}

	public LnDemoException(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String toString() {
		return "LnDemoException: " + status + " : message: " + message;
	}

}
