package cl.stgo.usuarioApp.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TELEFONOS")
public class Telefono implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6034830380960457895L;

	@Id
	@GeneratedValue(generator = "telefonoGenerator")
	@SequenceGenerator(name = "telefonoGenerator", allocationSize = 1, sequenceName = "seq_telefonos")
	private Long id;

	private String number;

	private String citycode;

	private String contrycode;

	public Telefono() {
	}

	public Telefono(String number, String citycode, String contrycode) {
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

}
