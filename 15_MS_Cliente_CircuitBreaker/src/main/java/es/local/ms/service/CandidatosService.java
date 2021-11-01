package es.local.ms.service;

import java.util.List;

import es.local.ms.model.Candidato;

public interface CandidatosService {

	List<Candidato> candidatos(String puesto);
}
