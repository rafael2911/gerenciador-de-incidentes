package br.com.crcarvalho.incidentes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crcarvalho.incidentes.model.entity.Origem;

public interface OrigemRepository extends JpaRepository<Origem, Long> {

}
