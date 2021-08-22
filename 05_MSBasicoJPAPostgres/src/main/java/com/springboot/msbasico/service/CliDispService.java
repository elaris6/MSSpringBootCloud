package com.springboot.msbasico.service;

import java.util.List;

import com.springboot.msbasico.model.CliDisp;

/* Capa de servicio en la que se implementaría la lógica de negocio necesaria.
 * Los nombres de los métodos no tienen porqué coincidir con los de la capa DAO
 * de acceso a datos. Ni siqueira tiene porqué haber los mismos métodos.  */
public interface CliDispService {

	List<CliDisp> listarTodosCliDisp();

	List<CliDisp> buscarCliDispPorHashDni(String hashDni);
	
	List<CliDisp> buscarCliDispPorDni(String dni);

	Integer altaCliDisp(CliDisp cliDisp);

	boolean cambiaCliDisp(CliDisp cliDisp);

	boolean borraCliDisp(int id);
}
