package br.com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.EstoqueUsuarioModel;

@Repository
public interface UsuarioEstoqueRepository extends JpaRepository<EstoqueUsuarioModel, Long> {

	
	
	
}
