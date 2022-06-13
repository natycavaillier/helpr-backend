package com.api.helpr.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpr.domain.Chamado;
import com.api.helpr.domain.Cliente;
import com.api.helpr.domain.Tecnico;
import com.api.helpr.domain.dtos.ChamadoDTO;
import com.api.helpr.domain.dtos.ClienteDTO;
import com.api.helpr.domain.enums.Prioridade;
import com.api.helpr.domain.enums.Status;
import com.api.helpr.repositories.ChamadoRepository;
import com.api.helpr.repositories.PessoaRepository;
import com.api.helpr.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;

	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
	public Chamado create(@Valid ChamadoDTO objDto) {
		return repository.save(newChamado(objDto));
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO objDto) {
		objDto.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDto);
		return repository.save(oldObj);
	}
	
	public Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}

	
	
}
