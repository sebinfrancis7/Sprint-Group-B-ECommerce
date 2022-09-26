package com.sprint.ecommerce.exception;

public class AlreadyExistsException extends Exception {
	private String message;

	public AlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public AlreadyExistsException() {
		super();
	}
}
