package br.com.estoque.usuario.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.usuario.dto.UsuarioDTO;
import br.com.estoque.usuario.model.UsuarioModel;
import br.com.estoque.usuario.service.UsuarioService;
import br.com.estoque.util.EncryptionSHA;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8", value = "register")
	public ResponseEntity<UsuarioModel> registerUsuario(@RequestBody UsuarioDTO usuarioDTO)
			throws NoSuchAlgorithmException {
		UsuarioModel usuarioModel = new UsuarioModel();
		BeanUtils.copyProperties(usuarioDTO, usuarioModel);
		usuarioModel.setSenhaUsuario(EncryptionSHA.encrypt(usuarioModel.getSenhaUsuario()));
		this.usuarioService.criarUsuario(usuarioModel);
		return new ResponseEntity<>(usuarioModel, HttpStatus.CREATED);
	}
	
	@RequestMapping("/users")
	@ResponseBody
	public String getUsers() {
		return "{\"users\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"}," +
		           "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
	}
}
