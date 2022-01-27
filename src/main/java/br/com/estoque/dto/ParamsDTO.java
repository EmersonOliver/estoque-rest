package br.com.estoque.dto;

import java.util.List;

import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.model.FabricanteModel;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class ParamsDTO {

	private List<FabricanteModel> fabricantes;
	
	private List<DepartamentoModel> departamentos;
	
	
}
