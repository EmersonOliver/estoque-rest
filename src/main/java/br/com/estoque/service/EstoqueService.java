package br.com.estoque.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.model.CidadeModel;
import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.model.EquipamentoModel;
import br.com.estoque.model.EstoqueModel;
import br.com.estoque.model.EstoqueUsuarioModel;
import br.com.estoque.model.FabricanteModel;
import br.com.estoque.repository.CidadeRepository;
import br.com.estoque.repository.DepartamentoRepository;
import br.com.estoque.repository.EquipamentoRepository;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.FabricanteRepository;
import br.com.estoque.repository.UsuarioEstoqueRepository;
import br.com.estoque.util.DataUtil;

@Service
public class EstoqueService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	@Autowired
	private UsuarioEstoqueRepository usuarioEstoqueRepository;

	public CidadeModel cadastrarCidade(CidadeModel cidade) {
		cidade.toUpperCaseAndTrim();
		Optional<CidadeModel> retorno = this.cidadeRepository.carregarCidade(cidade.getCidade(), cidade.getEstado(),
				cidade.getUf());
		if (retorno.isPresent()) {
			return retorno.get();
		}
		return this.cidadeRepository.save(cidade);
	}

	public FabricanteModel cadastrarFabricante(FabricanteModel fabricante) {
		fabricante.toUpperCaseAndTrim();
		Optional<FabricanteModel> retorno = this.fabricanteRepository
				.buscarFabricanteByNomeFabricante(fabricante.getNomeFabricante());
		if (retorno.isPresent()) {
			return retorno.get();
		}
		return this.fabricanteRepository.save(fabricante);
	}

	public EstoqueModel cadastrarEntradaEstoque(EstoqueModel estoque) {
		Date dataEntradaEstoque = new Date();
		Optional<EstoqueModel> retorno = this.estoqueRepository
				.buscarEstoqueData(DataUtil.menorData(dataEntradaEstoque), DataUtil.maiorData(dataEntradaEstoque));
		if (retorno.isPresent()) {
			return retorno.get();
		}
		estoque.setDataEntrada(dataEntradaEstoque);
		return this.estoqueRepository.save(estoque);
	}

	public DepartamentoModel cadastrarDepartamento(DepartamentoModel departamento) {
		departamento.toUpperCaseAndTrim();
		Optional<DepartamentoModel> retorno = this.departamentoRepository
				.buscarDepartamentoNome(departamento.getNomeDepartamento());
		if (retorno.isPresent()) {
			return retorno.get();
		}
		return this.departamentoRepository.save(departamento);
	}

	public EquipamentoModel cadastrarEquipamento(EquipamentoModel equipamento) {
		return this.equipamentoRepository.save(equipamento);
	}

	public void cadastrarEntradaUsuarioEstoque(EstoqueUsuarioModel estoqueUsuarioModel) {
		this.usuarioEstoqueRepository.save(estoqueUsuarioModel);
	}
	
	public List<DepartamentoModel> carregarDepartamentos(){
		return this.departamentoRepository.carregarDepartamentosParams().get();
	}
	
	public List<FabricanteModel> carregarFabricantes(){
		return this.fabricanteRepository.carregarFabricantesParams().get();
	}

}
