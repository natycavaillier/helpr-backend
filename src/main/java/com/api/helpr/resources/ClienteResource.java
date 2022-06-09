package com.api.helpr.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	//Resposta de todos os clientes
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAllClientes(){
		List<Cliente> list = service.findAllClientes();
		List<ClienteDTO> listDto = list.stream()
				.map(cliente -> new ClienteDTO(cliente))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	//Inserindo um cliente
	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO objDto){
		Cliente newObj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//Alteração dos dados de um cliente
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> updateCliente(
			@PathVariable Integer id, @RequestBody ClienteDTO objDto){
		Cliente obj = service.update(id, objDto);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
}
