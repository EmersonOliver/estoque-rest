package br.com.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.CidadeModel;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {

	@Query(name = "CidadeModel.carregarCidade", value = "SELECT c FROM CidadeModel c WHERE c.cidade =:cidade and c.estado=:estado and c.uf = :uf")
	Optional<CidadeModel> carregarCidade(@Param("cidade") String cidade, @Param("estado") String estado,
			@Param("uf") String uf);
	

}
