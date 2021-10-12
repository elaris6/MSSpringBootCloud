package es.local.ms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.local.ms.model.Vuelo;

@Repository
public class VuelosDaoImpl implements VuelosDao {

	@Autowired
	VuelosJpaSpring jpa;
	
	@Override
	public List<Vuelo> listarVuelos() {
		return jpa.findAll();
	}

	@Override
	public List<Vuelo> listarVuelosDisp() {
		return jpa.vuelosFuturos();
	}
	
	@Override
	public Vuelo buscarVuelo(int idVuelo) {
		
		/* Necesario el uso del método adicional "orElse()", ya que el
		 * método "findById" devuelve un "Optional<T>" y se debe indicar
		 * que devolver en caso de que no se encuentre nada. */
		return jpa.findById(idVuelo).orElse(null);
	}

	@Override
	public void reservarPlazas(Vuelo vuelo) {

		jpa.save(vuelo);
	}

}
