package com.local.contactos.exceptions.custom;

public class CustomExpiredJwtException extends RuntimeException{
	public CustomExpiredJwtException() {
        super();
    }

    public CustomExpiredJwtException(String message) {
        super(message);
    }
}
