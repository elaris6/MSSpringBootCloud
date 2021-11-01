package com.springboot.msbasico.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/* Para que una clase de entidad o modelo se pueda transformar en un XML es necesario
 * anotarla con "XmlAccessorType(XmlAccessType.FIELD)" ó con "XmlRootElement".
 * Esto no evita que se pueda también transformar a la vez a JSON, por ejemplo. */
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Centro {

	private String nombre;
	
	private String ciudad;
	
	private String direccion;

	public Centro() {
		super();
	}

	public Centro(String nombre, String ciudad, String direccion) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Centro [nombre=" + nombre + ", ciudad=" + ciudad + ", direccion=" + direccion + "]";
	}
	
	
}
