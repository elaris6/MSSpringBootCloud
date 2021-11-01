package com.local.contactos.dao;

import java.util.List;

import com.local.contactos.model.Contacto;

/* Interfaz para operaciones de acceso a datos. */
public interface ContactoDao {

	void agregarContacto(Contacto contacto);
	
	Contacto recuperarContacto(String email);
	
	void eliminarContacto(String email);
	
	List<Contacto> devolverContactos();
	
	void eliminarContacto(int idContacto);
	
	Contacto recuperarContacto(int idContacto);
	
	void actualizarContacto(Contacto contacto);
	
	Contacto recuperarContactoDni(String dni);
}
