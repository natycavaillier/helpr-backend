package com.api.helpr.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.helpr.domain.LogChamadoStatus;

public interface LogChamadoStatusRepository extends JpaRepository<LogChamadoStatus, Integer>{

	@Query(value="SELECT * FROM log_chamado_status WHERE data_alteracao=DATE(:dataDia)", nativeQuery=true)
	List<LogChamadoStatus> findLogChamadoStatusDia(LocalDate dataDia);
}
