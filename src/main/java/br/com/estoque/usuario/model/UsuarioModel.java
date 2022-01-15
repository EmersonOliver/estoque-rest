package br.com.estoque.usuario.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class UsuarioModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", unique = true, precision = 8, nullable = false)
	private Long idUsuario;
	
	@Column(name = "nome_usuario")
	private String nomeUsuario;
	
	@Column(name = "email_usuario")
	private String emailUsuario;
	
	@Column(name = "senha_usuario")
	private String senhaUsuario;
	
	@Column(name="telefone_usuario")
	private String telefoneUsuario;
	
	

	public UsuarioModel(Long idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario,
			String telefoneUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.telefoneUsuario = telefoneUsuario;
	}



	public UsuarioModel(String emailUsuario, String senhaUsuario) {
		super();
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
	}
}
