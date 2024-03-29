package br.com.crcarvalho.incidentes.model.entity;

import java.time.LocalDateTime;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Interacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable=false)
	@NotBlank(message="O campo descrição não pode ficar em branco")
	private String mensagem;
	
	@Column(nullable=false)
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	@Column(nullable=false)
	@NotNull(message="Campo tipo interação é obrigatório.")
	@Enumerated(EnumType.STRING)
	private TipoInteracao tipoInteracao;
	
	public Interacao() {
		this.data = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return (mensagem != null) ? mensagem.replace("\r\n", "<br />") : mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getData() {
		return data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoInteracao getTipoInteracao() {
		return tipoInteracao;
	}

	public void setTipoInteracao(TipoInteracao tipoInteracao) {
		this.tipoInteracao = tipoInteracao;
	}

	@Override
	public String toString() {
		return "Iteracao [id=" + id + ", mensagem=" + mensagem + ", data=" + data + ", usuario=" + usuario.getEmail() + "]";
	}
	
}
