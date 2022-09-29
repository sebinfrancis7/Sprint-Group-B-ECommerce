package com.sprint.ecommerce.exception;

public class UniqueValueException extends Exception {
	String msg;

	public UniqueValueException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public UniqueValueException() {
		super();
	}
}
