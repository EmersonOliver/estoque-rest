package br.com.estoque.usuario.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
	
	@NotEmpty(message = "Nome nao pode ser vazio")
	private String nomeUsuario;
	
	@NotEmpty(message = "Email nao pode ser vazio")
	private String emailUsuario;
	
	@NotEmpty(message = "Senha nao pode ser vazio")
	private String senhaUsuario;
	
	@NotEmpty(message = "Telefone nao pode ser vazio")
	private String telefoneUsuario;
	
	private String token;

}
