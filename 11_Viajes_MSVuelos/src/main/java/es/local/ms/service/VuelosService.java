package es.local.ms.service;

import java.util.List;

import es.local.ms.model.Vuelo;

public interface VuelosService {

	List<Vuelo> listarVuelos();
	
	List<Vuelo> listarVuelosDisp();
}
