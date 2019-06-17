package br.com.crcarvalho.incidentes.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.crcarvalho.incidentes.model.entity.Chamado;

public interface ChamadoRepository extends PagingAndSortingRepository<Chamado, Long> {

}
