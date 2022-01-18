package br.com.estoque.usuario.error.handler;

import lombok.Data;

@Data
public class ErrorHandler {
	
	private String message;

	public ErrorHandler(String message) {
		this.message = message;
	}

}
