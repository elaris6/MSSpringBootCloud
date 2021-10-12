package es.local.ms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.local.ms.model.Reserva;

@Repository
public class ReservasDaoImpl implements ReservasDao {

	@Autowired
	ReservasSpringJpa jpa;
	
	@Override
	public List<Reserva> listarReservas() {
		return jpa.findAll();
	}

	@Override
	public void crearReserva(Reserva res) {
		
		jpa.save(res);
	}

}
