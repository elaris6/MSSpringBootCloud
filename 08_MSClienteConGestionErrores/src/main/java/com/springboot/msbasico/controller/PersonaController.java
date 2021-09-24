package com.springboot.msbasico.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.springboot.msbasico.model.Persona;

@RestController
public class PersonaController {

	/*
	 * En el controlador se inyecta una instancia de "RestTemplate", que se ha
	 * inicializado en el arranque de la aplicación en la clase principal de la
	 * aplicación.
	 */
	@Autowired
	RestTemplate template;

	String url = "http://localhost:8081";

	/* Para poder gestionar excepciones desde el cliente del microservicio, en lugar
	 * de recibir directamente la entidad a tratar, se recibe un objet "ResponseEntity"
	 * que permitirá recuperar tanto el body de la respuesta, como las cabeceras y
	 * códigos http. */
	@GetMapping(value = "/personas/{nombre}/{email}/{telefono}/{fechaNacimiento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Persona>> altaPersona(@PathVariable("nombre") String nombre, @PathVariable("email") String email,
			@PathVariable("telefono") String telefono, @PathVariable("fechaNacimiento") String fechaNacimiento) {

		// Se convierte la fecha del path param a un objeto Date de Java
		Date fecha = null;
		try {
			fecha = new SimpleDateFormat("ddMMyyyy").parse(fechaNacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Se crea una instancia de Persona (la entidad del MS local) con los parámetros
		Persona persona = new Persona(nombre, email, telefono, fecha);

		/* La funcionalidad de este orquestado es dar de alta una persona nueva y recibir
		 * el listado actualizado ya con dicha nueva entidad. */
		try {
			/* Se hace un POST con la instancia de Persona en el body y sin esperar
			 respuesta en el body.
			 Si la respuesta devuelve un código diferente a 200, se producirá una excepción. */
			template.postForLocation(url + "/agenda/contactos", persona);
			
			/* Si la llamada previa fue bien, se llama al GET de "/agenda/contactos" para 
			 * recuperar la lista de contactos actualizados. De nuevo, si la respuesta tuviese
			 * un código distinto de 200, se generaría una excepción. */
			Persona[] personas = template.getForObject(url + "/agenda/contactos", Persona[].class);

			// Se transforma el Array de objetos Persona a un objeto de tipo List
			return new ResponseEntity<List<Persona>>(Arrays.asList(personas),HttpStatus.OK);
		} catch (HttpStatusCodeException e) {
			
			/* Si se ha producir error en alguna de las llamadas del bloque "try", se envía
			 * al cliente del orquestado una cabeceracon un mensaje de error, una lista
			 * vacía de tipo "Persona" (obligado por la declaración del método) y el código
			 * de estado enviado desde el microservicio servidor de este orquestado. */
			
			// Cuidado al importar la clase del paquete de springboot, en lugar del de java.net
			HttpHeaders headers = new HttpHeaders();
			/* Se añade una cabecera con una descripción "Error" y se informa como valor
			 * lo que se recupera del body de la instancia de la excepción. */
			headers.add("Error", e.getResponseBodyAsString());
			/* Finalmente se genera la respuesta con una lista de "Persona" vacía,
			 * las cabeceras y el código de estado recuperado de la instancia de la excepción. */
			return new ResponseEntity<List<Persona>>(new ArrayList<Persona>(),headers,e.getStatusCode());
			
		}
		

		
	}

	@GetMapping(value = "/personas/{fechaNac1}/{fechaNac2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscaEdades(@PathVariable("fechaNac1") String fechaNac1,
			@PathVariable("fechaNac2") String fechaNac2) {

		/* Se convierten las fechas del path param a un objeto Date de Java
		 * Al desear usarlas dentro del una función lambda, Java no permite que las variables sean
		 * modificadas. Para salvar esto, se puede usar un array para contener estas variables.
		 * Explicación detallada:
		 * https://www.netjstech.com/2015/09/resolving-local-variable-defined-in-enclosing-scope-must-be-final-or-effectively-final-error.html*/
		Date[] fechas = {null, null};
		try {
			fechas[0] = new SimpleDateFormat("ddMMyyyy").parse(fechaNac1);
			fechas[1] = new SimpleDateFormat("ddMMyyyy").parse(fechaNac2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Se llama al GET de "/agenda/contactos" para recuperar la lista de contactos
		// actualizados
		Persona[] personas = template.getForObject(url + "/agenda/contactos", Persona[].class);

		// Se hace un filtro del Array de tipo Persona con la condición deseada y se
		// devuelve como un objeto de tipo List
		return Arrays.stream(personas)
				.filter(p -> p.getFechaNacimiento().compareTo(fechas[0]) > 0 && p.getFechaNacimiento().compareTo(fechas[1]) < 0)
				.collect(Collectors.toList());
	}

}
