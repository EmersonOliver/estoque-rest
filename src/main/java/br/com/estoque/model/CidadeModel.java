package br.com.estoque.model;

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
@Entity
@Table(name = "tb_cidade")
@NoArgsConstructor
public class CidadeModel implements Serializable {

	private static final long serialVersionUID = -4392651683054082383L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cidade", nullable = false, unique = true, precision = 8)
	private Long idCidade;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;

	@Column(name = "uf")
	private String uf;

	public CidadeModel(Long idCidade, String cidade, String estado, String uf) {
		super();
		this.idCidade = idCidade;
		this.cidade = cidade;
		this.estado = estado;
		this.uf = uf;
	}

}
