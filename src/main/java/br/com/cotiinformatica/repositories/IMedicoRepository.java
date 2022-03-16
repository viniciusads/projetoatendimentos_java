package br.com.cotiinformatica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cotiinformatica.entities.Medico;

public interface IMedicoRepository extends CrudRepository<Medico, Integer> {

	@Query("select m from Medico m order by m.nome")
	List<Medico> findAll();
}
