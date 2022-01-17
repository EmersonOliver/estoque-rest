package br.com.estoque.usuario.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.estoque.usuario.model.UsuarioModel;
import br.com.estoque.usuario.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UsuarioModel> optional = this.usuarioRepository.findByEmail(email);
		if(optional.isPresent()){
				return optional.get();
		}
		throw new UsernameNotFoundException("User not found");	
	}

}
