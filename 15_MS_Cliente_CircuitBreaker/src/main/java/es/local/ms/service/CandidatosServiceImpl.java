package es.local.ms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.local.ms.model.Candidato;

@Service
public class CandidatosServiceImpl implements CandidatosService {

	@Autowired
	RestTemplate template;
	
	/* Se injecta la dependencia de "CircuitBreakerFactory" de Resilience4J, que
	 * generará una instancia con una configuración predeterminada, a modificar
	 * a posteriori según la necesidad. */
	@Autowired
	CircuitBreakerFactory factory;	
	
	private String url_ms_final = "http://localhost:8081/empleados";
	
	@Override
	public List<Candidato> candidatos(String puesto) {

		/* Sr crea una instancia de la clase "CircuitBreker", con un identificador
		 * de tipo String, que puede ser cualquier nombre que se quiera.
		 * 
		 * Al importar la librería, se debe importar la de Spring cloud estándar,
		 * para que la implementación no sea acoplada con la librería especídifica
		 * de Resilience4J. De este modo si en el futuro usamos otra implementación,
		 * esta parte sería válida y solo sería necesario modificar la configuración
		 * realizada, que sería seguramente diferente. */
		CircuitBreaker circuit = factory.create("circuit1");
		
		/* A través de la instancia de "CircuitBreaker" se llama al método "run",
		 * que espera como parámetros la llamada al servicio final, así como la
		 * respuesta en caso de fallo.
		 * Los parámetros deberán ser proporcionados en forma de funciones lambda.
		 * 
		 * circuit.run(Supplier<T> supplier, Function<Throwable> fallback) */

		return circuit.run(() -> {
					List<Candidato> candidatos = Arrays.asList(template.getForObject(url_ms_final,Candidato[].class));
					return candidatos.stream()
						.filter(c -> c.getPuesto().toLowerCase().equals(puesto.toLowerCase()))
						.collect(Collectors.toList());
					},
					t -> new ArrayList<Candidato>());
		
	}

}
