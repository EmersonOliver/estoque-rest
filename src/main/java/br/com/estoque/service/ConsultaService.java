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

import br.com.estoque.model.CidadeModel;
import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.model.EquipamentoModel;
import br.com.estoque.model.EstoqueModel;
import br.com.estoque.model.FabricanteModel;
import br.com.estoque.repository.CidadeRepository;
import br.com.estoque.repository.DepartamentoRepository;
import br.com.estoque.repository.EquipamentoRepository;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FabricanteRepository;
import br.com.estoque.specs.EquipamentoSpecs;

@Service
public class ConsultaService {

	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	
	public Page<EquipamentoModel> pesquisar(String nomeEquipamento, List<Integer> statusEquipamento, Long fabricante,
			Long departamento, Integer page, String campo, String ordem) {
		Direction direction = ordem.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pagination = PageRequest.of(page, 10, Sort.by(direction, campo));
		if (nomeEquipamento == null && statusEquipamento == null && fabricante == null && departamento == null) {
			return this.equipamentoRepository.findAll(pagination);
		} else {
			Specification<EquipamentoModel> specs = EquipamentoSpecs.nomeEquipamento(nomeEquipamento)
					.or(EquipamentoSpecs.departamento(departamento).or(EquipamentoSpecs.fabricante(fabricante).or(
							statusEquipamento != null ? EquipamentoSpecs.statusEquipamento(statusEquipamento) : null)));
			return this.equipamentoRepository.findAll(specs, pagination);
		}
	}
	
	public EquipamentoModel detalharEquipamento(Long idEquipamento) {
		return this.equipamentoRepository.detalharEquipamento(idEquipamento).get();
	}
	
	public DepartamentoModel detalharDepartamento(Long idDepartamento) {
		return this.departamentoRepository.findById(idDepartamento).get();
	}
	
	public CidadeModel detalharCidade(Long idCidade) {
		return this.cidadeRepository.findById(idCidade).get();
	}
	
	public FabricanteModel detalharFabricante(Long idFabricante) {
		return this.fabricanteRepository.findById(idFabricante).get();
	}

	public EstoqueModel detalharEstoque(Long idEstoque) {
		return this.estoqueRepository.findById(idEstoque).get();
	}

}
