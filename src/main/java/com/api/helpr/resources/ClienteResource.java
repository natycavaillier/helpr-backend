package com.api.helpr.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.helpr.domain.Cliente;
import com.api.helpr.domain.dtos.ClienteDTO;
import com.api.helpr.services.ClienteService;


@RestController
@RequestMapping(value = "/service/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	//IDA
	// FRONT/POSTMAN -> CLIENTE RESOURCE -> CLIENTE SERVICE -> CLIENTE REPOSITORY -> BANCO DE DADOS
	//VOLTA
	// FRONT/POSTMAN <- CLIENTE DTO <- CLIENTE RESOURCE <- CLIENTE SERVICE <- CLIENTE REPOSITORY <- BANCO DE DADOS
	
	//Resposta do cliente solicitado pelo ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
}
