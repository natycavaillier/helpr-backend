package com.api.helpr.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpr.domain.Chamado;
import com.api.helpr.domain.Cliente;
import com.api.helpr.domain.dtos.ChamadoDTO;
import com.api.helpr.domain.dtos.ClienteDTO;
import com.api.helpr.services.ChamadoService;

@RestController
@RequestMapping(value = "/service/chamados")
public class ChamadoResource {

	@Autowired
	private ChamadoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado obj = service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAllChamados() {
		List<Chamado> list = service.findAll();
		List<ChamadoDTO> listDto = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	/*
	@GetMapping(value="/relatorio/{tecnico}")
	public ResponseEntity<List<ChamadoDTO>> reportTecnicoChamado(@PathVariable Integer tecnico){
		List<Chamado> listReport = service.reportTecnicoChamado(tecnico);
		List<ChamadoDTO> listDto = listReport.stream().map(rep -> new ChamadoDTO(rep)).collect(Collectors.toList());
		return null;
	}*/
	
	@PreAuthorize("hasAnyRole('ROLE_TECNICO')")
	@PostMapping
	public ResponseEntity<ChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO objDto){
		Chamado newObj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TECNICO')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDto){
		Chamado newObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new ChamadoDTO(newObj));
	}

}
