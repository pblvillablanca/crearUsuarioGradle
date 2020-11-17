package cl.stgo.usuarioApp.dao.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.stgo.usuarioApp.dao.entity.Usuario;
import cl.stgo.usuarioApp.dao.service.IUsuarioDao;
import cl.stgo.usuarioApp.util.GeneradorTokenUtil;

@Service
@Transactional
public class UsuarioService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioDao usuarioDao;	

	public Usuario crearUsuario(Usuario usuario) {
	
		usuario.setCreated(new Date());
		usuario.setModified(new Date());
		usuario.setLastLogin(new Date());
		usuario.setActive(true);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setToken(new GeneradorTokenUtil().generarTokenJWT(usuario));

		Usuario save = usuarioDao.save(usuario);

		log.info("Usuario " + usuario.getEmail() + "  creado correctamente");

		return save;
	}

	public Usuario findByEmail(String email) {
		return usuarioDao.findByEmail(email);
	}

}
