package es.local.ms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.local.ms.model.FichaProducto;

@RestController
public class ClienteController {

	/* Se informa en una variable la url del microservicio, pero como está
	 * configurado con Eureka, solo es necesario saber el nombre del ms, no su
	 * dirección real. */
	private String url_ms = "http://ms-ficha-producto/producto/";
	
	@Autowired
	private RestTemplate template;
	
	@GetMapping(value = "/fichas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FichaProducto> fichas(){
		
		List<FichaProducto> fichas = new ArrayList<FichaProducto>();
		
		for(int i = 0; i<5; i++) {
			FichaProducto ficha = new FichaProducto();
			ficha = template.getForObject(url_ms + "producto_" + i, FichaProducto.class);
			fichas.add(ficha);
		}
		
		return fichas;
	}
	
	
}
