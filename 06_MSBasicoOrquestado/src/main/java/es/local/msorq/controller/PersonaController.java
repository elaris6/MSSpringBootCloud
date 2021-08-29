package es.local.msorq.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.local.msorq.model.Cliente;
import es.local.msorq.model.Dispositivo;
import es.local.msorq.model.Respuesta;

@RestController
public class PersonaController {

	@Autowired
	RestTemplate template;
	
	final String urlContactos = "http://localhost:8081/agenda";
	final String urlCliDisp = "http://localhost:8082/clidisp";
	
	@GetMapping(value = "/personas", produces = MediaType.APPLICATION_JSON_VALUE)
	public Respuesta getPersona(@RequestParam("dni")String dni) {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		Respuesta res = new Respuesta("OK",0,clientes);

		Cliente cli = template.getForObject(urlContactos + "/contacto?dni=" + dni, Cliente.class);
		if (cli != null) {
			cli.setDispositivos(Arrays.asList(template.getForObject(urlCliDisp + "/listarDni?dni=" + dni, Dispositivo[].class)));
			clientes.add(cli);
			res.setNumClientes(res.getNumClientes()+1);
		}
		return res;
	}
	
	@GetMapping(value = "/personas/{fechaNac1}/{fechaNac2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Respuesta buscaEdades(@PathVariable("fechaNac1") String fechaNac1,
			@PathVariable("fechaNac2") String fechaNac2) {

		/* Se convierten las fechas del path param a un objeto Date de Java
		 * Al desear usarlas dentro del una función lambda, Java no permite que las variables sean
		 * modificadas. Para salvar esto, se puede usar un array para contener estas variables.
		 * Explicación detallada:
		 * https://www.netjstech.com/2015/09/resolving-local-variable-defined-in-enclosing-scope-must-be-final-or-effectively-final-error.html*/
		Date[] fechas = {null, null};
		try {
			//El mes en el patrón deb ir con mayúsculpas para que lo tome adecuadamente como número
			//https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
			fechas[0] = new SimpleDateFormat("ddMMyyyy").parse(fechaNac1);
			System.out.println("Fecha inicial: " + fechas[0]);
			fechas[1] = new SimpleDateFormat("ddMMyyyy").parse(fechaNac2);
			System.out.println("Fecha final: " + fechas[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/* Se llama al GET de "/agenda/contactos" para recuperar la lista de contactos
		actualizados.
		Hay que convertir la List que deuvuelve la función "Arrays.asList" a una "LinkedList",
		porque dicha función devuelve una Lista inmutable. */
		List<Cliente> clientes = new LinkedList<Cliente>(Arrays.asList(template.getForObject(urlContactos + "/contactos", Cliente[].class)));
		
		if(clientes != null) {
			
			// Se hace un filtro de la lista de tipo Cliente con la condición deseada
			//clientes.removeIf(cli -> cli.getFechaNacimiento().compareTo(fechas[0]) < 0 || cli.getFechaNacimiento().compareTo(fechas[1]) > 0);
			Iterator<Cliente> iter = clientes.iterator();
			while(iter.hasNext()) {
				Cliente cli = iter.next();
				
				System.out.print(cli.getFechaNacimiento());
				System.out.print(" " + (cli.getFechaNacimiento().compareTo(fechas[0]) > 0));
				System.out.println(" " + (cli.getFechaNacimiento().compareTo(fechas[1]) < 0));
				if(cli.getFechaNacimiento().compareTo(fechas[0]) < 0 || cli.getFechaNacimiento().compareTo(fechas[1]) > 0) {
					iter.remove();
				} else {
					cli.setDispositivos(Arrays.asList(template.getForObject(urlCliDisp + "/listarDni?dni=" + cli.getDni(), Dispositivo[].class)));
				}
			}
		}

		return new Respuesta("OK",clientes.size(),clientes);
	}
}
