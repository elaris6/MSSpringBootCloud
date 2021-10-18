package es.local.ms.dao;

import java.util.List;

import es.local.ms.model.Reserva;

public interface ReservasDao {
	
	public List<Reserva> listarReservas();
	
	public void crearReserva(Reserva res);

}
