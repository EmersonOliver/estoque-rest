package br.com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.SaidaEstoqueModel;

@Repository
public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoqueModel, Long> {

}
