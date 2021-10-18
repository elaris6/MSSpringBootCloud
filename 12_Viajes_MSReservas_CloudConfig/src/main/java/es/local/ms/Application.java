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
	
	/* Se anota "RestTemplate" con "LoadBalanced" para activar la librería
	 * "Ribbon" para el acceso a Eureka con un balanceo de carga sencillo. */
	@LoadBalanced
	@Bean
	public RestTemplate template() {
		
		return new RestTemplate();
	}

}
