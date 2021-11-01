package com.local.contactos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/* Ejemplo de clase de excepción personalizada en caso de desear o requerir no
 * usar una clase genérica de excepción de Java.
 * En este caso incluso, si se incluye la anotación "ResponseStatus", se puede
 * indicar el código de error de la respuesta http, para que vaya implícito. */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExcepcionPersonalizada extends Exception {

	/* Al heredar de "Exception" que a su vez hereda de "Serializable", se requiere
	 * la declaración del "serialVersionUID". */
	private static final long serialVersionUID = 1L;

	public ExcepcionPersonalizada(String message) {
		super(message);
	}
}
