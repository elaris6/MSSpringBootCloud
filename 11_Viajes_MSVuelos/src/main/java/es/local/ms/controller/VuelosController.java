package es.local.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.local.ms.model.Vuelo;
import es.local.ms.service.VuelosService;

@CrossOrigin(origins="*")
@RestController
public class VuelosController {
	
	@Autowired
	VuelosService service;
	
	@GetMapping(value = "/vuelos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> recuperarVuelos(){
		
		return service.listarVuelosDisp();
	}

}
