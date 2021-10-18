package es.local.ms.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.local.ms.dao.VuelosDao;
import es.local.ms.model.Vuelo;

@Service
public class VuelosServiceImpl implements VuelosService {

	@Autowired
	VuelosDao dao;
	
	/* Se filtran los vuelos pasados directamente mediante la aplicación del
	 * filtro en la capa DAO, al hacer la consulta en BBDD. */
	@Override
	public List<Vuelo> listarVuelosDisp(int plazas) {
		
		return dao.listarVuelosDisp().stream()
				.filter(v -> v.getPlazas()>=plazas)
				.collect(Collectors.toList());
	}
	
	/* Se recuperan todos los elementos de la BBDD y el filtro se aplica en
	 * esta capa de servicio simulando una sencilla lógica de negocio. */
	@Override
	public List<Vuelo> listarVuelos() {
		
		Date fechaSistema = new Date();
		
		return dao.listarVuelos().stream()
				.filter(v -> v.getFecha().after(fechaSistema))
				.collect(Collectors.toList());
	}

	@Override
	public void actualizarPlazas(int idVuelo, int plazas) {

		Vuelo vuelo = dao.buscarVuelo(idVuelo);
		if(vuelo != null) {
			
			vuelo.setPlazas(vuelo.getPlazas()-plazas);
			dao.reservarPlazas(vuelo);
		} 
	}

}
