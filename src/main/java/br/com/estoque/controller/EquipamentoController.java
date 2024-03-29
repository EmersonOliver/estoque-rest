package br.com.estoque.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.EstoqueDTO;
import br.com.estoque.dto.input.EditarEstoqueDTO;
import br.com.estoque.enums.StatusEstoqueEnum;
import br.com.estoque.model.CidadeModel;
import br.com.estoque.model.DepartamentoModel;
import br.com.estoque.model.EquipamentoModel;
import br.com.estoque.model.EstoqueModel;
import br.com.estoque.model.EstoqueUsuarioModel;
import br.com.estoque.model.FabricanteModel;
import br.com.estoque.service.ConsultaService;
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
	private ConsultaService consultaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping(value = "cadastrar",consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf8")
	public ResponseEntity<?> criarEquipamento(@RequestBody EstoqueDTO estoque, HttpServletRequest http){
		
		//Cadastrar Estoque
		EstoqueModel estoqueModel  = new EstoqueModel();
		estoqueModel = this.cadastrarEstoque(estoque, estoqueModel);
		
		//Cadastrar Cidade
		CidadeModel cidadeModel = new CidadeModel();
		cidadeModel = cadastrarCidade(estoque, cidadeModel);
		
		//Cadastrar Fabricante
		FabricanteModel fabricanteModel = new FabricanteModel();
		fabricanteModel = this.cadastrarFabricante(estoque, fabricanteModel);
		
		//Cadastrar Departamento
		DepartamentoModel departamentoModel = new DepartamentoModel();
		departamentoModel =	this.cadastrarDepartamento(cidadeModel.getIdCidade(), estoque, departamentoModel);
		
		
		//Cadastrar Equipamento
		EquipamentoModel equipamentoModel = new EquipamentoModel();
		equipamentoModel = this.cadastrarEquipamento(fabricanteModel.getIdFabricante(), 
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
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
	@GetMapping(value = "consultar", produces = "application/json;charset=utf-8")
	public ResponseEntity<?> buscarEquipamento(
			@RequestParam(value = "nome", required = false) String nomeEquipamento, 
			@RequestParam(value="status", required = false) List<Integer> statusEquipamento, 
			@RequestParam(value="fabricante", required = false)Long fabricante,
			@RequestParam(value="departamento", required = false) Long departamento,
			@RequestParam(value="pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(value="campo", defaultValue = "idEquipamento")String campo,
			@RequestParam(value="ordem", defaultValue = "desc") String ordem){
		
		
		Page<EquipamentoModel> resultEquipamentos = 
				this.consultaService.pesquisar(nomeEquipamento, statusEquipamento, fabricante, departamento, pagina, campo, ordem);
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", String.valueOf(resultEquipamentos.getTotalElements()));
		headers.add("X-Total-Pages", String.valueOf(resultEquipamentos.getTotalPages()));
		return (resultEquipamentos.isEmpty()) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
				: new ResponseEntity<>(resultEquipamentos, headers, HttpStatus.OK);
	}
	
	@GetMapping("detalhar/{idEquipamento}")
	@ResponseBody
	public ResponseEntity<?> detalharEquipamento(@PathVariable("idEquipamento") Long idEquipamento){
		return new ResponseEntity<>(this.consultaService.detalharEquipamento(idEquipamento), HttpStatus.OK);
	}
	
	@PutMapping("editar")
	public ResponseEntity<?> editarEquipamento(@RequestBody EditarEstoqueDTO equipamento){
		EquipamentoModel equipamentoModel = 
				this.consultaService.detalharEquipamento(equipamento.getIdEquipamento());
		DepartamentoModel departamentoModel = 
				this.consultaService.detalharDepartamento(equipamento.getIdDepartamento());
		
		FabricanteModel fabricanteModel = 
				this.consultaService.detalharFabricante(equipamento.getIdFabricante());
		
		EstoqueModel estoqueModel = 
				this.consultaService.detalharEstoque(equipamento.getIdEstoque());
		
		BeanUtils.copyProperties(equipamento, equipamentoModel);
		equipamentoModel.setIdDepartamento(departamentoModel.getIdDepartamento());
		equipamentoModel.setIdFabricante(fabricanteModel.getIdFabricante());
		equipamentoModel.setIdEstoque(estoqueModel.getIdEstoque());
		equipamentoModel.setDepartamento(departamentoModel);
		equipamentoModel.setFabricante(fabricanteModel);
		equipamentoModel.setEstoque(estoqueModel);
		equipamentoModel.setStatusEquipamento(equipamento.getStatus());
		service.cadastrarEquipamento(equipamentoModel);
		
		return new ResponseEntity<>(equipamentoModel, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("listar")
	public ResponseEntity<?> listarEquipamento(){
		List<EquipamentoModel> list = this.consultaService.listarEquipamento();
		return new ResponseEntity<>((list.isEmpty()) ? HttpStatus.NO_CONTENT : list, HttpStatus.OK);
	}
	
	private EstoqueModel cadastrarEstoque(EstoqueDTO estoque, EstoqueModel estoqueModel) {
		try {
			estoqueModel.setDataEntrada(DataUtil.obterDataFormatada(estoque.getDataEntradaEstoque(), DataUtil.FORMATO_DATA_ANGULAR_HIFEN));
			estoqueModel.setStatusEstoque(StatusEstoqueEnum.NOVA_ENTRADA.getCodigo());
			return this.service.cadastrarEntradaEstoque(estoqueModel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		
	}
	
	private CidadeModel cadastrarCidade(EstoqueDTO estoque, CidadeModel cidadeModel) {
		try {
			BeanUtils.copyProperties(estoque.getEquipamento().getDepartamento().getCidade(), cidadeModel);
			return this.service.cadastrarCidade(cidadeModel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		
	}
	
	private DepartamentoModel cadastrarDepartamento(Long idCidade, EstoqueDTO estoque, DepartamentoModel departamentoModel) {
		try {
			departamentoModel.setIdCidade(idCidade);
			departamentoModel.setNomeDepartamento(estoque.getEquipamento().getDepartamento().getNomeDepartamento());
			return this.service.cadastrarDepartamento(departamentoModel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	private FabricanteModel cadastrarFabricante(EstoqueDTO estoque, FabricanteModel fabricanteModel) {
		try {
			fabricanteModel.setNomeFabricante(estoque.getEquipamento().getFabricante().getNome());
			return this.service.cadastrarFabricante(fabricanteModel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	private EquipamentoModel cadastrarEquipamento(Long idFabricante, Long idDepartamento, Long idEstoque, EstoqueDTO estoque, EquipamentoModel equipamentoModel) {
		try {
			BeanUtils.copyProperties(estoque.getEquipamento(), equipamentoModel);
			equipamentoModel.setIdDepartamento(idDepartamento);
			equipamentoModel.setIdEstoque(idEstoque);
			equipamentoModel.setIdFabricante(idFabricante);
			return this.service.cadastrarEquipamento(equipamentoModel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}
