package com.cognizant.settlement.exception;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.settlement.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@Autowired
	private HttpServletRequest request;
	
	private String getTraceId() {
		String trace = request.getHeader("X-Correlation-Id");
		return (trace != null && !trace.isEmpty()) ? trace : UUID.randomUUID().toString();
	}
	
	private ErrorResponse build(int status , String error, String message) {
		return ErrorResponse.builder()
				.traceId(getTraceId())
				.status(status)
				.error(error)
				.message(message)
				.path(request.getRequestURI())
				.build();
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){
		log.info("Not Found: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(build(404,"Not Found", ex.getMessage()));
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex){
		log.info("Bad Request : {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(build(400,"Bad Request", ex.getMessage()));
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex){
		log.info("Conflict : {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(build(409,"Conflict", ex.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){
		List<String> errors = ex.getBindingResult().getFieldErrors()
				         .stream().map(e -> e.getField()+": "+e.getDefaultMessage())
				         .collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(build(400,"Validation Failed ", String.join("; ", errors)));
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraint(ConstraintViolationException ex){
	    String msg = ex.getConstraintViolations().stream()
	        .map(v -> v.getPropertyPath() + ": " + v.getMessage())
	        .collect(Collectors.joining("; "));
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	        .body(build(400, "Constraint Violation", msg));
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex){
		log.error("Unhandled Exception: {}",ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(build(500,"Internal Server Error ", ex.getMessage()));
	}
	
}
