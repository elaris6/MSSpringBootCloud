package es.local.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/* Se crea una instancia de la clase "RestTemplate" que facilitará las llamadas
	 * a otros servicios.
	 * Como en Spring Boot no se trabaja con un fichero de configuración XML donde
	 * decirle al framework que objetos debe crear al iniciar la aplicación. Esto
	 * se hace mediante a través de las anotaciones "Bean" vía código, que se colocan
	 * en clases anotadas con "Configuration" ("SpringBootApplication" incluye 
	 * "Configuration"). */
	
	/* Para crear un servicio cliente final de Eureka es necesario (además de la dependencia
	 * de "eureka-client"), incluir la anotación "@LoadBalanced" en la instancia de
	 * "RestTemplate", para que se cargue la lirería "Ribbon", que será la encargada
	 * de la gestión automática. */
	@Bean
	@LoadBalanced
	public RestTemplate template() {
		
		return new RestTemplate();
	}

}
