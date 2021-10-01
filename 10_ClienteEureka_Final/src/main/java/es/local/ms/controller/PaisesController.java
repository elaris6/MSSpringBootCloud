package es.local.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.local.ms.model.Pais;
import es.local.ms.service.PaisesService;

@RestController
public class PaisesController {

	
	@Autowired
	PaisesService paises;
	
	
	@GetMapping(value = "/paises", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> getPaises(){
		
		return paises.obtenerPaises();
	}
	
	
	@GetMapping(value = "/paises/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> getPaisesNombre(@PathVariable("name")String name){
		
		return paises.buscarPaises(name);
	}
}
