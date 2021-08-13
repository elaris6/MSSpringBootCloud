package com.springboot.msbasico.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
}
