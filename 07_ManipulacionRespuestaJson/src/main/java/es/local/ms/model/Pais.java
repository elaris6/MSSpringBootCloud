package es.local.ms.model;

public class Pais {

	private String name;
	
	private String apha2Code;
	
	private String apha3Code;
	
	private String capital;

	public Pais() {
		super();
	}

	public Pais(String name, String apha2Code, String apha3Code, String capital) {
		super();
		this.name = name;
		this.apha2Code = apha2Code;
		this.apha3Code = apha3Code;
		this.capital = capital;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApha2Code() {
		return apha2Code;
	}

	public void setApha2Code(String apha2Code) {
		this.apha2Code = apha2Code;
	}

	public String getApha3Code() {
		return apha3Code;
	}

	public void setApha3Code(String apha3Code) {
		this.apha3Code = apha3Code;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	
}
