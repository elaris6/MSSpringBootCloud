package com.springboot.msbasico.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		return "Respuesta SaludoController - SpringBoot MS";
	}
	
	/* VARIABLES DE ENTRADA */
	
	/* Ejemplo variables de entrada vía PathParam.
	 * http://localhost:8090/servicio/saludo/Israel
	 * Se deben indicar en el path de la anotación y luego mapearlas mediante la
	 * anotación "@PathVariable" en los argumentos del método. */
	@GetMapping(value = "saludo/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String saludo(@PathVariable("name") String nombre) {
		
		String salida = "Saludos " + nombre + "\nRespuesta SaludoController - SpringBoot MS";
		return salida;
	}
	
	/* Ejemplo variables de entrada vía QueryParam.
	 * http://localhost:8090/servicio/saludoNombre?name=Israel
	 * En este caso no es necesario indicar nada en el path de la anotación del
	 * tipo de request HTTP, y se mapeará mediante la anotación "@RequestParam"
	 * en los argumentos del método.
	 * El mismo método puede tener dos variantes con el mismo path y nombre de
	 * método, una sin variables y otra con variables en el path, pero no puede
	 * haber dos métodos con el mismo nombre y path cuya diferencia sea la existencia
	 * de QueryParams, pues Spring no sabe diferenciarlos. */
	@GetMapping(value = "saludoNombre", produces = MediaType.TEXT_PLAIN_VALUE)
	public String saludoNombre(@RequestParam("name") String nombre) {
		
		String salida = "Saludos " + nombre + "\nRespuesta SaludoController - SpringBoot MS";
		return salida;
	}
}
