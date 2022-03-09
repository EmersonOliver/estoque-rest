package br.com.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.ParamsDTO;
import br.com.estoque.service.EstoqueService;

@RestController
@RequestMapping("parametros")
public class ParametrosController {

	@Autowired
	private EstoqueService estoqueService;
	
	
	@GetMapping("listAll")
	public ParamsDTO departamentoModel(){
		ParamsDTO params = new ParamsDTO();
		params.setDepartamentos(this.estoqueService.carregarParametrosDepartamento());
		params.setFabricantes(this.estoqueService.carregarParametrosFabricante());
		return params;
	}
	
	@GetMapping("listCidade/{id_departamento}")
	public List<ParamsDTO> listarCidadeByDepartamento(@PathVariable("id_departamento") Long idDepartamento){
		
		
		return null;
	}
	
	
}
