package com.local.contactos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.contactos.model.Contacto;
import com.local.contactos.service.ContactoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/* La anotación "CrossOrigin", permite controlar los orígenes permitidos sobre la
 * entrada del servicio. En este caso, para permitir llamadas desde una aplicación
 * front javascript, se aporta el valor "*". */
@CrossOrigin(origins = "*")
/* Anotación "Api" para indicar la información general del servicio- */
@Api(value = "Ejemplo de MS CRUD de contactos con JPA y MySQL")
@RestController
public class ContactosController {

	@Autowired
	ContactoService service;
	
	/* La anotación "ApiOperation" informa de la funcionalidad del método, así como del tipo de respuesta del mismo. */
	@ApiOperation(value = "Devuelve la lista completa de contactos almacenados sin ningún filtro.", response = List.class)
	@GetMapping(value = "/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos(){
		
		return service.recuperarContactos();
	}
	
	/* Dentro de los atributos del método se puede incluir la anotación "ApiParam" para indicar la descripción del parámetro, así como si es obligatorio o no. */
	@ApiOperation(value = "Devuelve un contacto buscando por id o dni, pasando éstos como query param.", response = Contacto.class)
	@GetMapping(value = "/contacto", produces = MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContacto(@ApiParam(value = "Identificador del contacto. Tipo Integer", required = false) @RequestParam(name = "id", required = false) Integer id, @ApiParam(value = "Dni del contacto. Tipo String", required = false) @RequestParam(name = "dni", required = false)String dni) {
		
		if (id != null) {
			return service.buscarContacto(id);
		} else if (dni != null) {
			return service.buscarContactoDni(dni);
		} else {
			return null;
		}

	}
	
	@ApiOperation(value = "Devuelve un contacto buscando por id, pasando éste como path param.", response = Contacto.class)
	@GetMapping(value = "/contactos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactoPorId(@ApiParam(value = "Identificador del contacto. Tipo Integer", required = true) @PathVariable("id") int id) {
	
		return service.buscarContacto(id);
	}
	 
	@ApiOperation(value = "Agrega un nuevo contacto que debe pasarse como parámetro en el body.", response = String.class)
	@PostMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String agregarContacto(@ApiParam(value = "Objeto JSON con todos los atributos del contacto excepto el id.", required = true)@RequestBody Contacto contacto) {
		
		return String.valueOf(service.agregarContacto(contacto));
	}
	
	@ApiOperation(value = "Modifica los atributos de un contacto. Los atributos así como el id de contacto, deben ser informados como parámetro en el body.")
	@PutMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@ApiParam(value = "Objeto JSON con todos los atributos del contacto.", required = true) @RequestBody Contacto contacto) {
		
		service.actualizarContacto(contacto);
	}
	
	@ApiOperation(value = "Elimina un contacto de la BBDD en base a su id, pasado como path param.", response = String.class)
	@DeleteMapping(value = "/contactos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarContactoPorId(@ApiParam(value = "Identificador del contacto. Tipo Integer", required = true) @PathVariable("id") int id) {
		
		return String.valueOf(service.eliminarContacto(id));
	}

}
