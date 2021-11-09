package com.local.contactos.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;
    
    private String nombre;

	public Integer getId_rol() {
		return id_rol;
	}

	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    /* No creamos el atributo de usuario, pues para este ejercicio no tiene
    utilidad, pero para que la clase estuviese completa, debería existir. */
    /*@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;*/
    
    
    
}
