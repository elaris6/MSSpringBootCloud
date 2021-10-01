package es.local.ms.service;

import java.util.List;

import es.local.ms.model.Pais;

public interface PaisesService {

	List<Pais> obtenerPaises();
	List<Pais> buscarPaises(String name);
}
