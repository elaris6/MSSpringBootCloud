package es.local.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.local.ms.model.Hotel;

public interface HotelService {

	public List<Hotel> listarHoteles();
	
	public List<Hotel> listarHotelesDisp();
}
