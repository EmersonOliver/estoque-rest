package br.com.estoque.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tb_fabricante")
public class FabricanteModel implements Serializable {
	private static final long serialVersionUID = 5668545454772363960L;

	@Id
	@Column(name = "id_fabricante", nullable = false, unique = true, precision = 8)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFabricante;

	@Column(name = "nome_fabricante")
	private String nomeFabricante;
	
	@JsonIgnoreProperties("fabricante")
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fabricante", referencedColumnName = "id_fabricante", insertable = false, updatable = false)
	private List<EquipamentoModel> equipamemntos;

	public FabricanteModel(Long idFabricante, String nomeFabricante) {
		super();
		this.idFabricante = idFabricante;
		this.nomeFabricante = nomeFabricante;
	}

	public FabricanteModel(Long idFabricante, String nomeFabricante, List<EquipamentoModel> equipamemntos) {
		super();
		this.idFabricante = idFabricante;
		this.nomeFabricante = nomeFabricante;
		this.equipamemntos = equipamemntos;
	}

	public void toUpperCaseAndTrim() {
		this.nomeFabricante = this.nomeFabricante.toUpperCase().trim();
	}
}
