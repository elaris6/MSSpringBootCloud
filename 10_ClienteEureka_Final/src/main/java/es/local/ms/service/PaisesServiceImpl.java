package es.local.ms.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.local.ms.model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {

	/* Al ser un cliente de Eureka, no se necesita conocer la url:puerto concretos
	 * del servicio final, sino que será Eureka el que los proporcione.
	 * Para ello, se incluye simplemente el identificador del servicio registrado
	 * en Eureka server. */
	
	/* Este servicio cliente podría ser a su vez también registrado en Eureka. */
	String url = "http://servicio-paises";
	
	@Autowired
	RestTemplate template;
	
	/* Ejemplo de manejo personalizado de un gran JSON que hay que parsear o mapear
	 * según el interés de nuestra aplicación, sin necesidad de crear una gran
	 * clase de entidad que tenga la estructura exacta de la respuesta. */
	@Override
	public List<Pais> obtenerPaises() {

		Pais[] paises = template.getForObject(url+"/paises", Pais[].class);
		
		return Arrays.asList(paises);
	}

	@Override
	public List<Pais> buscarPaises(String name) {

		/* Este método recupera el listado completo de paises y aplica un filtro
		 * sobre el array para conservar solo los que contengan la cadena pasada
		 * como parámetro. */
		return obtenerPaises()
				.stream()
				.filter(p->p.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

}
