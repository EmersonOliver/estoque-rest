package br.com.estoque.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.EstoqueModel;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long>{
	
	@Query(name = "EstoqueModel.buscarEstoqueData", value = "SELECT e FROM EstoqueModel e WHERE e.dataEntrada BETWEEN :entradaMenor and :entradaMaior")
	Optional<EstoqueModel> buscarEstoqueData(@Param("entradaMenor") Date entradaMenor,@Param("entradaMaior")  Date entradaMaior);

}
