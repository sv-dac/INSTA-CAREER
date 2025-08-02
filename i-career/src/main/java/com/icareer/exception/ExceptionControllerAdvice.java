package com.icareer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	// HttpStatus.BAD_REQUEST (400), HttpStatus.NOT_FOUND (404),
	// or HttpStatus.INTERNAL_SERVER_ERROR (500) are more appropriate

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("An unexpected server error occurred: " + ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public String runtimeExceptionHandler(RuntimeException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(Throwable.class)
	public String throwableHandler(Throwable ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(InstaCareerException.class)
	public ResponseEntity<ErrorMessage> instaCareerExceptionHandler(InstaCareerException ex) {
		ErrorMessage error = ex.getErrorMessage();

		if (error == null) {
			error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"An unexpected internal error occurred.");
		}
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}