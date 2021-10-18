package es.local.ms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.local.ms.model.Hotel;

public interface HotelJpaSpring extends JpaRepository<Hotel, Integer> {
	
	/* Creamos una consulta customizada para obtener solo los hoteles
	 * que estén disponibles en el momentode la consulta. */
	@Query("SELECT h from Hotel h WHERE h.disponible = true")
	public List<Hotel> listarHotelesDisp();

}
