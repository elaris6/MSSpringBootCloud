package es.local.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

/* Esta clase de configuración es necesaria para que en el MS Spring Cloud Gateway
 * no se bloqueen por CORS las peticiones que no sean de tipo GET.
 * Al incluir esta clase de configuración se debería así mismo retirar la anotación
 * para permitir el CORS en los MS finales, pues en caso contrario surgiría un problema
 * de cuplicidad de cabeceras.*/
@Configuration
public class FiltroCors {

	private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Content-Length, Authorization, credential, X-XSRF-TOKEN";
	private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
	private static final String ALLOWED_ORIGIN = "*";
	//private static final String MAX_AGE = "7200"; //2 hours (2 * 60 * 60) 

	@Bean
	public WebFilter corsFilter() {
		return (ServerWebExchange ctx, WebFilterChain chain) -> {
			ServerHttpRequest request = ctx.getRequest();
	    	if (CorsUtils.isCorsRequest(request)) {
	    		ServerHttpResponse response = ctx.getResponse();
	    		HttpHeaders headers = response.getHeaders();
	    		headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
	    		headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);	       
	    		headers.add("Access-Control-Allow-Headers",ALLOWED_HEADERS);
	    	}
	    	return chain.filter(ctx);
	    };
	}
}
