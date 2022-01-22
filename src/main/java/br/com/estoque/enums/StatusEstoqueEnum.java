package br.com.estoque.enums;

public enum StatusEstoqueEnum {

	EM_ESTOQUE(4, "E0004"), 
	DISPONIVEL(5, "D0005"), 
	NAO_DISPONIVEL(6, "NA0006"), 
	FORA_ESTOQUE(7, "FE0007"),
	NOVA_ENTRADA(8, "NE0008");

	private Integer codigo;
	private String situacao;
	private static StatusEstoqueEnum[] values = StatusEstoqueEnum.values();

	private StatusEstoqueEnum(Integer codigo, String situacao) {
		this.situacao = situacao;
		this.codigo = codigo;
	}

	public static StatusEstoqueEnum getStatusEstoque(int codigo) {
		for(StatusEstoqueEnum s : values) {
			if(s.getCodigo().equals(codigo)) {
				return s;
			}
		}
		return null;
	}
	
	public static StatusEstoqueEnum getStatusEstoque(String situacao) {
		for(StatusEstoqueEnum s : values) {
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
