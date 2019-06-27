package br.com.crcarvalho.incidentes.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.crcarvalho.incidentes.model.entity.Chamado;
import br.com.crcarvalho.incidentes.model.entity.Usuario;

public interface ChamadoRepository extends PagingAndSortingRepository<Chamado, Long> {
	
	List<Chamado> findByRequerente(Usuario usuario);

}
