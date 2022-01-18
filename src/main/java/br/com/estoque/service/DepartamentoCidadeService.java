package br.com.estoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.model.CidadeModel;
import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.repository.CidadeRepository;
import br.com.estoque.repository.DepartamentoRepository;

@Service
public class DepartamentoCidadeService {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	private CidadeRepository cidadeRepository;

	public void cadastrarDepartamentos(DepartamentoModel departamento) {
		this.departamentoRepository.save(departamento);
	}
	
	public CidadeModel cadastrarCidade(CidadeModel cidade) {
		Optional<CidadeModel> cidadeModel = this.cidadeRepository.existCidade(cidade.getCidade().trim(),
				cidade.getEstado().trim(),
				cidade.getUf().trim());
		if(cidadeModel.isPresent()) {
			return cidadeModel.get();
		}
		return this.cidadeRepository.save(cidade);
	}
	
	
}
