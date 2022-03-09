package br.com.estoque.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tb_equipamento")
public class EquipamentoModel implements Serializable{
	
	private static final long serialVersionUID = -1525504097880119358L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipamento", nullable = false, precision = 8, unique = true)
	private Long idEquipamento;
	
	@Column(name = "id_fabricante", nullable = false)
	private Long idFabricante;
	
	@Column(name = "id_estoque", nullable = false)
	private Long idEstoque;
	
	@Column(name = "id_departamento", nullable = false)
	private Long idDepartamento;	
	
	@Column(name="nome_equipamento", nullable = false)
	private String nome;
	
	@Column(name = "numero_serie", nullable = false)
	private String numeroSerie;
	
	@Column(name = "patrimonio", nullable = false)
	private String patrimonio;
	
	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	@Column(name = "cor", nullable = false)
	private String cor;
	
	@Column(name = "status", nullable = false)
	private int statusEquipamento;
	
	
	@JsonIgnoreProperties("equipamentos")
	@ManyToOne
	@JoinColumn(name = "id_departamento", 
	referencedColumnName = "id_departamento", 
	insertable = false,
	updatable = false,
	foreignKey = @ForeignKey(name="fk_id_departamento"))
	private DepartamentoModel departamento;
	
	@JsonIgnoreProperties("equipamemntos")
	@ManyToOne
	@JoinColumn(name = "id_fabricante", 
	referencedColumnName = "id_fabricante",
	insertable = false,
	updatable = false,
	foreignKey = @ForeignKey(name="fk_id_fabricante"))
	private FabricanteModel fabricante;
	
	@JsonIgnoreProperties("equipamentos")
	@ManyToOne
	@JoinColumn(name = "id_estoque", referencedColumnName = "id_estoque", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name="fk_id_estoque"))
	private EstoqueModel estoque;
	
	
}
