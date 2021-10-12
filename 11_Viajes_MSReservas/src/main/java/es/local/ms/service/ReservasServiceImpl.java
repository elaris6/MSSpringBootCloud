package es.local.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.local.ms.dao.ReservasDao;
import es.local.ms.model.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService {

	@Autowired
	ReservasDao dao;
	
	@Autowired
	RestTemplate template;
	
	/* Al estar sustritos a Eureka, usaremos el nombre del servicio para acceder
	 * a él, no necesitando conocer la url real del servicio, que será proporcionada
	 * por Eureka. */
	String url = "http://servicio-vuelos";
	
	@Override
	public List<Reserva> recuperarReservas() {
		
		return dao.listarReservas();
	}

	@Override
	public void nuevaReserva(Reserva res, int plazas) {
		
		/* Se realiza la reserva de plazas en el vuelo asociado a la reserva,
		 * mediante una llamada con RestTemplate al servicio de vuelos.
		 * Como no se le pasa nada en el body de la llamada PUT, el último
		 * parámetro se informa con "null". */
		
		/* Dos formas de hacer la llamada PUT */
		//template.put(url + "/vuelos/" + res.getVuelo() + "/" + plazas, null);
		template.put(url + "/vuelos/{param1}/{param2}", null, res.getVuelo(), plazas);
		
		dao.crearReserva(res);
	
	}

}
