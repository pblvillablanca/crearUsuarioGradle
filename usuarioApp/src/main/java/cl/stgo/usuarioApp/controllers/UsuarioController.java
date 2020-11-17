package cl.stgo.usuarioApp.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.stgo.usuarioApp.dao.entity.Usuario;
import cl.stgo.usuarioApp.dao.service.impl.UsuarioService;
import cl.stgo.usuarioApp.util.EmailValidator;
import cl.stgo.usuarioApp.util.PassValidator;


@RestController
public class UsuarioController {	
	
	@Autowired
	private UsuarioService usuarioService;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@PostMapping("/usuarios/crear")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {

		Usuario usuarioByEmail = usuarioService.findByEmail(usuario.getEmail());
		Map<String, Object> response = new HashMap<>();

		if (usuarioByEmail != null) {
			response.put("mensje", "El correo ya registrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (!EmailValidator.validate(usuario.getEmail())) {
			response.put("mensje", "El correo no cuenta con el formato aaaaaaa@dominio.c");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (!PassValidator.validate(usuario.getPassword())) {
			response.put("mensje",
					"La contraseña debe seguir el formato: Una Mayúscula, letras minúsculas, y dos números");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		log.info("No hay errores, procedemos  a crear el usuario");

		Usuario crearUsuario = usuarioService.crearUsuario(usuario);

		response.put("mensje", crearUsuario);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
