package cl.stgo.usuarioApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import cl.stgo.usuarioApp.dao.entity.Telefono;
import cl.stgo.usuarioApp.dao.entity.Usuario;
import cl.stgo.usuarioApp.util.EmailValidator;
import cl.stgo.usuarioApp.util.PassValidator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioAppApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private Logger log = LoggerFactory.getLogger(UsuarioAppApplicationTests.class);

	@LocalServerPort
	int randomServerPort;

	@Test
	void contextLoads() {
	}

	@Test
	void testValidaEmail() {
		assumeTrue(EmailValidator.validate("pablovillablanca@gmail.com"));
	}

	@Test
	void testValidaPass() {
		assumeTrue(PassValidator.validate("Acrrr34"));
	}

	@Test
	void testValidaEmailIncorrecto() {
		assumeFalse(EmailValidator.validate("pablo.villablaail.com"));
	}

	@Test
	void testValidaPassIncorrecto() {
		assumeFalse(PassValidator.validate("c33332c"));
	}

	@Test
	public void testCrearUsuarioCorrecto() throws URISyntaxException {

		final String baseUrl = "http://localhost:" + randomServerPort + "/usuarios/crear";
		URI uri = new URI(baseUrl);

		Usuario usuario = new Usuario("pablo", "pablo@gmail.com", "Abzxc34");

		Telefono t1 = new Telefono("68069882", "1", "1");

		List<Telefono> phones = new ArrayList<>();
		phones.add(t1);

		usuario.setPhones(phones);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Usuario> request = new HttpEntity<>(usuario, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
	}

	@Test
	public void testCrearUsuarioEmailIncorrecto() throws URISyntaxException {

		final String baseUrl = "http://localhost:" + randomServerPort + "/usuarios/crear";
		URI uri = new URI(baseUrl);

		Usuario usuario = new Usuario("pablo", "pablogmail.com", "Abzxc34");

		Telefono t1 = new Telefono("68069882", "1", "1");

		List<Telefono> phones = new ArrayList<>();
		phones.add(t1);

		usuario.setPhones(phones);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Usuario> request = new HttpEntity<>(usuario, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCode().value());
	}

	@Test
	public void testCrearUsuarioPasswordIncorrecto() throws URISyntaxException {

		final String baseUrl = "http://localhost:" + randomServerPort + "/usuarios/crear";
		URI uri = new URI(baseUrl);

		Usuario usuario = new Usuario("pablo", "pablo@gmail.com", "333332");

		Telefono t1 = new Telefono("68069882", "1", "1");

		List<Telefono> phones = new ArrayList<>();
		phones.add(t1);

		usuario.setPhones(phones);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Usuario> request = new HttpEntity<>(usuario, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCode().value());
	}

	@Test
	public void testCrearUsuarioCorreoExistente() throws URISyntaxException {

		final String baseUrl = "http://localhost:" + randomServerPort + "/usuarios/crear";
		URI uri = new URI(baseUrl);

		log.info("--------- Creamos primer usuario -----------");

		Usuario usuario = new Usuario("pablo", "pablo@gmail.com", "Abzxc34");

		Telefono t1 = new Telefono("68069882", "1", "1");

		List<Telefono> phones = new ArrayList<>();
		phones.add(t1);

		usuario.setPhones(phones);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Usuario> request = new HttpEntity<>(usuario, headers);

		this.restTemplate.postForEntity(uri, request, String.class);
		
		log.info("--------- Creamos segundo usuario -----------");

		Usuario usuario2 = new Usuario("pablo", "pablo@gmail.com", "Abzxc34");

		Telefono t2 = new Telefono("68069882", "1", "1");

		List<Telefono> phones2 = new ArrayList<>();
		phones2.add(t2);

		usuario2.setPhones(phones2);
		HttpEntity<Usuario> request2 = new HttpEntity<>(usuario2, headers);

		ResponseEntity<String> result2 = this.restTemplate.postForEntity(uri, request2, String.class);

		assertEquals(HttpStatus.BAD_REQUEST.value(), result2.getStatusCode().value());
	}

}
