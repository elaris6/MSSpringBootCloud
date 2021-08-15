package com.springboot.msbasico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


/* La clase de test se genera de forma automática en Spring como norma general.
 * Aunque está casi vacía, ya se incluye una anotación "SpringBootTest" sobre la
 * clase, así como un método vacío con la anotación "Test".
 * 
 * No será necesario tener levantado el microservicio ya que la clase de prueba
 * se encargará de hacerlo de forma automática al ejecutarla (Run JUnit Test).
 * 
 * Para que los métodos de la clase de pruebas se ejecuten en un orden concreto
 * (no tienen porque ejecutarse en el orden en que aparecen en la clase), se debe
 * anotar la clase de pruebas con "@TestMethodOrder(OrderAnnotation.class)", y luego
 * cada uno de los métodos con "@Order(n)", y se ejecutarán los métodos desde "n"
 * menor a mayor. */

@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class ApplicationTests {
	
	/* Para hacer las pruebas vamos a utilizar un objeto de tipo "MockMvc", injectado
	 * directamente del framework.
	 * Este objeto expone algunos métodos para ejecutar peticiones HTTP y comprobar
	 * los resultados directamente en la consola.
	 * Para poder usarlo, se indica a Spring que lo autoconfigure anotando la clase
	 * con "AutoConfigureMockMvc".  */
	@Autowired
	MockMvc mock;

	@Test
	void contextLoads() {
	}
	
	/* En lugar de usar el método de prueba que propone la clase, se puede crear uno
	 * propio, siempre que se le anote con "Test". */
	@Test
	@Order(2)
	void getCursos() throws Exception {
		
		/* Mediante la instancia "mock" y el método "perform()", se pueden realizar
		 * peticiones con métodos static que, tras ser importados permiten ejecutar
		 * la petición contra un recurso determinado ("get()"), e imprimir el resultado
		 * en la consola ("print()"). */
		mock.perform(get("/cursos")).andDo(print());
	}
	
	@Test
	@Order(1)
	void deleteCurso() throws Exception {
		
		/* El caracter espacio se convertirá automáticamente en tiempo de ejecución a
		 * "%20", para que la url pueda ser procesada. */
		mock.perform(delete("/curso/Java Core")).andDo(print());
	}
	
	
	@Test
	@Order(0)
	void postCurso() throws Exception {
		
		/* Para pasar un objeto en el body de la petición será necesario el uso de
		 * los métodos "contentType()" y "content()" para indicar el tipo y el contenido.
		 * Si el contenido es de tipo JSON, se deberán usar las barras de escape de
		 * caracteres para entrecomillar cada elemento necesario. */
		
		mock.perform(post("/curso")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"nombre\":\"Python\",\"duracion\":40,\"horario\":\"T\"}")
			).andDo(print());
	}
	
	@Test
	@Order(3)
	void putCurso() throws Exception {
		
		/* Prueba realizada para obtener una respuesta HTTP 404. */
		mock.perform(put("/curso")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"nombre\":\"Pyython\",\"duracion\":45,\"horario\":\"O\"}")
			).andDo(print());
	}

}
