package br.com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.EquipamentoModel;

@Repository
public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Long>{

	
	
}
