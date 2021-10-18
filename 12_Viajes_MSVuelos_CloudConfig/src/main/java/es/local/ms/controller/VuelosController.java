package es.local.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import es.local.ms.model.Vuelo;
import es.local.ms.service.VuelosService;

/* La anotación "@CrossOrigin" permitirá que el servicio sea llamado de forma
 * directa desde origenes externos.
 * En caso de estar usando una configuración con Spring Cloud Gateway, se deberá
 * retirar esta anotación, pues surgirían errores de cabeceras duplicadas, ya que
 * en el MS Spring Cloud Gateway ya se incluye la configuración para que no surja
 * el problema de CORS.
 */

//@CrossOrigin(origins="*")
@RestController
public class VuelosController {
	
	@Autowired
	VuelosService service;
	
	@GetMapping(value = "/vuelos/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> recuperarVuelos(@PathVariable("plazas") int plazas){
		
		return service.listarVuelosDisp(plazas);
	}
	
	@PutMapping(value = "/vuelos/{idVuelo}/{plazas}")
	public void reservarVuelo(@PathVariable("idVuelo")int idVuelo, @PathVariable("plazas")int plazas) {
		
		service.actualizarPlazas(idVuelo, plazas);
	}

	
}
