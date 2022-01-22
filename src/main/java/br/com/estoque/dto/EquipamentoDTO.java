package br.com.estoque.dto;

import lombok.Data;

@Data
public class EquipamentoDTO {
	private String nome;
	private String numeroSerie;
	private String patrimonio;
	private String modelo;
	private String cor;
	private Integer statusEquipamento;
	private FabricanteDTO fabricante;
	private DepartamentoDTO departamento;
}
