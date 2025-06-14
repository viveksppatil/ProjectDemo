package com.ExceptionHandler;

public class RoleNotFound extends RuntimeException{

	public RoleNotFound(String msg) {
		
		super(msg);
	}
}
