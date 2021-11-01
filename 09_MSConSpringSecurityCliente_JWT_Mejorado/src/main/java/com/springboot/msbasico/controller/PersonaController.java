package com.springboot.msbasico.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
	
	String user = "admin";
	String password = "admin";
	String token;
	
	/* Para incluir la cabecera necesaria con el token, no se pueden usar los
	 * métodos básicos de "RestTemplate", sino que se debe usar el método
	 * ".exchange()" que permite construir una llamda totalmente personalizada,
	 * incluyendo también cabeceras específicas. */
	HttpHeaders headers = new HttpHeaders();

	/* Se construye un método con la anotación "PostConstruct", para que sea 
	 * ejecutado justo tras la creación de la clase del controlador ("PersonaController). */
	@PostConstruct()
	public void autenticar() {
		
		/* Se llama al método "/login" del servicio en el que nos debemos autenticar
		 * para conseguir el token JWT que deberá ser usado en el resto de llamadas. */
		token = template.postForObject(url + "/login?user=" + user + "&password=" + password, null, String.class);
		// Se añade la cabecera a la lista de cabeceras
		headers.add("Authorization", "Bearer " + token);
		
	}
	
	@GetMapping(value = "/personas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> obtenerPersonas(){
		
		/* Se llama al GET de "/agenda/contactos" para recuperar la lista de contactos completa */
		//Persona[] personas = template.getForObject(url + "/agenda/contactos", Persona[].class);
		ResponseEntity<Persona[]> entidadRespuesta = template.exchange(url + "/agenda/contactos", HttpMethod.GET, new HttpEntity<>(headers), Persona[].class);
		Persona[] personas = entidadRespuesta.getBody();
		
		return Arrays.asList(personas);
		
	}

	@GetMapping(value = "/personas/{nombre}/{email}/{telefono}/{fechaNacimiento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre, @PathVariable("email") String email,
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

		// Se hace un POST con la instancia de Persona en el body y sin esperar
		// respuesta en el body
		//template.postForLocation(url + "/agenda/contactos", persona);
		template.exchange(url + "/agenda/contactos", HttpMethod.POST, new HttpEntity<Persona>(persona, headers), String.class);

		// Se llama al GET de "/agenda/contactos" para recuperar la lista de contactos
		// actualizados
		//Persona[] personas = template.getForObject(url + "/agenda/contactos", Persona[].class);
		ResponseEntity<Persona[]> entidadRespuesta = template.exchange(url + "/agenda/contactos", HttpMethod.GET, new HttpEntity<>(headers), Persona[].class);
		Persona[] personas = entidadRespuesta.getBody();

		// Se transforma el Array de objetos Persona a un objeto de tipo List
		return Arrays.asList(personas);
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
		//Persona[] personas = template.getForObject(url + "/agenda/contactos", Persona[].class);
		ResponseEntity<Persona[]> entidadRespuesta = template.exchange(url + "/agenda/contactos", HttpMethod.GET, new HttpEntity<>(headers), Persona[].class);
		Persona[] personas = entidadRespuesta.getBody();

		// Se hace un filtro del Array de tipo Persona con la condición deseada y se
		// devuelve como un objeto de tipo List
		return Arrays.stream(personas)
				.filter(p -> p.getFechaNacimiento().compareTo(fechas[0]) > 0 && p.getFechaNacimiento().compareTo(fechas[1]) < 0)
				.collect(Collectors.toList());
	}

}
