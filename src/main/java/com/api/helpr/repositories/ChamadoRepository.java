package com.api.helpr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.helpr.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

	@Query(value= "SELECT * FROM chamado WHERE tecnico_id = :tecnico AND status= 1", nativeQuery = true)
	List<Chamado> findByTecnico(Integer tecnico);

}