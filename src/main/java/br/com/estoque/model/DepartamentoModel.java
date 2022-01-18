package br.com.estoque.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_departamento")
public class DepartamentoModel implements Serializable {

	private static final long serialVersionUID = 7967269642901636210L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departamento", nullable = false, unique = true, precision = 8)
	private Long idDepartamento;

	@Column(name = "id_cidade")
	private Long idCidade;

	@Column(name = "nome_departamento")
	private String nomeDepartamento;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento", insertable = false, updatable = false)
	private Set<EquipamentoModel> equipamentos;

	@ManyToOne
	@JoinColumn(name = "id_cidade", referencedColumnName = "id_cidade", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_id_cidade"))
	private CidadeModel cidade;

	public DepartamentoModel(Long idDepartamento, String nomeDepartamento) {
		super();
		this.idDepartamento = idDepartamento;
		this.nomeDepartamento = nomeDepartamento;
	}

	public DepartamentoModel(Long idDepartamento, String nomeDepartamento, Set<EquipamentoModel> equipamentos) {
		super();
		this.idDepartamento = idDepartamento;
		this.nomeDepartamento = nomeDepartamento;
		this.equipamentos = equipamentos;
	}

	public DepartamentoModel(Long idDepartamento, Long idCidade, String nomeDepartamento,
			Set<EquipamentoModel> equipamentos, CidadeModel cidade) {
		super();
		this.idDepartamento = idDepartamento;
		this.idCidade = idCidade;
		this.nomeDepartamento = nomeDepartamento;
		this.equipamentos = equipamentos;
		this.cidade = cidade;
	}

	public DepartamentoModel(String nomeDepartamento) {
		super();
		this.nomeDepartamento = nomeDepartamento;
	}
	
	
	
	

}
