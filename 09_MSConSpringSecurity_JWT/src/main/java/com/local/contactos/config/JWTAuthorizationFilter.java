package com.local.contactos.config;

import static com.local.contactos.config.Constantes.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/* Clase de apoyo que hereda de la "BasicAuthenticationFilter", que es la que
 * proporciona la autenticación básica de Spring. */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/* Se recupera la cabecera en la que llegará el token en la request. */
		String header = request.getHeader(ENCABEZADO);
		
		/* Si no existe la cabecera o no está bien formada (no está el prefijo,
		 * se continúa sin validar el token. Se pasa al siguiente filtro de la
		 * cadena si hubiese más. */
		if (header == null || !header.startsWith(PREFIJO_TOKEN)) {
			
			chain.doFilter(request, response);
			return;
		}
		
		// Se obtienen los datos del usuario del token
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		
		/* La información del usuario/rol se almacena en el contexto de seguridad
		 * para que pueda ser utilizada por Spring dutante el proceos de
		 * autorización. */
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		/* Se pasa al siguiente filtro de la cadena si hubiese más. */
		chain.doFilter(request, response);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		
		/* Se recupera la cabecera en la que llegará el token en la request. */
		String token = request.getHeader(ENCABEZADO);
		
		if(token != null) {
			/* Si el token existe, se procesa, extrayendo toda la información. */
			Claims claims = Jwts.parser()
					.setSigningKey(CLAVE)
					.parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
					.getBody();
			
			/* Se mapea el usuario. */
			String user = claims.getSubject();
			
			/* Se mapea la lista de roles. */
			List<String> authorities = (List<String>) claims.get("authorities");
			
			if(user != null) {			
				/* Se devuelve el objeto con la información extraída del token. */
				return new UsernamePasswordAuthenticationToken(user,null,authorities.stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList()));
			}
			return null;
		}
		return null;
	}
	
	
}
