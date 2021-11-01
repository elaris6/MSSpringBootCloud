package com.local.contactos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* Clase de configuración ("@Configuration") de seguridad ("@EnableWebSecurity").
 * Se podría incluir en la clase de "Application", pero para no alterar la naturaleza
 * de ésta, es más recomendable crear una clase separada para este fin. */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/* Se sobre-escribe el método "authenticationManagerBean" para usarlo en el
	 * filtro que se añadirá en caso de autenticación con JWT, para sustituir
	 * al filtro de autenticación básica. */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

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
			.csrf().disable() // Se desactivan filtros internos para no requerir la autenticación cada vez que se accede a un mismo recurso
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/contactos").hasRole("ADMIN") // Proteger un recurso con un método concreto y requiriendo un rol
			.antMatchers("/contactos").authenticated() // Proteger todos los métodos sobre un recurso requiriendo autenticación
			//.antMatchers("/agenda/**").authenticated() // Se protege el acceso a cualquier recurso
			//.antMatchers("/agenda/contactos/**").authenticated() // Se protege un recurso al que se envían parámetros. Si no se pone, no quedaría protegido en este caso
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Para que no se guarde la sesión web y se autentique cada petición
			.and()
			/* Se añade el filtro para validación del token JWT, en lugar de la autenticación básica.
			 * Para ello se deberá crear una clase de apoyo, que gestionará este filtro. */
			.addFilter(new JWTAuthorizationFilter(authenticationManager()));
			/*.httpBasic();*/ // Autenticación básica (usuario + password). Otras opciones con certificados, OAuth..
	}
}
