package com.ExceptionHandler;

public class UserAlreadyExists extends RuntimeException {

	public UserAlreadyExists(String msg) {

		super(msg);
	}
}
