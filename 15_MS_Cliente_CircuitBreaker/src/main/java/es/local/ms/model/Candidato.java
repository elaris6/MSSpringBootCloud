package es.local.ms.model;

public class Candidato {

	private String nombre;
	private int idEmpleado;
	private String puesto;
	
	public Candidato() {
		super();
	}
	
	public Candidato(String nombre, int idEmpleado, String puesto) {
		super();
		this.nombre = nombre;
		this.idEmpleado = idEmpleado;
		this.puesto = puesto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public String getPuesto() {
		return puesto;
	}
	
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
}
