package br.com.crcarvalho.incidentes.model.entity;

public enum TipoInteracao {
	MENSAGEM("Mensagem"), CONCLUIDO("Concluido"), CANCELADO("Cancelado");
	
	private String descricao;
	
	private TipoInteracao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
