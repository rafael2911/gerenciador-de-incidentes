package br.com.crcarvalho.incidentes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crcarvalho.incidentes.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
