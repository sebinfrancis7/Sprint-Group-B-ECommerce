package com.sprint.ecommerce.exception;

public class NotFoundException extends Exception {
	private String message;

	public NotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public NotFoundException() {
		super();
	}
}
