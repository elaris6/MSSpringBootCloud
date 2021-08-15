package com.springboot.msbasico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/* Si se requiere incluir componentes que no están anidados en el paquete de la
 * clase principal de aplicación se de usar la anotación "ComponentScan", para
 * informar explícitamente los paquetes a escanear en busca de componentes. */
@SpringBootApplication
//@ComponentScan(basePackages = {"nombrePaquete"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
