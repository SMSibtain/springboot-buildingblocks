package com.smsrn.restservices.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = -7522169697492771949L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
