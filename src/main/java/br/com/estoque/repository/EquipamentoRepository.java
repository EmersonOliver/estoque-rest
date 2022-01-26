package br.com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.EquipamentoModel;

@Repository
public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Long>{

	
	@Query(name = "EquipamentoModel.countEquipamento", value = "SELECT COUNT(e) FROM EquipamentoModel e")
	Long countEquipamento();
}
