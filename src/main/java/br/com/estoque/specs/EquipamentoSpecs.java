package br.com.estoque.specs;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.estoque.model.EquipamentoModel;

public class EquipamentoSpecs {
	
	public static Specification<EquipamentoModel> nomeEquipamento(String nomeEquipamento){
		return (root, query, builder) -> builder.or(builder.equal(root.get("nome"), nomeEquipamento));
	}
	
	public static Specification<EquipamentoModel> statusEquipamento(List<Integer> statusEquipamento){
		return new Specification<EquipamentoModel>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<EquipamentoModel> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				final Path<Integer> status = root.get("statusEquipamento");
				return status.in(statusEquipamento);
			}
		};
	}
	
	public static Specification<EquipamentoModel> fabricante(Long idFabricante){
		return (root, query, builder) -> builder.or(builder.equal(root.get("fabricante").get("idFabricante"), idFabricante));
	}
	
	public static Specification<EquipamentoModel> departamento(Long departamento){
		return (root, query, builder) -> builder.or(builder.equal(root.get("departamento").get("idDepartamento"), departamento));
	}

}
