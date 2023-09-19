package com.example.transactions.Advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<String> handleEmptyInput(RecordNotFoundException recordNotFoundException){
		return new ResponseEntity<String>("No value found on DB", HttpStatus.NOT_FOUND );
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> NoSuchElementException(NoSuchElementException noSuchElementException){
		return new ResponseEntity<String>("No value found on DB", HttpStatus.NOT_FOUND );
	}
	
	@ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>("Anauthorized transaction.", HttpStatus.UNAUTHORIZED);
    }


}
