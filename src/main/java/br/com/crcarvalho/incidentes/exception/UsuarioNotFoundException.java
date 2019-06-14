package br.com.crcarvalho.incidentes.exception;

public class UsuarioNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3020197194842857955L;
	
	
	public UsuarioNotFoundException(String descricao) {
		super(descricao);
	}

}
