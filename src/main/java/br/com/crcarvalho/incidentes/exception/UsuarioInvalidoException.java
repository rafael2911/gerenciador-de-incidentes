package br.com.crcarvalho.incidentes.exception;

public class UsuarioInvalidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3020197194842857955L;
	
	
	public UsuarioInvalidoException(String descricao) {
		super(descricao);
	}

}
