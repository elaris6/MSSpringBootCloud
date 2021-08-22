package com.springboot.msbasico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/* En caso de que los paquetes del modelo de datos, controladores, servicios, dao... etc,
 * no estén jerárquicamente bajo el paquete donde se encuentra la clase de aplicación,
 * será necesario indicar mediante anotaciones, donde debe Spring buscar cada uno de los
 * componentes. */
//@ComponentScan(basePackages = {"com.springboot.msbasico.controller", "com.springboot.msbasico.dao", "com.springboot.msbasico.service"})
//@EntityScan(basePackages = {"com.springboot.msbasico.model"})
//@EnableJpaRepositories(basePackages = {"com.springboot.msbasico.dao"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
