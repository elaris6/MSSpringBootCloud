package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Contacto;

@Repository("agendaService")
public class AgendaDaoImpl implements AgendaDao {
	@PersistenceContext
	EntityManager em;

	@Transactional
	@Override
	public boolean agregarContacto(Contacto contacto) {
		em.persist(contacto);
		return true;
	}

	@Override
	public Contacto recuperarContacto(String email) {
		TypedQuery<Contacto> tquery=em.createNamedQuery("Contacto.findByEmail",Contacto.class);
		tquery.setParameter(1, email);
		List<Contacto> contactos=tquery.getResultList();
		return contactos.size()>0?contactos.get(0):null;
	}
	@Transactional
	@Override
	public void eliminarContacto(String email) {
		Query query=em.createNamedQuery("Contacto.deleteByEmail");
		//query.setParameter(1, email);
		query.setParameter("eml", email);
		query.executeUpdate();
		
	}

	@Override
	public List<Contacto> devolverContactos() {
		TypedQuery<Contacto> tquery=em.createNamedQuery("Contacto.findAll",Contacto.class);
		return tquery.getResultList();
	}
	@Transactional
	@Override
	public void eliminarContacto(int idContacto) {
		Contacto contacto=em.find(Contacto.class, idContacto);
		if(contacto!=null) {
			em.remove(contacto);
		}
		
	}

	@Override
	public Contacto recuperarContacto(int idContacto) {	
		return em.find(Contacto.class, idContacto);
	}

	@Transactional
	@Override
	public void actualizarContacto(Contacto contacto) {
		em.merge(contacto);
		
	}
	
	
	
}
