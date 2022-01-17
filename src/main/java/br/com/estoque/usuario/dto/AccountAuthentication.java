package br.com.estoque.usuario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountAuthentication {

	private String email;
	private String password;
}
