package com.api.helpr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpr.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}