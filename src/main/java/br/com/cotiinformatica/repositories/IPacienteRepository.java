package br.com.cotiinformatica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cotiinformatica.entities.Paciente;

public interface IPacienteRepository extends CrudRepository<Paciente, Integer> {

	@Query("select p from Paciente p order by p.nome")
	List<Paciente> findAll();
}
