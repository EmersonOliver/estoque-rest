package br.com.estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DashboardDTO {

	private Long countEquipamento;
	private Long countEquipamentoFuncionando;
	private Long countEquipamentoDanificado;
	private Long countEquipamentoEmManutencao;
	private Long countEquipamentoDisponiveis;
	private Long countEquipamentoAlugados;

}
