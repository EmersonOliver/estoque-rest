package br.com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.DepartamentoModel;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoModel, Long>{

}
