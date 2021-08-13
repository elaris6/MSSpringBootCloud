package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.AgendaDao;
import model.Contacto;
@CrossOrigin(origins = "*")
@RestController
public class ContactosController {
	@Autowired
	AgendaDao agenda;
	@GetMapping(value="contactos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos() {
		return agenda.devolverContactos();
	}
	@GetMapping(value="contactos/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactos(@PathVariable("id") int id) {
		return agenda.recuperarContacto(id);
	}
	
	@PostMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void guardarContacto(@RequestBody Contacto contacto) {		
		agenda.agregarContacto(contacto);	
	}
	
	@PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@RequestBody Contacto contacto) {		
		agenda.actualizarContacto(contacto);
	}
	@DeleteMapping(value="eliminarPorEmail/{email}")
	public void eliminarContacto(@PathVariable("email") String email ) {	
		System.out.println(email);
		agenda.eliminarContacto(email);		
	}	
	@DeleteMapping(value="eliminarPorId/{id}")
	public void eliminarPorId(@PathVariable("id") int idContacto) {
		agenda.eliminarContacto(idContacto);
		
	}
}
