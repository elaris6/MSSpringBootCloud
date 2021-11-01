package es.local.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.local.ms.model.Candidato;
import es.local.ms.service.CandidatosService;

@RestController
public class CandidatosController {
	
	@Autowired
	CandidatosService service;
	
	@GetMapping(value = "/candidatos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Candidato> getCandidatos(@RequestParam("puesto") String p){
		
		return service.candidatos(p);
	}

}
