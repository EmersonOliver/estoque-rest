package br.com.estoque.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.enums.StatusEstoqueEnum;

@RestController
@RequestMapping("parametros")
public class ParametrosController {

	
	@GetMapping("estoque")
	@ResponseBody
	public Map<String, Object> statusEstoque(){
		Map<String, Object> statusEstoque = new HashMap<>();
		for(StatusEstoqueEnum e : StatusEstoqueEnum.values()) {
			statusEstoque.put(e.name(), e.getCodigo());
		}
		return statusEstoque;
	}
	
	
	
}
