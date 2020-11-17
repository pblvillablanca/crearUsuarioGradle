package cl.stgo.usuarioApp.util;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.stgo.usuarioApp.dao.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class GeneradorTokenUtil {
	
	Logger log = LoggerFactory.getLogger(GeneradorTokenUtil.class);

	private final String signingKey = "OtO9yyhmB1BsZsDQmmAlHBAsFPVK0eNg";
	
	public String generarTokenJWT(Usuario usuario) {

		SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
		Map<String, String> map = new HashMap<String, String>();
		map.put("usuario", usuario.getName());

		String jwt = Jwts.builder().setClaims(map).signWith(key).compact();

		log.info("el token generado es " + jwt);

		return jwt;
	}	
	
}
