package com.local.contactos.config;

import static com.local.contactos.config.Constantes.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.local.contactos.exceptions.custom.CustomExpiredJwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/* Clase de apoyo que hereda de la "BasicAuthenticationFilter", que es la que
 * proporciona la autenticación básica de Spring. */
@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	/*
	 * En lugar de tener la clave de hash en el código, se incorpora y se recupera
	 * de las propiedades. recibiéndola como argumento en el constructor.
	 */
	// @Value("${claveHash}") // Esto aquí no funciona :(
	// String claveHash;
	private String claveHash;

	public JWTAuthorizationFilter(AuthenticationManager authManager, @Value("${claveHash}") String claveHash) {

		super(authManager);
		this.claveHash = claveHash;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

		/* Se recupera la cabecera en la que llegará el token en la request. */
		String header = request.getHeader(ENCABEZADO);

		try {
		/*
		 * Si no existe la cabecera o no está bien formada (no está el prefijo, se
		 * continúa sin validar el token. Se pasa al siguiente filtro de la cadena si
		 * hubiese más.
		 */
		if (header == null || !header.startsWith(PREFIJO_TOKEN)) {

			chain.doFilter(request, response);
			return;
		}

		// Se obtienen los datos del usuario del token
		UsernamePasswordAuthenticationToken authentication;
		authentication = getAuthentication(request, response);

		/*
		 * La información del usuario/rol se almacena en el contexto de seguridad para
		 * que pueda ser utilizada por Spring dutante el proceos de autorización.
		 */
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		/* Se pasa al siguiente filtro de la cadena si hubiese más. */
		chain.doFilter(request, response);
		
		} catch (Exception e) {
			//throw new CustomExpiredJwtException(e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		/* Se recupera la cabecera en la que llegará el token en la request. */
		String token = request.getHeader(ENCABEZADO);

		if (token != null) {

			try {
				/* Si el token existe, se procesa, extrayendo toda la información. */

				Claims claims = Jwts.parser().setSigningKey(claveHash).parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
						.getBody();

				/* Se mapea el usuario. */
				String user = claims.getSubject();

				/* Se mapea la lista de roles. */
				List<String> authorities = (List<String>) claims.get("authorities");

				if (user != null) {
					/* Se devuelve el objeto con la información extraída del token. */
					return new UsernamePasswordAuthenticationToken(user, null,
							authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				}
			} catch (ExpiredJwtException e) {
				System.out.println(e.getMessage());
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			}

			return null;
		}
		return null;
	}

}
