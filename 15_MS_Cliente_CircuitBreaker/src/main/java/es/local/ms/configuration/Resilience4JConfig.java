package es.local.ms.configuration;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;

/* Clase de configuración del patrón Circuit Breaker sobre la implementación
 * de Resilience4J. */
@Configuration
public class Resilience4JConfig {
	
	/* Configuración del Circuit Brekaer en base a los métodos de la implementación
	 * de Resilience4J.
	 * Se indican los parámetros básicos, pero hay más parámetros de configuración:
	 * https://resilience4j.readme.io/docs/circuitbreaker
	 * */
	CircuitBreakerConfig config = CircuitBreakerConfig.custom()
			.slidingWindowType(SlidingWindowType.COUNT_BASED) // Tipo de cuenta, por llamadas o por segundos
			.slidingWindowSize(6) // Número de eventos (llamadas o segundos) sobre los que se computa
			.failureRateThreshold(50) // Porcentaje de errores para que se abra el circuito (por defecto 50%)
			.waitDurationInOpenState(Duration.ofMillis(10000)) // Tiempo a esperar para pasar a estado half-open y se vuelva a intentar la llamada normal 
			.build();
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration(){
		
		// Configuración global que Spring tomará para todos los Circuit Breaker
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(config)
				.build());
		
		// Ejemplo de configuración específica para un determinado Circuit Breaker
		/*return factory -> factory.configure(builder -> builder
				.circuitBreakerConfig(config)
				.build(), "circuit1");*/
		
	}

}
