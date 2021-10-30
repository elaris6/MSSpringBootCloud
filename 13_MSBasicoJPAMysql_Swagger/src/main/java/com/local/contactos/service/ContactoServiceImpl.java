package com.local.contactos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.contactos.dao.ContactoDao;
import com.local.contactos.model.Contacto;

/* Clase implementadora de la capa de servicio, que será la que incluya toda la
 * lógica de negocio que fuese necesaria y que será independiente de la tecnología
 * de acceso a datos, que está implementada en Modelo y clase DAO.
 * Para indicar a Spring que esta clase implementará la capa de servicio se emplea
 * la anotación "Service". Spring instanciará automáticamente esta clase. */
@Service
public class ContactoServiceImpl implements ContactoService {

	@Autowired
	ContactoDao dao;
	
	@Override
	public boolean agregarContacto(Contacto contacto) {
		
		/* Se añade la pequeña lógica de comprobar primero si el contacto existe. */
		if(dao.recuperarContacto(contacto.getIdContacto()) == null) {
			dao.agregarContacto(contacto);
			return true;
		}
		return false;
	}

	@Override
	public List<Contacto> recuperarContactos() {
		
		return dao.devolverContactos();
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		
		/* Se añade la pequeña lógica de comprobar primero si el contacto existe. */
		if(dao.recuperarContacto(contacto.getIdContacto()) != null) {
			dao.actualizarContacto(contacto);
		}
	}

	@Override
	public boolean eliminarContacto(int idContacto) {
		
		if(dao.recuperarContacto(idContacto) != null) {
			dao.eliminarContacto(idContacto);
			return true;
		}
		return false;
	}

	@Override
	public Contacto buscarContacto(int idContacto) {
		
		return dao.recuperarContacto(idContacto);
	}
	
	@Override
	public Contacto buscarContactoDni(String dni) {
		
		return dao.recuperarContactoDni(dni);
	}

}
