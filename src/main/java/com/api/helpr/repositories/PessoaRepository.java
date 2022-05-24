package com.api.helpr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpr.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}