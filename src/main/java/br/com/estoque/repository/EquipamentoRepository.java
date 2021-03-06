package br.com.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.EquipamentoModel;

@Repository
public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Long>, JpaSpecificationExecutor<EquipamentoModel>{

	
	@Query(name = "EquipamentoModel.countEquipamento", value = "SELECT COUNT(e) FROM EquipamentoModel e")
	Long countEquipamento();
	
	
	@Query(name="EquipamentoModel.buscarEquipamento", value = "SELECT e FROM EquipamentoModel e WHERE "
			+ "e.nome LIKE :nomeEquipamento% or e.idFabricante =:fabricante "
			+ "or e.statusEquipamento = :statusEquipamento or e.idDepartamento =:departamento")
	List<EquipamentoModel> buscarEquipamento(
			@Param("nomeEquipamento")String nomeEquipamento,
			@Param("statusEquipamento")Integer statusEquipamento,
			@Param("fabricante") Long fabricante,
			@Param("departamento") Long departamento);
	
	@Query("SELECT e FROM EquipamentoModel e ORDER BY e.idEquipamento DESC")
	List<EquipamentoModel> listarEquipamentos();
	
	@Query("SELECT e FROM EquipamentoModel e WHERE e.idEquipamento=:idEquipamento")
	Optional<EquipamentoModel> detalharEquipamento(@Param("idEquipamento") Long idEquipamento);
	
	
}
