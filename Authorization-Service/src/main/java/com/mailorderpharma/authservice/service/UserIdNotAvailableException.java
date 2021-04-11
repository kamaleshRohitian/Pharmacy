package com.mailorderpharma.authservice.service;

@SuppressWarnings("serial")
public class UserIdNotAvailableException extends RuntimeException{
	public UserIdNotAvailableException(String message) {
		super(message);
	}
}
