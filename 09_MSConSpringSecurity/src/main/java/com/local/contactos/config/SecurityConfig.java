package com.local.contactos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* Clase de configuración ("@Configuration") de seguridad ("@EnableWebSecurity").
 * Se podría incluir en la clase de "Application", pero para no alterar la naturaleza
 * de ésta, es más recomendable crear una clase separada para este fin. */

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// Método para la definición de roles y usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// La opción "{noop}" se añade para no obligar a usar la encriptación
		auth
			.inMemoryAuthentication() //Definición en memoria en el propio servicio
			.withUser("user1")
				.password("{noop}user1") 
				.roles("USER")
				.and()
			.withUser("admin")
				.password("{noop}admin")
				.roles("USER", "ADMIN");
		
		// Opción con password encriptada también en memoria
		/*auth
		.inMemoryAuthentication()
		.withUser("user1")
			.password(new BCryptPasswordEncoder().encode("user1")) 
			.roles("USER")
			.and()
		.withUser("admin")
			.password(new BCryptPasswordEncoder().encode("admin"))
			.roles("USER", "ADMIN");*/
		
		/* Opción con usuarios y password en BBDD. La estructura de la BBDD podría
		 * ser cualquiera siempre que se indique correctamente la forma de obtener
		 * usuario, password y rol (authority) en los métodos. */
		/*auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password, enabled FROM users where username = ?")
			.authoritiesByUsernameQuery("SELECT username, authority FROM authorities where username = ?");*/	
	}

	// Definición de políticas de seguridad
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable() // Se desactivan filtros internos para no requerir la autenticación cada vez que se accede a un recurso
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/agenda/contactos").hasRole("ADMIN")
			.antMatchers("/agenda/contactos").authenticated()
			//.antMatchers("/agenda/**").authenticated() // Se protege el acceso a cualquier recurso
			//.antMatchers("/agenda/contactos/**").authenticated() // Se protege un recurso al que se envían parámetros. Si no se pone, no quedaría protegido en este caso
			.and()
			.httpBasic(); // Autenticación básica (usuario + password). Otras opciones con certificados, OAuth..
	}
	
	
}