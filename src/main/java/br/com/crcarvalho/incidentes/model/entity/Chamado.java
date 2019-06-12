package br.com.crcarvalho.incidentes.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Chamado {

	private Long id;
	private String titulo;
	private String descricao;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataAbertura;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataEncerramento;
	@Enumerated(EnumType.STRING)
	private StatusChamado status;
	private Categoria categoria;
	private Origem origem;
	private Usuario requerente;
	private Usuario atendente;
	private List<Iteracao> iteracoes;
	private Sla sla;
	
	public Chamado() {
		this.iteracoes = new ArrayList<Iteracao>();
		this.dataAbertura = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public StatusChamado getStatus() {
		return status;
	}

	public void setStatus(StatusChamado statusChamado) {
		this.status = statusChamado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Usuario getRequerente() {
		return requerente;
	}

	public void setRequerente(Usuario requerente) {
		this.requerente = requerente;
	}

	public Usuario getAtendente() {
		return atendente;
	}

	public void setAtendente(Usuario atendente) {
		this.atendente = atendente;
	}

	public List<Iteracao> getIteracoes() {
		return Collections.unmodifiableList(iteracoes);
	}
	
	public void adicionaIteracao(Iteracao iteracao) {
		this.iteracoes.add(iteracao);
	}

	public Sla getSla() {
		return sla;
	}

	public void setSla(Sla sla) {
		this.sla = sla;
	}

}
