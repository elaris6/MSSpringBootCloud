package es.local.ms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hoteles database table.
 * 
 */
@Entity
@Table(name="hoteles")
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_hotel")
	private Integer idHotel;

	private Integer categoria;

	private Boolean disponible;

	private String nombre;

	private double precio;

	public Hotel() {
	}

	public Integer getIdHotel() {
		return this.idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	public Integer getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Boolean getDisponible() {
		return this.disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}