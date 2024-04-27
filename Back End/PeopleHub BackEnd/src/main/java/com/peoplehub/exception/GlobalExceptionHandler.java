package com.peoplehub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	ResponseEntity securityUserNotFoundExceptionHandler(UsernameNotFoundException u) {
		return new ResponseEntity<>(u.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCredentailsException.class)
	ResponseEntity InvalidCredentailsExceptionHandler() {
		return new ResponseEntity<>("Invalid Credentials",HttpStatus.NOT_FOUND);
	}
	
	

	@ExceptionHandler(InvalidOtpException.class)
	ResponseEntity InvalidOtpExceptionHandler() {
		return new ResponseEntity<>("Invalid OTP",HttpStatus.NOT_FOUND);
	}
}
