package br.com.estoque.controller;

import java.util.Date;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.input.SaidaEstoqueDTO;
import br.com.estoque.enums.StatusEstoqueEnum;
import br.com.estoque.model.EquipamentoModel;
import br.com.estoque.model.EstoqueModel;
import br.com.estoque.model.SaidaEstoqueModel;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.SaidaEstoqueRepository;
import br.com.estoque.service.ConsultaService;
import br.com.estoque.usuario.model.UsuarioModel;
import br.com.estoque.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping("estoque")
public class EstoqueController {

	private static final Logger logger = Logger.getLogger(EstoqueController.class);

	@Autowired
	private ConsultaService consultaService;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SaidaEstoqueRepository saidaEstoqueRepository;

	@PostMapping("saida")
	public ResponseEntity<?> registrarSaida(@RequestBody SaidaEstoqueDTO saidaEstoque) {
		logger.info("--> Requicao estoque saida");
		try {
			EquipamentoModel equipamentoModel = this.consultaService
					.detalharEquipamento(saidaEstoque.getIdEquipamento());
			
			EstoqueModel estoqueModel = 
					this.consultaService.detalharEstoque(equipamentoModel.getIdEstoque());
			
			UsuarioModel usuario = this.usuarioRepository.findById(saidaEstoque.getIdUsuario()).get();
			SaidaEstoqueModel saidaEstoqueModel = new SaidaEstoqueModel();
			
			saidaEstoqueModel.setEquipamento(equipamentoModel);
			saidaEstoqueModel.setIdEquipamento(equipamentoModel.getIdEquipamento());
			saidaEstoqueModel.setDtSaida(new Date(System.currentTimeMillis()));
			saidaEstoqueModel.setObservacao(saidaEstoque.getObservacao());
			saidaEstoqueModel.setUsuario(usuario);
			saidaEstoqueModel.setIdUsuario(usuario.getIdUsuario());
			saidaEstoqueModel =	saidaEstoqueRepository.save(saidaEstoqueModel);
			
			//Update estoque
			estoqueModel.setDataSaida(saidaEstoqueModel.getDtSaida());
			estoqueModel.setStatusEstoque(StatusEstoqueEnum.NAO_DISPONIVEL.getCodigo());
			this.estoqueRepository.save(estoqueModel);
			
			return new ResponseEntity<>(saidaEstoqueModel,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
