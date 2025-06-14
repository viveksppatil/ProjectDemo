package com.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.DTO.ExceptionResponseHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ExceptionResponseHandler exceptionResponse(RuntimeException e) {

		return new ExceptionResponseHandler().builder().msg(e.getMessage()).build();

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validateUserData(MethodArgumentNotValidException ex) {

		return new ResponseEntity(ex.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<?> userAlreadyExistsResponse(UserAlreadyExists e) {

		return new ResponseEntity(exceptionResponse(e), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RoleNotFound.class)
	public ResponseEntity<?> roleNotFoundResponse(RoleNotFound e) {

		return new ResponseEntity(exceptionResponse(e), HttpStatus.NOT_FOUND);
	}

}
