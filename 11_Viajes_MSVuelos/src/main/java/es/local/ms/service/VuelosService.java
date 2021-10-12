package es.local.ms.service;

import java.util.List;

import es.local.ms.model.Vuelo;

public interface VuelosService {

	public List<Vuelo> listarVuelos();
	
	public List<Vuelo> listarVuelosDisp(int plazas);
	
	public void actualizarPlazas(int idVuelo, int plazas);
}
