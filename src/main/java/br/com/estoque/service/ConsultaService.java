package br.com.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.estoque.model.EquipamentoModel;
import br.com.estoque.repository.EquipamentoRepository;
import br.com.estoque.specs.EquipamentoSpecs;

@Service
public class ConsultaService {

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	public List<EquipamentoModel> buscarEquipamento(String nomeEquipamento, Integer statusEquipamento, Long fabricante,
			Long departamento) {
			return (nomeEquipamento == null || nomeEquipamento.equals("") 
					&& statusEquipamento == null
					&& fabricante == null
					&& departamento == null) ? this.equipamentoRepository.listarEquipamentos() 
							: this.equipamentoRepository.buscarEquipamento(nomeEquipamento, statusEquipamento, fabricante,
							departamento);
	}
	
	public Page<EquipamentoModel>pesquisar(String nomeEquipamento, Integer statusEquipamento, Long fabricante,
			Long departamento, Integer page, String campo, String ordem){
		
		Specification<EquipamentoModel> specs = 
				EquipamentoSpecs.nomeEquipamento(nomeEquipamento)
						.or(EquipamentoSpecs.departamento(departamento)
								.or(EquipamentoSpecs.fabricante(fabricante)
										.or(EquipamentoSpecs.statusEquipamento(statusEquipamento))));
		
		Direction direction  = ordem.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pagination = PageRequest.of(page, 10, Sort.by(direction, campo));
		
		return this.equipamentoRepository.findAll(specs, pagination);
		
	}

}
