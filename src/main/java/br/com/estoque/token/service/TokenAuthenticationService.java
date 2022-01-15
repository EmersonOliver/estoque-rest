package br.com.estoque.token.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.prefix}")
	private String prefix;

	private static final String HEADER = "Authorization";

	public void addAuthentication(HttpServletResponse httpResponse, String username) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 1);
		Date expirationDate = cal.getTime();

		String token = Jwts.builder().setSubject(username).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, this.secret).compact();
		httpResponse.addHeader(HEADER, this.prefix + " " + token);
	}
	public Authentication getAuthentication(HttpServletRequest httpRequest) {
		String token  = httpRequest.getHeader(HEADER);
		
		String email = Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJwt(token.replace(this.prefix, "")).getBody()
				.getSubject();
		if (email != null) {
			return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
		}
		return null;
	}
}
