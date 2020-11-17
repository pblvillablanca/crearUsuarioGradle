package cl.stgo.usuarioApp.dao.service;

import org.springframework.data.repository.CrudRepository;

import cl.stgo.usuarioApp.dao.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	Usuario findByEmail(String email);

}
