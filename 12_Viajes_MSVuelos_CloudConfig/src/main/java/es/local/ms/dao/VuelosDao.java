package es.local.ms.dao;

import java.util.List;

import es.local.ms.model.Vuelo;

public interface VuelosDao {
	
	public List<Vuelo> listarVuelos();
	
	public List<Vuelo> listarVuelosDisp();
	
	public Vuelo buscarVuelo(int idVuelo);
	
	public void reservarPlazas(Vuelo vuelo);

}
