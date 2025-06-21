package com.icareer.exception;

public class NoSuchCustomerException extends Exception {
	private static final long serialVersionUID = -878709961754331051L;

	public NoSuchCustomerException() {
		super();
	}

	public NoSuchCustomerException(String errors) {
		super(errors);
	}
}