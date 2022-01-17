package br.com.estoque.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class DepartamentoDTO {
	
	@NotEmpty(message = "Departamento nome nao pode ser vazio")
	private String nomeDepartamento;
	
	@NotEmpty(message = "Cidade nao pode ser vazio")
	private String cidade;
	
	private String estado;
	@NotEmpty(message = "Estado nao pode ser vazio")
	
	@NotEmpty(message="UF nao pode ser vazio")
	private String uf;
}
