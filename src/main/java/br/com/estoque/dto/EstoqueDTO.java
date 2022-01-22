package br.com.estoque.dto;

import lombok.Data;

@Data
public class EstoqueDTO {

	private String dataEntradaEstoque;
	private String dataSaidaEstoque;
	private EquipamentoDTO equipamento;
	
}
