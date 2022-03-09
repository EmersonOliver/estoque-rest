package br.com.estoque.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.estoque.usuario.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_saida_estoque", schema = "estoque")
public class SaidaEstoqueModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4139741311374113047L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_saida_estoque", precision = 8, nullable = false, unique = true)
	private Long idSaidaEstoque;

	@Column(name = "id_usuario", nullable = false)
	private Long idUsuario;

	@Column(name = "id_equipamento", nullable = false)
	private Long idEquipamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_saida")
	private Date dtSaida;

	@Column(name = "de_observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_id_equipamento"))
	private EquipamentoModel equipamento;

	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false, foreignKey = @ForeignKey(name="fk_id_usuario"))
	private UsuarioModel usuario;

}
