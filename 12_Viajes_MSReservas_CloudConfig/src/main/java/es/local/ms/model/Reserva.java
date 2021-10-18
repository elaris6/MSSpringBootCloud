package es.local.ms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reservas database table.
 * 
 */
@Entity
@Table(name="reservas")
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reserva")
	private Integer idReserva;

	@Column(name="docu_cli")
	private String docuCli;

	private Integer hotel;

	@Column(name="nombre_cli")
	private String nombreCli;

	private Integer vuelo;

	public Reserva() {
	}
	
	public Reserva(String docuCli,String nombreCli,Integer hotel,Integer vuelo) {
		super();
		this.docuCli = docuCli;
		this.nombreCli = nombreCli;
		this.hotel = hotel;
		this.vuelo = vuelo;
	}

	public Integer getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public String getDocuCli() {
		return this.docuCli;
	}

	public void setDocuCli(String docuCli) {
		this.docuCli = docuCli;
	}

	public Integer getHotel() {
		return this.hotel;
	}

	public void setHotel(Integer hotel) {
		this.hotel = hotel;
	}

	public String getNombreCli() {
		return this.nombreCli;
	}

	public void setNombreCli(String nombreCli) {
		this.nombreCli = nombreCli;
	}

	public Integer getVuelo() {
		return this.vuelo;
	}

	public void setVuelo(Integer vuelo) {
		this.vuelo = vuelo;
	}

}