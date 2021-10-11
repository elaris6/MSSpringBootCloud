package es.local.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.local.ms.model.Hotel;
import es.local.ms.service.HotelService;

// La anotación "@CrossOrigin" permitirá que el servicio sea llamado
@CrossOrigin(origins="*")
@RestController
public class HotelController {
	
	@Autowired
	HotelService service;
	
	@GetMapping(value = "/hoteles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> listarHoteles(){
		
		return service.listarHotelesDisp();
	}

}
