package br.com.estoque.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_estoque")
public class EstoqueModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estoque", nullable = false, unique = true, precision = 8)
	private Long idEstoque;
	
	@Column(name = "data_entrada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;
	
	@Column(name = "data_saida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaida;
	
	@Column(name = "status_estoque")
	private int statusEstoque;
	
	@JsonIgnoreProperties("estoque")
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estoque", referencedColumnName = "id_estoque", insertable = false, nullable = true, updatable = false)
	private List<EquipamentoModel> equipamentos;
	
	
	
}
