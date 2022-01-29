package br.com.estoque.specs;

import org.springframework.data.jpa.domain.Specification;

import br.com.estoque.model.EquipamentoModel;

public class EquipamentoSpecs {
	
	public static Specification<EquipamentoModel> nomeEquipamento(String nomeEquipamento){
		return (root, query, builder) -> builder.or(builder.equal(root.get("nome"), nomeEquipamento));
	}
	
	public static Specification<EquipamentoModel> statusEquipamento(Integer statusEquipamento){
		return (root, query, builder) -> builder.or(builder.equal(root.get("statusEquipamento"), statusEquipamento));
	}
	
	public static Specification<EquipamentoModel> fabricante(Long idFabricante){
		return (root, query, builder) -> builder.or(builder.equal(root.get("fabricante").get("idFabricante"), idFabricante));
	}
	
	public static Specification<EquipamentoModel> departamento(Long departamento){
		return (root, query, builder) -> builder.or(builder.equal(root.get("departamento").get("idDepartamento"), departamento));
	}

}
