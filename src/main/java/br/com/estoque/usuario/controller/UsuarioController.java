package br.com.estoque.usuario.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.usuario.dto.UsuarioDTO;
import br.com.estoque.usuario.error.handler.ErrorHandler;
import br.com.estoque.usuario.model.UsuarioModel;
import br.com.estoque.usuario.service.UsuarioService;
import br.com.estoque.util.EncryptionSHA;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8", value = "cadastrar")
	public ResponseEntity<?> registerUsuario(@RequestBody UsuarioDTO usuarioDTO)
			throws NoSuchAlgorithmException {
		UsuarioModel usuarioModel = this.usuarioService.usuarioByEmail(usuarioDTO.getEmail());
		if(usuarioModel != null) {
			return new ResponseEntity<>(new ErrorHandler("Já existe um usuário com este endereço de e-mail."),HttpStatus.FOUND);
		}
		usuarioModel = new UsuarioModel();
		usuarioModel.setEmailUsuario(usuarioDTO.getEmail());
		usuarioModel.setNomeUsuario(usuarioDTO.getNome()+" " + usuarioDTO.getSobrenome());
		usuarioModel.setSenhaUsuario(EncryptionSHA.encrypt(usuarioDTO.getSenha()));
		usuarioModel.setTelefoneUsuario(usuarioDTO.getTelefone());
		this.usuarioService.criarUsuario(usuarioModel);
		return new ResponseEntity<>(usuarioModel, HttpStatus.CREATED);
	}

	@GetMapping(produces = "application/json;charset=utf-8", value ="/carregar")
	public ResponseEntity<?> getUsers(@RequestParam(value = "idUsuario", required = false) Long idUsuario) {
		Optional<UsuarioModel> usuario = 
				Optional.ofNullable(this.usuarioService.get(idUsuario).get());
		return new ResponseEntity<>(usuario, HttpStatus.OK);
		
	}
	
	
}
