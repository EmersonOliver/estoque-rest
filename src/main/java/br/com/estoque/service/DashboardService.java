package br.com.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.repository.EquipamentoRepository;

@Service
public class DashboardService {

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	public Long countEquipamento() {
		return this.equipamentoRepository.countEquipamento();
	}

}
