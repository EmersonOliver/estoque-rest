package br.com.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.DashboardDTO;
import br.com.estoque.service.DashboardService;

@RestController
@RequestMapping("dashboard")
public class DashboardController {
	
	
	@Autowired 
	private DashboardService dashboardService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<DashboardDTO> dashboard() {
		return new ResponseEntity<>(DashboardDTO.builder()
				.countEquipamento(this.dashboardService.countEquipamento())
				.countEquipamentoAlugados(this.dashboardService.countEquipamentoAlugados())
				.countEquipamentoDanificado(this.dashboardService.countEquipamentoDanificado())
				.countEquipamentoDisponiveis(this.dashboardService.countEquipamentoDisponiveis())
				.countEquipamentoEmManutencao(this.dashboardService.countEquipamentoEmManutencao())
				.countEquipamentoFuncionando(this.dashboardService.countEquipamentoFuncionando())
				
				.build(), HttpStatus.OK);
	}

}
