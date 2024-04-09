package com.tech.pgfinder.config;

import java.io.Serializable;


public class ApplicationResponse<responseData> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statuscode;

	private String message;

	private responseData result;

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public responseData getResult() {
		return result;
	}

	public void setResult(responseData result) {
		this.result = result;
	}

	
	
}
