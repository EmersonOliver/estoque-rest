package br.com.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.DepartamentoModel;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoModel, Long>{
	
	
	@Query(name = "DepartamentoModel.buscarDepartamentoNome", value = "SELECT d FROM DepartamentoModel d WHERE UPPER(d.nomeDepartamento) = :nomeDepartamento")
	public Optional<DepartamentoModel> buscarDepartamentoNome(@Param("nomeDepartamento") String nomeDepartamento);

}
