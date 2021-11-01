package com.springboot.msbasico.model;

import java.util.Date;

/* Se define los java bean básicos para la gestión de las entidades a transitar
 * entre los microservicios.
 * No tiene porque coincidir con las entidades de origen o destino de los otros
 * servicios, pero tiene que ser coherente con los datos enviatos o recibidos,
 * para poder hacer el manejo de la entidad. */
public class Persona {

	private String nombre;
	
	private String email;

	private Date fechaNacimiento;
	
	private String telefono;
	
	public Persona() {
		
		super();
	}
	
	public Persona(String nombre, String email, String telefono, Date fechaNacimiento) {
		
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + ", telefono="
				+ telefono + "]";
	}

	
}
