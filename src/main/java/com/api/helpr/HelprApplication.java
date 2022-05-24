package com.api.helpr;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.helpr.domain.Chamado;
import com.api.helpr.domain.Cliente;
import com.api.helpr.domain.Tecnico;
import com.api.helpr.domain.enums.Perfil;
import com.api.helpr.domain.enums.Prioridade;
import com.api.helpr.domain.enums.Status;
import com.api.helpr.repositories.ChamadoRepository;
import com.api.helpr.repositories.ClienteRepository;
import com.api.helpr.repositories.TecnicoRepository;

@SpringBootApplication
public class HelprApplication implements CommandLineRunner{
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(HelprApplication.class, args);
		System.out.println("Servidor HelpR.soulcode.com Funcionando localhost:8080");
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico t1 = new Tecnico(null, "Victor Icoma", "009.393.200-61","victor.icoma@gmail.com","gordinho123");
		t1.addPerfils(Perfil.ADMIN);
		
		Cliente c1 = new Cliente(null, "Salazar Sonserina", "172.551.910-02","sa.sonserina@gmail.com","ofideo123");
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, 
				Status.ANDAMENTO, "Chamado Inaugural","Este é o 1o chamado para o sistema.", t1, c1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}
}
