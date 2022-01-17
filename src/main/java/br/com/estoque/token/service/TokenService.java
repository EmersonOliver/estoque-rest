package br.com.estoque.token.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.estoque.usuario.model.UsuarioModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenService {

	@Value("${jwt.secret}")
	private String secret;
	
//	@Bean
//	public UserDetails defaultUser() {
//		 return User.withUsername("admin").password("admin").roles("ADMIN").build();
//	}
//	
	
	public String generateToken(Authentication authentication) {
		 UsuarioModel usuarioModel = (UsuarioModel) authentication.getPrincipal();
		 
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.HOUR_OF_DAY, 1);
		 Date expiration = cal.getTime();
		 
		 return Jwts.builder().setIssuer("ApiEstoque")
				 .setSubject(usuarioModel.getIdUsuario().toString())
				 .setIssuedAt(new Date())
				 .setExpiration(expiration)
				 .signWith(SignatureAlgorithm.HS256, secret).compact();
		 
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
	
}
