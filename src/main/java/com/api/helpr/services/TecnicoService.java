package com.api.helpr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired //Vinculo com repositório.
	private TecnicoRepository repository;

	@Autowired //Vinculo com repositório de pessoa.
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	//Métoido de busca por um ID no banco.
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado: " + id));
	}

	//Método de busca para todos os registros de técnicos
	public List<Tecnico> findAllTecnicos() {
		return repository.findAll();
	}

	//Método que fará a criação de novo técnico.
	public Tecnico create(TecnicoDTO objDto) {
		objDto.setId(null);
		objDto.setSenha(encoder.encode(objDto.getSenha()));
		validaCpfEEmail(objDto);
		Tecnico newObj = new Tecnico(objDto);
		return repository.save(newObj);
	}

	//Método para modificar técnicos existentes.
	public Tecnico update(Integer id, TecnicoDTO objDto) {
		objDto.setId(id);
		Tecnico oldObj = findById(id);
		validaCpfEEmail(objDto);
		oldObj = new Tecnico(objDto);
		return repository.save(oldObj);
	}
	
	//Excluirá um tecnico pela ordem do endpoint.
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O Tecnico: "+
		id+" tem chamados no sistema: "+
		obj.getChamados().size());
		}
		repository.deleteById(id);
	}
	
	//Validará os CPFs e E-mails para update e create. 
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
