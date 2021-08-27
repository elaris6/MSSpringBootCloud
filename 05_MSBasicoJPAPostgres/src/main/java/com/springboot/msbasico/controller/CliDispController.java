package com.springboot.msbasico.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.msbasico.model.CliDisp;
import com.springboot.msbasico.service.CliDispService;

@RestController
public class CliDispController {

	@Autowired
	CliDispService service;


	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CliDisp> listarCliDisp(){

		return service.listarTodosCliDisp();
	}
	
	@GetMapping(value = "/listar/{hashDni}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CliDisp> listarCliDisp(@PathVariable("hashDni") String hash){

		return service.buscarCliDispPorHashDni(hash);
	}
	
	@GetMapping(value = "/listarDni", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CliDisp> listarCliDispDni(@RequestParam("dni") String dni){

		return service.buscarCliDispPorDni(dni);
	}
	
	@PostMapping(value = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String insertarCliDisp(@RequestBody CliDisp cliDisp) {
		
		return String.valueOf(service.altaCliDisp(cliDisp));
	}
	
	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String actualizarCliDisp(@RequestBody CliDisp cliDisp) {
		
		return String.valueOf(service.cambiaCliDisp(cliDisp));
	}
	
	@DeleteMapping(value = "/eliminar/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String eliminarCliDisp(@PathVariable("id") int id) {
		
		return String.valueOf(service.borraCliDisp(id));
	}
}
