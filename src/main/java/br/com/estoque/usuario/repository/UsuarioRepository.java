package br.com.estoque.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estoque.usuario.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	
	@Query(name = "UsuarioModel.loginUsuario", value = "SELECT u FROM UsuarioModel u WHERE u.emailUsuario =:emailUsuario and u.senhaUsuario =:senhaUsuario")
	public UsuarioModel loginUsuario( @Param("emailUsuario") String emailUsuario, @Param("senhaUsuario") String senhaUsuario);
	
	@Query(name="UsuarioModel.usuarioByEmail", value = "SELECT u FROM UsuarioModel u WHERE u.emailUsuario =:emailUsuario")
	public UsuarioModel usuarioByEmail(@Param("emailUsuario")String emailUsuario);

}
