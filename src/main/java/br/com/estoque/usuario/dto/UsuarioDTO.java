package br.com.estoque.usuario.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
	
	@NotEmpty(message = "Nome nao pode ser vazio")
	private String nome;
	
	@NotEmpty(message = "Sobrenome nao pode ser vazio")
	private String sobrenome;
	
	@NotEmpty(message = "Email nao pode ser vazio")
	private String email;
	
	@NotEmpty(message = "Senha nao pode ser vazio")
	private String senha;
	
	@NotEmpty(message = "Telefone nao pode ser vazio")
	private String telefone;
	

}
