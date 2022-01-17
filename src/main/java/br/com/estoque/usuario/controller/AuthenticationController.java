package br.com.estoque.usuario.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.token.service.TokenService;
import br.com.estoque.usuario.dto.AccountAuthentication;
import br.com.estoque.usuario.dto.TokenDTO;
import br.com.estoque.util.EncryptionSHA;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> auth(@RequestBody AccountAuthentication account) throws NoSuchAlgorithmException{
		UsernamePasswordAuthenticationToken usernameAuthToken = 
				new UsernamePasswordAuthenticationToken(account.getEmail(),EncryptionSHA.encrypt(account.getPassword()));
		Authentication authentication = authenticationManager.authenticate(usernameAuthToken);
		String token = tokenService.generateToken(authentication);
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
	}
}


