package br.com.crcarvalho.incidentes.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Sla {
	
	@Id
	@Column(length=12)
	@NotBlank(message="Campo descrição não pode ficar em branco")
	@Length(min=4, max=12, message="A Descrição deve conter entre {min} e {max} caracteres.")
	private String descricao;
	
	@NotNull
	@Min(value=1, message="O valor mínimo deve ser {value} dias")
	private int dias;
	
	public Sla() {

	}

	public Sla(String descricao, int dias) {
		this.descricao = descricao;
		this.dias = dias;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}
	
}
