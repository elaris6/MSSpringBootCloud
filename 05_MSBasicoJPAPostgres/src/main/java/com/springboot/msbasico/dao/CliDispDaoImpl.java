package com.springboot.msbasico.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.msbasico.model.CliDisp;

/* La anotación "Repository" hace que Spring instancie automáticamente la clase
 * para gestionar el modelo. */
@Repository
public class CliDispDaoImpl implements CliDispDao {

	@Autowired
	CliDispJpaSpring jpaOper;

	@Override
	public CliDisp buscarCliDisp(int id) {

		/* En este caso se puede indicar una instrucción de alternativa de valor
		 * en caso de no encontrarse la entidad buscada por el identificador
		 * proporcionado. */
		return jpaOper.findById(id).orElse(null);
	}

	@Override
	public List<CliDisp> listarClientesDispositivos() {

		return jpaOper.findAll();
	}

	@Override
	public List<CliDisp> buscarCliDispHashDni(String hashDni) {

		return jpaOper.findByHashDni(hashDni);
	}

	@Override
	public void insertarCliDisp(CliDisp cliDisp) {

		jpaOper.save(cliDisp);
	}

	@Override
	public void actualizarCliDisp(CliDisp cliDisp) {

		jpaOper.save(cliDisp);
	}

	@Override
	public void eliminarCliDisp(int id) {

		jpaOper.deleteById(id);
	}


}
