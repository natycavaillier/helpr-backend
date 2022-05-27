package com.api.helpr.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.helpr.domain.Tecnico;
import com.api.helpr.services.TecnicoService;

@RestController
@RequestMapping(value = "/service/tecnico")
public class TecnicoResource {
	//localhost:8080/service/tecnico/1
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
