package br.com.crcarvalho.incidentes.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.AccessDeniedException;

@Entity
public class Chamado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=80, nullable=false)
	@NotBlank(message="O campo título não pode ficar em branco")
	@Length(min=6, max=80, message="O título deve conter no mínimo entre {min} e {max} caracteres.")
	private String titulo;
	
	@Lob
	@Column(nullable=false)
	@NotBlank(message="O campo descrição não pode ficar em branco")
	private String descricao;
	
	@Column(nullable=false)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataAbertura;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataEncerramento;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private StatusChamado status;
	
	@NotNull(message="Campo categoria é obrigatório.")
	@ManyToOne
	@JoinColumn(name="categoria", nullable=false)
	private Categoria categoria;
	
	@NotNull(message="Campo origem é obrigatório.")
	@ManyToOne
	@JoinColumn(name="origem", nullable=false)
	private Origem origem;
	
	@ManyToOne
	@JoinColumn(name="requerente", nullable=false)
	private Usuario requerente;
	
	@ManyToOne
	@JoinColumn(name="atendente")
	private Usuario atendente;
	
	@OneToMany
	@Cascade(CascadeType.MERGE)
	private List<Interacao> interacoes;
	
	@ManyToOne
	@JoinColumn(name="sla")
	private Sla sla;
	
	public Chamado() {
		this.interacoes = new ArrayList<Interacao>();
		this.dataAbertura = LocalDateTime.now();
		this.status = StatusChamado.ABERTO;
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
		
		if(!requerente.getRoles().contains(new Role("ROLE_USER"))) {
			throw new AccessDeniedException("Somente usuários com o perfil USER podem abrir chamados.");
		}
		
		this.requerente = requerente;
		
	}

	public Usuario getAtendente() {
		return atendente;
	}

	public void setAtendente(Usuario atendente) {
		this.atendente = atendente;
	}

	public List<Interacao> getInteracoes() {
		return Collections.unmodifiableList(interacoes);
	}
	
	public void adicionaInteracao(Interacao interacao) {
		this.interacoes.add(interacao);
	}

	public Sla getSla() {
		return sla;
	}

	public void setSla(Sla sla) {
		this.sla = sla;
	}

}
