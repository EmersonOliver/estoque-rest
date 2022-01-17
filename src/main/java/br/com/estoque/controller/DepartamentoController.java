package br.com.estoque.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.DepartamentoDTO;
import br.com.estoque.repository.DepartamentoRepository;
import br.com.estoque.service.DepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

	private DepartamentoService service;
	
	@PostMapping(value = "register", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
	public ResponseEntity<?> registrarDepartamento(@RequestBody @Valid DepartamentoDTO departamentoDTO) {

		return null;
	}

}
