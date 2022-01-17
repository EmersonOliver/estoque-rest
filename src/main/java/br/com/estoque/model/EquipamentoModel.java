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

import br.com.estoque.enums.StatusEquipamentoEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_equipamento")
public class EquipamentoModel implements Serializable{
	
	private static final long serialVersionUID = -1525504097880119358L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipamento", nullable = false, precision = 8, unique = true)
	private Long idEquipamento;
	
	@Column(name = "id_fabricante")
	private Long idFabricante;
	
	@Column(name = "id_departamento")
	private Long idDepartamento;	
	
	@Column(name="nome_equipamento")
	private String nome;
	
	@Column(name = "numero_serie")
	private String numeroSerie;
	
	@Column(name = "patrimonio")
	private String patrimonio;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "cor")
	private String cor;
	
	@Column(name = "status")
	private StatusEquipamentoEnum statusEquipamento;
	
	@ManyToOne
	@JoinColumn(name = "id_departamento", 
	referencedColumnName = "id_departamento", 
	insertable = false,
	updatable = false,
	foreignKey = @ForeignKey(name="fk_id_departamento"))
	private DepartamentoModel departamento;
	
	@ManyToOne
	@JoinColumn(name = "id_fabricante", 
	referencedColumnName = "id_fabricante", 
	insertable = false,
	updatable = false,
	foreignKey = @ForeignKey(name="fk_id_fabricante"))
	private FabricanteModel fabricante;
	
	
}
