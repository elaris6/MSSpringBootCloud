package es.local.ms.model;

import java.util.List;

public class Pais {

	private String name;
	
	private String apha2Code;
	
	private String apha3Code;
	
	private String capital;
	
	private List<String> langs;

	public Pais() {
		super();
	}

	public Pais(String name, String apha2Code, String apha3Code, String capital, List<String> langs) {
		super();
		this.name = name;
		this.apha2Code = apha2Code;
		this.apha3Code = apha3Code;
		this.capital = capital;
		this.langs = langs;
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

	public List<String> getLangs() {
		return langs;
	}

	public void setLangs(List<String> langs) {
		this.langs = langs;
	}
	
	
}
