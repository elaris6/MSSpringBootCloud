package com.local.contactos.exceptions;

import com.local.contactos.exceptions.custom.*;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
class CustomControllerAdvice {
	

    @ExceptionHandler(CustomDataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomDataNotFoundExceptions(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        return new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ErrorResponse> handleCustomErrorExceptions(Exception e) {
        // casting the generic Exception e to CustomErrorException
        CustomErrorException customErrorException = (CustomErrorException) e;

        HttpStatus status = customErrorException.getStatus();

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        customErrorException.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(new ErrorResponse(status,customErrorException.getMessage(),stackTrace,customErrorException.getData()),status);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsExceptions(Exception e) {
    	
    	HttpStatus status = HttpStatus.FORBIDDEN; // 403
    	
    	return new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
    	
    }
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtExcepcion(Exception e) {
    	
    	HttpStatus status = HttpStatus.UNAUTHORIZED; // 401
    	System.out.println("Custom exception");
    	return new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
    	
    }
    
    
    @ExceptionHandler(CustomExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtExceptions(Exception e) {
    	
    	HttpStatus status = HttpStatus.UNAUTHORIZED; // 401
    	
    	return new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
    	
    }

    // fallback method
    @ExceptionHandler(Exception.class) // exception handled
    public ResponseEntity handleExceptions(Exception e) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(new ErrorResponse(status,e.getMessage(),
                        stackTrace // specifying the stack trace in case of 500
                ),
                status
        );
    }
}
