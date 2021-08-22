package com.springboot.msbasico.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.msbasico.dao.CliDispDao;
import com.springboot.msbasico.model.CliDisp;
import com.springboot.msbasico.util.HMAC;

/* Clase implementadora de la capa de servicio, que será la que incluya toda la
 * lógica de negocio que fuese necesaria y que será independiente de la tecnología
 * de acceso a datos, que está implementada en Modelo y clase DAO.
 * Para indicar a Spring que esta clase implementará la capa de servicio se emplea
 * la anotación "Service". Spring instanciará automáticamente esta clase. */
@Service
public class CliDispServiceImpl implements CliDispService {

	@Autowired
	CliDispDao dao;

	@Override
	public List<CliDisp> listarTodosCliDisp() {

		return dao.listarClientesDispositivos();
	}

	@Override
	public List<CliDisp> buscarCliDispPorHashDni(String hashDni) {

		return dao.buscarCliDispHashDni(hashDni);
	}
	
	@Override
	public List<CliDisp> buscarCliDispPorDni(String dni) {
		
		String hmacSha256 = null;
		try {
		      hmacSha256 = HMAC.calcHmacSha256(dni.getBytes("UTF-8"));
		      System.out.println(hmacSha256);
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
		
		return dao.buscarCliDispHashDni(hmacSha256);
	}

	@Override
	public Integer altaCliDisp(CliDisp cliDisp) {

		if(dao.buscarCliDisp(cliDisp.getId()) == null) {
			dao.insertarCliDisp(cliDisp);
			return cliDisp.getId();
		} else {
			return 0;
		}
	}

	@Override
	public boolean cambiaCliDisp(CliDisp cliDisp) {

		if(dao.buscarCliDisp(cliDisp.getId()) != null) {
			dao.actualizarCliDisp(cliDisp);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean borraCliDisp(int id) {

		if(dao.buscarCliDisp(id) != null) {
			dao.eliminarCliDisp(id);
			return true;
		} else {
			return false;
		}
	}

}
