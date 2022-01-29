package br.com.estoque.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipamentoVO {
	
	private Long idEquipamento;
	private String nomeEquipamento;
	private String nomeFabricante;
	private String nomeDepartamento;
	private String numeroSerie;
	private String patrimonio;
	private String modelo;
	private String cor;
	private String statusEquipamento;
	
	
	public EquipamentoVO(Long idEquipamento, String nomeEquipamento, String nomeFabricante, String nomeDepartamento,
			String numeroSerie, String patrimonio, String modelo, String cor, String statusEquipamento) {
		super();
		this.idEquipamento = idEquipamento;
		this.nomeEquipamento = nomeEquipamento;
		this.nomeFabricante = nomeFabricante;
		this.nomeDepartamento = nomeDepartamento;
		this.numeroSerie = numeroSerie;
		this.patrimonio = patrimonio;
		this.modelo = modelo;
		this.cor = cor;
		this.statusEquipamento = statusEquipamento;
	}
}
