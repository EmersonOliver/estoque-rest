package br.com.estoque.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntrypoint implements AuthenticationEntryPoint{

	private static final Logger logger = Logger.getLogger(JwtAuthenticationEntrypoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		logger.debug("Usuario nao autorizado");
	}

}
