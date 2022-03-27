package br.com.estoque.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.estoque.model.EquipamentoModel;
import br.com.estoque.repository.EquipamentoRepository;
import br.com.estoque.specs.EquipamentoSpecs;

@Service
public class DashboardService {

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	public Long countEquipamento() {
		return this.equipamentoRepository.countEquipamento();
	}

	public Long countEquipamentoFuncionando() {
		Specification<EquipamentoModel> specs = EquipamentoSpecs.statusEquipamento(Arrays.asList(1));
		return Long.valueOf(this.equipamentoRepository.findAll(specs).size());
	}
	
	public Long countEquipamentoDanificado() {
		Specification<EquipamentoModel> specs = EquipamentoSpecs.statusEquipamento(Arrays.asList(2));
		return Long.valueOf(this.equipamentoRepository.findAll(specs).size());
	}
	
	public Long countEquipamentoEmManutencao() {
		Specification<EquipamentoModel> specs = EquipamentoSpecs.statusEquipamento(Arrays.asList(3));
		return Long.valueOf(this.equipamentoRepository.findAll(specs).size());
	}
	
	public Long countEquipamentoDisponiveis() {
		Specification<EquipamentoModel> specs = EquipamentoSpecs.statusEquipamento(Arrays.asList(1)).and(EquipamentoSpecs.statusEstoque(Arrays.asList(4,5,8)));
		return Long.valueOf(this.equipamentoRepository.findAll(specs).size());
	}
	public Long countEquipamentoAlugados() {
		Specification<EquipamentoModel> specs = EquipamentoSpecs.statusEquipamento(Arrays.asList(1,2,3)).and(EquipamentoSpecs.statusEstoque(Arrays.asList(6,7)));
		return Long.valueOf(this.equipamentoRepository.findAll(specs).size());
	}
	
	
}
