package es.local.ms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import es.local.ms.model.Reserva;

public interface ReservasSpringJpa extends JpaRepository<Reserva, Integer> {

}
