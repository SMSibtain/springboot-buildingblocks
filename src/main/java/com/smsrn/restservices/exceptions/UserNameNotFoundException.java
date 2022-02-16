package com.smsrn.restservices.exceptions;

public class UserNameNotFoundException extends Exception {

	private static final long serialVersionUID = -7090556812558609464L;

	public UserNameNotFoundException(String message) {
		super(message);
	}
}
