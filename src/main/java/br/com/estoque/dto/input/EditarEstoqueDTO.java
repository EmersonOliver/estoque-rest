package br.com.estoque.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditarEstoqueDTO {

	private String cidade;
	private String cor;
	private String dataEntrada;
	private String departamento;
	private String estado;
	private String fabricante;
	private Long idDepartamento;
	private Long idEquipamento;
	private Long idEstoque;
	private Long idFabricante;
	private String modelo;
	private String nome;
	private String numeroSerie;
	private String patrimonio;
	private Integer status;
	private String uf;

}
