package br.com.crcarvalho.incidentes.model.entity;

public class Sla {
	
	private String descricao;
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
