package com.springboot.msbasico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.msbasico.model.CliDisp;

/* De forma similar a la clase "CrudRepository<E,T>", la clase "JpaRepository<E,T>"
permite que Spring implemente de manera automática los métodos CRUD estándar, para
que solo se deban implementar los más específicos que requiera la aplicación.
La clase "JpaRepository<E,T>" a su vez implementa la "CrudRepository<E,T>" y añade
mejoras.
https://www.baeldung.com/spring-data-repositories */
public interface CliDispJpaSpring extends JpaRepository<CliDisp, Integer> {

	/* Añadir métodos customizados para DDL ó DML */

	List<CliDisp> findByHashDni(String hashDni);
}
