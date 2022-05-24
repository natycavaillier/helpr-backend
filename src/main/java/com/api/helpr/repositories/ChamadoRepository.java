package com.api.helpr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpr.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}