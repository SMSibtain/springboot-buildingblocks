package com.smsrn.restservices.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -7522169697492771949L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
