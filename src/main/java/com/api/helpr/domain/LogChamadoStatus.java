package com.api.helpr.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class LogChamadoStatus implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAlteracao;
	
	private String dadoAlterado;
	private String dadoNovo;
	private Integer devCorrecao;
	
	
	public LogChamadoStatus() {
		super();
	}

	public LogChamadoStatus(Integer id, LocalDate dataAlteracao, String dadoAlterado, String dadoNovo,
			Integer devCorrecao) {
		super();
		this.id = id;
		this.dataAlteracao = dataAlteracao;
		this.dadoAlterado = dadoAlterado;
		this.dadoNovo = dadoNovo;
		this.devCorrecao = devCorrecao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDate dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getDadoAlterado() {
		return dadoAlterado;
	}

	public void setDadoAlterado(String dadoAlterado) {
		this.dadoAlterado = dadoAlterado;
	}

	public String getDadoNovo() {
		return dadoNovo;
	}

	public void setDadoNovo(String dadoNovo) {
		this.dadoNovo = dadoNovo;
	}

	public Integer getDevCorrecao() {
		return devCorrecao;
	}

	public void setDevCorrecao(Integer devCorrecao) {
		this.devCorrecao = devCorrecao;
	}
	
	
}
