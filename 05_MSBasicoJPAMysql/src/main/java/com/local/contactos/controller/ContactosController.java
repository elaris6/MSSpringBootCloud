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

/* La anotación "CrossOrigin", permite controlar los orígenes permitidos sobre la
 * entrada del servicio. En este caso, para permitir llamadas desde una aplicación
 * front javascript, se aporta el valor "*". */
@CrossOrigin(origins = "*")
@RestController
public class ContactosController {

	@Autowired
	ContactoService service;
	
	@GetMapping(value = "/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos(){
		
		return service.recuperarContactos();
	}
	
	
	@GetMapping(value = "/contacto", produces = MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContacto(@RequestParam("id") int id) {
		
		return service.buscarContacto(id);
	}
	
	
	@GetMapping(value = "/contactos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactoPorId(@PathVariable("id") int id) {
	
		return service.buscarContacto(id);
	}
	 
	
	@PostMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String agregarContacto(@RequestBody Contacto contacto) {
		
		return String.valueOf(service.agregarContacto(contacto));
	}
	
	@PutMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@RequestBody Contacto contacto) {
		
		service.actualizarContacto(contacto);
	}
	
	@DeleteMapping(value = "/contactos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarContactoPorId(@PathVariable("id") int id) {
		
		return String.valueOf(service.eliminarContacto(id));
	}

	
}
