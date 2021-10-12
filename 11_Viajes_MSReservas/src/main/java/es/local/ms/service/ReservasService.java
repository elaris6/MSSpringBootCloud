package es.local.ms.service;

import java.util.List;

import es.local.ms.model.Reserva;

public interface ReservasService {

	public List<Reserva> recuperarReservas();
	
	public void nuevaReserva(Reserva res, int plazas);
}
