package com.local.contactos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* Se anota la clase con "RestControllerAdvice", para que SpringBoot entienda
 * que esta clase será encargada de gestión de excepciones. */
@RestControllerAdvice
public class GestionExcepciones {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> gestionaError(Exception e){
		
		/* Se devuelve una respuesta con código http  BAD_REQUEST (400), informando
		 * en el cuerpo el mensaje personalizado de error, enviado desde el método
		 * que ha provocado la excepción. */
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
