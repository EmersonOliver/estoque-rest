package br.com.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public void cadastrarDepartamentos(DepartamentoModel departamento) {
		this.departamentoRepository.save(departamento);
	}
}
