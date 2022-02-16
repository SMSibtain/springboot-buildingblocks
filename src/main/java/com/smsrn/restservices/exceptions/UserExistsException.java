package com.smsrn.restservices.exceptions;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = 6160875735815955174L;

	public UserExistsException(String message) {
		super(message);
	}
}
