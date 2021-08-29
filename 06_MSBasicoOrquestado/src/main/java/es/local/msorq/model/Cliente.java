package es.local.msorq.model;

import java.util.Date;
import java.util.List;

public class Cliente {

	private String email;

	private Date fechaNacimiento;

	private String nombre;

	private String telefono;
	
	private String dni;
	
	private List<Dispositivo> dispositivos;

	public Cliente() {
		super();
	}

	public Cliente(String email, Date fechaNacimiento, String nombre, String telefono, String dni,
			List<Dispositivo> dispositivos) {
		super();
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.telefono = telefono;
		this.dni = dni;
		this.dispositivos = dispositivos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}
	
	
}
