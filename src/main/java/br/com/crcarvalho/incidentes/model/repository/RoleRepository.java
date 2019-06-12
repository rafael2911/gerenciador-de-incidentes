package br.com.crcarvalho.incidentes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crcarvalho.incidentes.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
