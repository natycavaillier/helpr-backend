package com.api.helpr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpr.domain.Pessoa;
import com.api.helpr.domain.Tecnico;
import com.api.helpr.domain.dtos.TecnicoDTO;
import com.api.helpr.repositories.PessoaRepository;
import com.api.helpr.repositories.TecnicoRepository;
import com.api.helpr.services.exceptions.DataIntegrityViolationException;
import com.api.helpr.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado: " + id));
	}

	public List<Tecnico> findAllTecnicos() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDto) {
		objDto.setId(null);
		validaCpfEEmail(objDto);
		Tecnico newObj = new Tecnico(objDto);
		return repository.save(newObj);
	}

	private void validaCpfEEmail(TecnicoDTO objDto) {

		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = pessoaRepository.findByEmail(objDto.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
}
