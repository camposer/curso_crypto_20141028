package crypto.rmi.to;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {
	private static final long serialVersionUID = 3259248525886142849L;
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
