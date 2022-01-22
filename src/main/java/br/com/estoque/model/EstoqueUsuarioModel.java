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

import br.com.estoque.usuario.model.UsuarioModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_estoque_usuario")
public class EstoqueUsuarioModel implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estoque_usuario", precision = 8, unique = true, nullable = false)
	private Long idEstoqueUsuario;
	
	@Column(name = "id_estoque")
	private Long idEstoque;
	
	@Column(name = "id_usuario")
	private Long idUsuario;

	@ManyToOne
	@JoinColumn(name = "id_estoque", updatable = false, insertable = false, foreignKey = @ForeignKey(name="fk_id_estoque") )
	private EstoqueModel estoque;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", updatable = false, insertable = false, foreignKey = @ForeignKey(name="fk_id_usuario"))
	private UsuarioModel usuario;
	
	
	
}
