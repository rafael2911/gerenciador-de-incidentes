package br.com.crcarvalho.incidentes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crcarvalho.incidentes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
}
