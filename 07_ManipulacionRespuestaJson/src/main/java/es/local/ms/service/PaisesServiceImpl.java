package es.local.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.local.ms.model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {

	String url = "https://restcountries.eu/rest/v2/all";
	
	@Autowired
	RestTemplate template;
	
	@Override
	public List<Pais> obtenerPaises() {

		return null;
	}

	@Override
	public List<Pais> buscarPaises(String name) {

		return null;
	}

}
