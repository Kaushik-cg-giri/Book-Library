package com.book_management.exception;

public class ErrorResponse {
	
	private String message;
	
	private boolean Status;
	
	

	public ErrorResponse(String message, boolean status) {
		super();
		this.message = message;
		this.Status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
	
	

}
