package br.com.estoque.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.estoque.usuario.model.UsuarioModel;
import br.com.estoque.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public UsuarioModel criarUsuario(UsuarioModel usuario) {
		String encryptedSecuredPasswordHash = BCrypt.hashpw(usuario.getSenhaUsuario(), BCrypt.gensalt(12));
		usuario.setSenhaUsuario(encryptedSecuredPasswordHash);
		return this.usuarioRepository.save(usuario);
	}
	
	public List<UsuarioModel> carregarTodosUsuarios(){
		return this.usuarioRepository.findAll();
	}
	
	public Optional<UsuarioModel> get(Long id){
		return usuarioRepository.findById(id);
	}

	public UsuarioModel usuarioByEmail(String email) {
		return this.usuarioRepository.usuarioByEmail(email);
	}

	
	
}
