package com.springboot.msbasico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
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
	@Bean
	public RestTemplate template() {
		/* Para incluir la autenticación a la hora de llamar a un servicio se puede hacer incluyendo un
		 * elemento interceptor, que se encargará de incluir las cabeceras de autenticación en la request.
		 * Para ello se creará el interceptor con la funcionalidad deseada y luego tras crear la instancia
		 * de la clase "RestTemplate", se añadirá dicho interceptor que aporta la autenticación. */
		BasicAuthenticationInterceptor intercep;
		intercep = new BasicAuthenticationInterceptor("user1", "user1");
		RestTemplate template = new RestTemplate();
		template.getInterceptors().add(intercep);
		return template; 
}

}
