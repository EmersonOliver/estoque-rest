package br.com.estoque.enums;

public enum StatusEquipamentoEnum {
	FUNCIONANDO(1, "Funcionando"),
	DANIFICADO(2, "Danificado"),
	EM_MANUTENCAO(3, "Em Manutenção");
	
	private Integer codigo;
	private String situacao;
	private static StatusEquipamentoEnum [] values = StatusEquipamentoEnum.values();
	
	private StatusEquipamentoEnum(Integer codigo, String situacao) {
		this.codigo = codigo;
		this.situacao = situacao;
	}
	
	public static StatusEquipamentoEnum getStatusEquipamento(int codigo) {
		for(StatusEquipamentoEnum s : values) {
			if(s.getCodigo().equals(codigo)) {
				return s;
			}
		}
		return null;
	}
	
	
	public static StatusEquipamentoEnum getStatusEquipamento(String situacao) {
		for(StatusEquipamentoEnum s : values) {
			if(s.getSituacao().equals(situacao)) {
				return s;
			}
		}
		return null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	
	
}
