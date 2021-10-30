package com.local.contactos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/* Clase de configuración anotada con "Configuration" y "EnableSwagger2", para que
 * al levantarse, la aplicación busque las anotaciones de documentación en el
 * paquete "controller" que se le ha indicado, y genere la página de swagger.
 * 
 *  En la clase con el rest controller se deberán incluir todas las anotaciones
 *  necesarias para que la información se agregue. */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
						.basePackage("com.local.contactos.controller"))
				.paths(PathSelectors.regex("/.*"))
				.build();
	}
}
