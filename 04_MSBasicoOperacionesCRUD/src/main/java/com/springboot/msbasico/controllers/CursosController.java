package com.springboot.msbasico.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.msbasico.model.Centro;
import com.springboot.msbasico.model.Curso;

@RestController
public class CursosController {
	
	/* La anotación "PostConstruct" hace que tras instanciarse la clase y su
	 * constructor, se ejecutará el método anotado con esta anotación. */
	private List<Curso> cursos;
	private List<Centro> centros;
	@PostConstruct
	public void init() {
		cursos = new ArrayList<>();
		cursos.add(new Curso("Java Core", 50,"M"));
		cursos.add(new Curso("Java2EE", 150,"T"));
		cursos.add(new Curso("Java Servlets JSP JSF", 40,"M"));
		cursos.add(new Curso("Java JPA", 20,"T"));
		cursos.add(new Curso("Microservicios con SpringBoot", 10, "O"));
		
		centros = new ArrayList<>();
		centros.add(new Centro("Los disCursos","VALENCIA","PZA España 27"));
		centros.add(new Centro("Curseando","BARCELONA","AV Independencia 22"));
		centros.add(new Centro("Hello Learning","MADRID","C/Muy Larga, 188"));
		centros.add(new Centro("Chorroaprende","VIGO","PO De La Lluvia, 44"));
		centros.add(new Centro("Cursos Molones", "MADRID", "C/Pruebas, 16"));
	}

	/* MAPEO DE OBJETOS JAVA */
	
	/* Para que el método devuelva un objeto Java en formato JSON simplemente
	 * basta con indicar en la anotación "Mapping" el tipo de "MeidaType" de salida
	 * "APPLICATION_JSON_VALUE". Spring, mediante la librería "Jackson" se encargará
	 * de hacer la conversión de forma automática del objeto, que debe ser un Java Bean. */
	@GetMapping(value = "curso", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso getCurso(){
		
		Curso curso = new Curso("Microservicios con SpringBoot", 10, "O");
		return curso;
	}
	
	/* Para que el método devuelva un objeto Java en formato XML, se deberá indicar
	 * en la anotación "Mapping" el tipo de "MeidaType" de salida "APPLICATION_XML_VALUE".
	 * En este caso Spring no podrá realizar la conversión si la clase de entidad no
	 * está anotada para permitir la conversión a XML y no se añade la dependencia
	 * necesaria  */
	@GetMapping(value = "centro", produces = MediaType.APPLICATION_XML_VALUE)
	public Centro getCentro() {
		
		Centro centro = new Centro("Cursos Molones", "MADRID", "C/Pruebas, 16");
		return centro;
	}
	
	@GetMapping(value = "centroJson", produces = MediaType.APPLICATION_JSON_VALUE)
	public Centro getCentroJson() {
		
		Centro centro = new Centro("Cursos Molones", "MADRID", "C/Pruebas, 16");
		return centro;
	}	
	
	
	/* Se añaden algunos métodos adicionales de test para devolver la lista completa
	 * de cursos o para devolver una lista filtrada por nombres coincidentes. */
	
	@GetMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> getCursos(){

		return cursos;
	}
	
	
	@GetMapping(value = "cursos/{nomCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> getCursos(@PathVariable("nomCurso") String nombre){
		
		List<Curso> cursosEncontrados = new ArrayList<>();
		
		nombre = nombre.toUpperCase();
		
		for(Curso c:cursos) {
			if(c.getNombre().toUpperCase().contains(nombre)) {
				cursosEncontrados.add(c);
			}
		}
		
		return cursosEncontrados;
	}
	
	
	@GetMapping(value = "centros", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Centro> getCentros(){

		return centros;
	}
	
	
	@GetMapping(value = "centrosNom", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Centro> getCentrosNom(@RequestParam("nomCentro") String nombre){
		
		List<Centro> centrosEncontrados = new ArrayList<>();
		
		nombre = nombre.toUpperCase();
		
		for(Centro c:centros) {
			if(c.getNombre().toUpperCase().contains(nombre)) {
				centrosEncontrados.add(c);
			}
		}
		
		return centrosEncontrados;
	}
	
	/* RESTO OPERACIONES CRUD - DELETE, POST, PUT */
	
	/* La operación "Delete" no tiene porque devolver resultado (aunque podría),
	 * y simplemente la implementamos como la eliminación de un objeto en base a
	 * un PathParam dado. */
	@DeleteMapping(value = "curso/{name}")
	public void deleteCurso(@PathVariable("name") String nombre) {
		
		/* En lugar de recorrer el ArrayList comparando y eliminando la coincidencia,
		 * usamos el método de colecciones "removeIf" que automáticamente recorre la
		 * coleección y elimina un objeto si se cumple una condición dada.
		 * En este caso la condición se implementa con una funcion lambda. */
		boolean encontrado = cursos.removeIf(c -> c.getNombre().equals(nombre));
		/* "removeIf()" devolverá un boolean que será true, si se ha encontrado alguna
		 * coincidencia y false en caso contrario. */
		if(!encontrado) {
			/* Copiado de internet para devolver un HTTP 404. */
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Curso no encontrado"
			);
		}
	}
	
	/* La operación "Post" debe admitir como argumento en el body un objeto de petición,
	 * que en este caso se mapea mediante la anotación "RequestBody".
	 * Además, puede devolver o no un resultado según convenga. */
	@PostMapping(value = "curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> postCurso(@RequestBody Curso curso){
		
		cursos.add(curso);
		return cursos;
	}
	
	/* La operación "Put" es similar en cuanto a estructura de método HTTP, aunque
	 * en lugar de añadir un curso, se busca en la lista de cursos para actualizar
	 * uno con el objeto pasado en el body de la petición, mapeado también con la
	 * anotación "RequestBody" como en el caso del método "Post". */
	@PutMapping(value = "curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> putCurso(@RequestBody Curso curso){
		
		boolean encontrado = false; 
		
		for(Curso c:cursos) {
			if(c.getNombre().equals(curso.getNombre())) {
				c.setDuracion(curso.getDuracion());
				c.setHorario(curso.getHorario());
				encontrado = true;
			}
		}
		if(encontrado) {
			return cursos;
		} else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Curso no encontrado"
			);
		}
		
	}
	
	
}
