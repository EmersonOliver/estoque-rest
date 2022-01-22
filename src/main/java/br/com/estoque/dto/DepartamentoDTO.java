package br.com.estoque.dto;

import lombok.Data;

@Data
public class DepartamentoDTO {

	private String nomeDepartamento;
	private CidadeDTO cidade;
}
