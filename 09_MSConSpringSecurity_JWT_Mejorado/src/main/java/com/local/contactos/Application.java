package com.local.contactos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/* En caso de que los paquetes del modelo de datos, controladores, servicios, dao... etc,
 * no estén jerárquicamente bajo el paquete donde se encuentra la clase de aplicación,
 * será necesario indicar mediante anotaciones, donde debe Spring buscar cada uno de los
 * componentes. */
//@ComponentScan(basePackages = {"com.local.contactos.controller", "com.local.contactos.dao", "com.local.contactos.service"})
//@EntityScan(basePackages = {"com.local.contactos.model"})
//@EnableJpaRepositories(basePackages = {"com.local.contactos.dao"})
//@ComponentScan(basePackages = {"com.local.contactos.util", "com.local.contactos.config"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
