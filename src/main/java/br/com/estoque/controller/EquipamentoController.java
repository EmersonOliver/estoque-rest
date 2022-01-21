package br.com.estoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.DepartamentoDTO;
import br.com.estoque.model.CidadeModel;
import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.service.DepartamentoCidadeService;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

	@Autowired
	private DepartamentoCidadeService service;

	@PostMapping(value = "cadastrar", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
	public ResponseEntity<?> registrarDepartamento(@RequestBody @Valid DepartamentoDTO departamentoDTO) {

		CidadeModel cidade = new CidadeModel(departamentoDTO.getCidade(), departamentoDTO.getEstado(),
				departamentoDTO.getUf());
		
		DepartamentoModel departamento = new DepartamentoModel(departamentoDTO.getNomeDepartamento());

		cidade = service.cadastrarCidade(cidade);
		departamento.setIdCidade(cidade.getIdCidade());
		service.cadastrarDepartamentos(departamento);
		return null;
	}

}
