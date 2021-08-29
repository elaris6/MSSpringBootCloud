package es.local.msorq.model;

import java.util.List;

public class Respuesta {
	
	private String resultado;
	
	private Integer numClientes;
	
	private List<Cliente> clientes;
	
	public Respuesta() {
		super();
	}
	
	

	public Respuesta(String resultado, Integer numClientes, List<Cliente> clientes) {
		super();
		this.resultado = resultado;
		this.numClientes = numClientes;
		this.clientes = clientes;
	}


	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Integer getNumClientes() {
		return numClientes;
	}

	public void setNumClientes(Integer numClientes) {
		this.numClientes = numClientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
}
