package br.com.estoque.dto.input;

import lombok.Data;

@Data
public class SaidaEstoqueDTO {

	private Long idEquipamento;
	private Long idUsuario;
	private String observacao;
	
}
