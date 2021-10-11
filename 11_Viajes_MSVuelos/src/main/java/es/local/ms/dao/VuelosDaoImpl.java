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

}
