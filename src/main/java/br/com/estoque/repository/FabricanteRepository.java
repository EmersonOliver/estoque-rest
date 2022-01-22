package br.com.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.FabricanteModel;

@Repository
public interface FabricanteRepository extends JpaRepository<FabricanteModel, Long> {
	
	@Query(name = "FabricanteModel.buscarFabricanteByNomeFabricante", value = "SELECT f FROM FabricanteModel f WHERE UPPER(f.nomeFabricante) = :nomeFabricate")
	public Optional<FabricanteModel> buscarFabricanteByNomeFabricante(@Param("nomeFabricante") String nomeFabricante);

}
