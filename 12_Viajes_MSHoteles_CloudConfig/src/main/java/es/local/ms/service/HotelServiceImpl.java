package es.local.ms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.local.ms.dao.HotelDao;
import es.local.ms.model.Hotel;

@Service
public class HotelServiceImpl implements HotelService {

	// Se injecta la capa DAO
	@Autowired
	HotelDao dao;
	
	@Override
	public List<Hotel> listarHoteles() {
		/* Este método devolvería todos los hoteles sin filtrar. */	
		return dao.listarHoteles();
	}

	@Override
	public List<Hotel> listarHotelesDisp() {
		/* Este método realiza un filtro para devolver solo los hoteles
		 * disponibles simulando una pequeña lógica de negocio. */
	
		return dao.listarHoteles().stream()
				.filter(h -> h.getDisponible()==true)
				.collect(Collectors.toList());
	}

}
