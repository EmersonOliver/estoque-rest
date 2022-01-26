package br.com.estoque.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class DashboardDTO {

	private Long countEquipamento;

	public DashboardDTO(Long countEquipamento) {
		super();
		this.countEquipamento = countEquipamento;
	}

}
