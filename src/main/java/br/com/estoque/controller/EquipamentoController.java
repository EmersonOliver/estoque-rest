package br.com.estoque.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.EstoqueDTO;
import br.com.estoque.enums.StatusEstoqueEnum;
import br.com.estoque.model.CidadeModel;
import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.model.EquipamentoModel;
import br.com.estoque.model.EstoqueModel;
import br.com.estoque.model.EstoqueUsuarioModel;
import br.com.estoque.model.FabricanteModel;
import br.com.estoque.service.EstoqueService;
import br.com.estoque.token.TokenProvider;
import br.com.estoque.usuario.model.UsuarioModel;
import br.com.estoque.usuario.service.UsuarioService;
import br.com.estoque.util.DataUtil;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {
	
	private static final Logger logger = Logger.getLogger(EquipamentoController.class);

	@Autowired
	private EstoqueService service;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping(value = "cadastrar",consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf8")
	public ResponseEntity<?> criarEquipamento(@RequestBody EstoqueDTO estoque, HttpServletRequest http){
		
		//Cadastrar Estoque
		EstoqueModel estoqueModel  = new EstoqueModel();
		this.cadastrarEstoque(estoque, estoqueModel);
		
		//Cadastrar Cidade
		CidadeModel cidadeModel = new CidadeModel();
		cadastrarCidade(estoque, cidadeModel);
		
		//Cadastrar Fabricante
		FabricanteModel fabricanteModel = new FabricanteModel();
		this.cadastrarFabricante(estoque, fabricanteModel);
		
		//Cadastrar Departamento
		DepartamentoModel departamentoModel = new DepartamentoModel();
		this.cadastrarDepartamento(cidadeModel.getIdCidade(), estoque, departamentoModel);
		
		
		//Cadastrar Equipamento
		EquipamentoModel equipamentoModel = new EquipamentoModel();
		this.cadastrarEquipamento(fabricanteModel.getIdFabricante(), 
				departamentoModel.getIdDepartamento(), estoqueModel.getIdEstoque(), estoque, equipamentoModel);
		
		//Registrar Estoque ao usuário
		EstoqueUsuarioModel estoqueUsuarioModel = new EstoqueUsuarioModel();
		String token = http.getHeader("Authorization");
		Optional<UsuarioModel> usuario = 
				this.usuarioService.get(tokenProvider.getTokenId(token));
		if(usuario.isPresent()) {
			estoqueUsuarioModel.setIdEstoque(estoqueModel.getIdEstoque());
			estoqueUsuarioModel.setIdUsuario(usuario.get().getIdUsuario());
			this.service.cadastrarEntradaUsuarioEstoque(estoqueUsuarioModel);
		}else {
			//Roll back de todas as transacoes
			return null;
		}
		
		return null;
	}

	
	private void cadastrarEstoque(EstoqueDTO estoque, EstoqueModel estoqueModel) {
		try {
			estoqueModel.setDataEntrada(DataUtil.obterDataFormatada(estoque.getDataEntradaEstoque(), DataUtil.FORMATO_DATA_HORA_ANGULAR));
			estoqueModel.setStatusEstoque(StatusEstoqueEnum.NOVA_ENTRADA.getCodigo());
			estoqueModel = this.service.cadastrarEntradaEstoque(estoqueModel);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
	private void cadastrarCidade(EstoqueDTO estoque, CidadeModel cidadeModel) {
		try {
			BeanUtils.copyProperties(estoque.getEquipamento().getDepartamento().getCidade(), cidadeModel);
			this.service.cadastrarCidade(cidadeModel);
			return;
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
	private void cadastrarDepartamento(Long idCidade, EstoqueDTO estoque, DepartamentoModel departamentoModel) {
		try {
			departamentoModel.setIdCidade(idCidade);
			departamentoModel.setNomeDepartamento(estoque.getEquipamento().getDepartamento().getNomeDepartamento());
			departamentoModel = this.service.cadastrarDepartamento(departamentoModel);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	private void cadastrarFabricante(EstoqueDTO estoque, FabricanteModel fabricanteModel) {
		try {
			fabricanteModel.setNomeFabricante(estoque.getEquipamento().getFabricante().getNome());
			fabricanteModel = this.service.cadastrarFabricante(fabricanteModel);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	private void cadastrarEquipamento(Long idFabricante, Long idDepartamento, Long idEstoque, EstoqueDTO estoque, EquipamentoModel equipamentoModel) {
		try {
			BeanUtils.copyProperties(estoque.getEquipamento(), equipamentoModel);
			equipamentoModel.setIdDepartamento(idDepartamento);
			equipamentoModel.setIdEstoque(idEstoque);
			equipamentoModel.setIdFabricante(idFabricante);
			equipamentoModel = this.service.cadastrarEquipamento(equipamentoModel);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
