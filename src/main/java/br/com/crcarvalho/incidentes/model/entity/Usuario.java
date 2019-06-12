package br.com.crcarvalho.incidentes.model.entity;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Usuario {

	private String email;
	private String senha;
	private String nome;
	private String sobrenome;
	@Enumerated(EnumType.STRING)
	private StatusUsuario status;
	private List<Role> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
