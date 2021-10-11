package es.local.ms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.local.ms.model.Hotel;

@Repository
public class HotelDaoImpl implements HotelDao{
	
	@Autowired
	HotelJpaSpring hoteles;

	@Override
	public List<Hotel> listarHoteles() {
		/* Recuperamos todos los hoteles en lugar de solo los disponibles,
		 * para simular algo de lógica de negocio en la capa de servicio,
		 * filtrando ahí los disponibles. */
		
		//return hoteles.listarHotelesDisp();
		return hoteles.findAll();
	}

}
