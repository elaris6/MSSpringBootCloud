package com.springbootms.ejemplo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* Se anota el servicio con "RestController" para indicar a SpringBoot que esta 
 * clase será un controlador de servicio Rest. */
@RestController
public class SaludoController {

	/* Se anota el método con "GetMapping" para indicar que dicho método gestionará
	 * una petición de tipo GET al "RestController".
	 * El atributo "value" determina el path esperado para la petición.
	 * El atributo "produces" indica el tipo de retorno que se producirá. */
	@GetMapping(value = "saludo", produces = MediaType.TEXT_PLAIN_VALUE)
	public String saludo() {
		
		return "Microservicio con Springboot";
	}
}
