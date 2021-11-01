package es.local.ms.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.local.ms.model.FichaProducto;

@RestController
public class ProductosController {
	
	/* Se inyecta el valor de la instancia de las propiedades, para poder identificar
	 * que instancia se está ejecutando en la consola.  */
	@Value("${eureka.instance.instance-id}")
	String id;
	
	
	@GetMapping(value = "/producto/{producto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FichaProducto obtenerProducto(@PathVariable("producto") String nombre) {
		
		//Se imprime el valor de la propiedad "instance-id"
		System.out.println("-- Instancia: " + id);
		return new FichaProducto(nombre,(int)(Math.random()*10000+1));
		
	}

}
