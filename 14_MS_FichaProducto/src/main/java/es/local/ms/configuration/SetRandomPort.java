package es.local.ms.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SocketUtils;


/* Clase de configuración custom, para asociar un puerto aleatorio de entre un
 * rango concreto y que sea visible para Eureka.
 * Si simplemente se indica "server.port=0" como propiedad, el servicio se
 * levante con un puerto aleatorio, pero se envía el valor "0" a Eureka en el
 * registro. */
@Configuration
public class SetRandomPort implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Value("${port.number.min:8081}")
	private Integer minPort;

	@Value("${port.number.max:8089}")
	private Integer maxPort;

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
		factory.setPort(port);
		System.getProperties().put("server.port", port);
	}
}
