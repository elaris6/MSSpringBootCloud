package com.springboot.msbasico.dao;

import java.util.List;

import com.springboot.msbasico.model.CliDisp;

/* Interfaz para operaciones de acceso a datos. */
public interface CliDispDao {

	List<CliDisp> listarClientesDispositivos();

	List<CliDisp> buscarCliDispHashDni(String hashDni);

	CliDisp buscarCliDisp(int id);

	void insertarCliDisp(CliDisp cliDisp);

	void actualizarCliDisp(CliDisp cliDisp);

	void eliminarCliDisp(int id);
}
