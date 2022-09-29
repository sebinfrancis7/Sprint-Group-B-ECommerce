package com.sprint.ecommerce.exception;

public class MismatchException extends Exception {
	String msg;

	public MismatchException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public MismatchException() {
		super();
	}
}
