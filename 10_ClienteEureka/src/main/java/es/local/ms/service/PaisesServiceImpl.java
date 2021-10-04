package es.local.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.local.ms.model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {

	// Cuenta izzrra63@gmail.com:countrylayer.com
	String url = "http://api.countrylayer.com/v2/all?access_key=156798882b1813ba144708d52cac4e25";
	//Antigua url gratis
	//String url = "https://restcountries.eu/rest/v2/all";
	
	
	@Autowired
	RestTemplate template;
	
	/* Ejemplo de manejo personalizado de un gran JSON que hay que parsear o mapear
	 * según el interés de nuestra aplicación, sin necesidad de crear una gran
	 * clase de entidad que tenga la estructura exacta de la respuesta. */
	@Override
	public List<Pais> obtenerPaises() {
		
		/* Se almacena toda la respuesta a mapear en una variable de tipo String,
		 * que será luego lo que se gestionará con la clase "ObjectMapper".  */
		String resultado = template.getForObject(url, String.class);
		
		/* Se crea una instancia de "ObjectMapper", un "ArrayList" con el tipo
		 * de objeto base que querremos manejar, con la estructura que nos interese.
		 * También se crea un objeto de tipo "ArrayNode" que ayuda a gestioanr cada
		 * "nodo" del ArrayList mapeado por "ObjectMapper". */
		ObjectMapper mapper = new ObjectMapper();
		List<Pais> paises = new ArrayList<>();
		ArrayNode array;
		
		try {
			/* Se carga en el "ArrayNode" el mapeo que se realizar con el método
			 * "readTree()" de "ObjectMapper". */
			array = (ArrayNode)mapper.readTree(resultado);
			/* Se recurre dicho array, transofrmando cada nodo encontrato en un
			 * objeto "ObjectNode", que podremos leer para mapear lo que intesese,
			 * usando los métodos "get("nombre_prop")", junto con "asTipoDato()",
			 * para leer Text, Int, Double... */
			for (Object ob:array) {
				ObjectNode json = (ObjectNode)ob;
				Pais pais = new Pais();
				pais.setName(json.get("name").asText());
				pais.setApha2Code(json.get("alpha2Code").asText());
				pais.setApha3Code(json.get("alpha3Code").asText());
				pais.setCapital(json.get("capital").asText());
				
				/* Aquí se accede a una propiedad dentro de un nodo anidado en un sub array.
				 * https://www.baeldung.com/jackson-nested-values */
				List<String> langs = new ArrayList<>();
				// Se comprueba si viene el elemento "languages", que no aparece en la nueva api
				if(json.get("languages") != null) {
					for(JsonNode jn:json.get("languages")) {
						langs.add(jn.get("name").asText());
					}
					/* En lugar de recorrer el subarray se podría acceder deirectamente a uno
					 * de los elementos. */
					//pais.setLang(json.get("languages").get(0).get("name").asText());
				}
				
				pais.setLangs(langs);
	
				paises.add(pais);
			}
		} catch (JsonProcessingException e) {
			/* Se captura la excepción que podría provocar el intento de lectura
			 * del JSON de entrada en el método "readTree()".  */
			e.printStackTrace();
		}
		
		return paises;
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
