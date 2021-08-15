package com.local.contactos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.local.contactos.model.Contacto;

/* La anotación "Repository" hace que Spring instancie automáticamente la clase
 * para gestionar el modelo. */
@Repository
public class ContactoDaoImpl implements ContactoDao {

	/* Se inyecta la dependencia con la la interfaz que autoimplementará Spring,
	 * para la gestión de las interacciones con BBDD. */
	@Autowired
	ContactoJpaSpring agenda;
	
	@Override
	public void agregarContacto(Contacto contacto) {

		agenda.save(contacto);
	}

	@Override
	public Contacto recuperarContacto(String email) {
		
		return agenda.findByEmail(email);
	}

	@Override
	public void eliminarContacto(String email) {
		
		agenda.eliminarPorEmail(email);
	}

	@Override
	public List<Contacto> devolverContactos() {
		
		return agenda.findAll();
	}

	@Override
	public void eliminarContacto(int idContacto) {
		
		agenda.deleteById(idContacto);
	}

	@Override
	public Contacto recuperarContacto(int idContacto) {
		
		/* En este caso se puede indicar una instrucción de alternativa de valor
		 * en caso de no encontrarse la entidad buscada por el identificador
		 * proporcionado. */
		return agenda.findById(idContacto).orElse(null);
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		
		/* El método "save" crea una entidad si no existe, y si existe la actualiza. */
		agenda.save(contacto);
	}

}
