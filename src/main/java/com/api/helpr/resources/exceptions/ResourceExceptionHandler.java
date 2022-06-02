package com.api.helpr.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.helpr.services.exceptions.DataIntegrityViolationException;
import com.api.helpr.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex,
			HttpServletRequest request){
		StandardError error = new StandardError(
				System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), 
				"Object Not Found", 
				ex.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request){
		StandardError error = new StandardError(
				System.currentTimeMillis(), 
				HttpStatus.CONFLICT.value(), 
				"Conflict Data Value", 
				ex.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException ex,
			HttpServletRequest request){
		
		ValidationError errors = new ValidationError(
				System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Erro na validação dos campos", 
				"Campos obrigatórios:", 
				request.getRequestURI());
		
		for(FieldError x :ex.getBindingResult().getFieldErrors()) {
			errors.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
}
