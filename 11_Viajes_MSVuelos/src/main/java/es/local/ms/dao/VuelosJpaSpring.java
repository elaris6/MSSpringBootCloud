package es.local.ms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.local.ms.model.Vuelo;

public interface VuelosJpaSpring extends JpaRepository<Vuelo, Integer> {

	/* Consulta específica para filtrar ya desde BBDD los vuelos que
	 * son de fechas pasadas.
	 * En función de la lógica a aplicar y el juego de datos, interesará
	 * hacerlo directamente en la consulta, o en la capa de servicio.*/
	@Query("SELECT v FROM Vuelo v WHERE v.fecha > CURRENT_DATE")
	List<Vuelo> vuelosFuturos();

}
