package com.springboot.msbasico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Clase autogenerada por el asistente JPA de Eclipse.
 *
 */
@Entity
@Table(name="clientes_dispositivos")
@NamedQuery(name="CliDisp.findAll", query="SELECT c FROM CliDisp c")
public class CliDisp implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Se ha modificado el tipo asignado por JPA Tools de Integer a int, para que
	 * la búsqueda del id trate de forma sencilla el valor "null".
	 * Si "id" está declarado como "int" y es null, al hacer getId(id), devolverá 0.
	 * Sin embargo, si está declarado como "Integer" devolverá "null" y sería necesario
	 * hacer un tratamiento específico de este valor. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="browser_public_key")
	private String browserPublicKey;

	@Column(name="device_brand")
	private String deviceBrand;

	@Column(name="device_model")
	private String deviceModel;

	@Column(name="hash_dni")
	private String hashDni;

	private Boolean marketing;

	@Column(name="os_name")
	private String osName;

	@Column(name="os_version")
	private String osVersion;

	private String platform;

	@Column(name="push_token")
	private String pushToken;

	public CliDisp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrowserPublicKey() {
		return this.browserPublicKey;
	}

	public void setBrowserPublicKey(String browserPublicKey) {
		this.browserPublicKey = browserPublicKey;
	}

	public String getDeviceBrand() {
		return this.deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getHashDni() {
		return this.hashDni;
	}

	public void setHashDni(String hashDni) {
		this.hashDni = hashDni;
	}

	public Boolean getMarketing() {
		return this.marketing;
	}

	public void setMarketing(Boolean marketing) {
		this.marketing = marketing;
	}

	public String getOsName() {
		return this.osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return this.osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPushToken() {
		return this.pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	@Override
	public String toString() {
		return "ClientesDispositivo [id=" + id + ", browserPublicKey=" + browserPublicKey + ", deviceBrand="
				+ deviceBrand + ", deviceModel=" + deviceModel + ", hashDni=" + hashDni + ", marketing=" + marketing
				+ ", osName=" + osName + ", osVersion=" + osVersion + ", platform=" + platform + ", pushToken="
				+ pushToken + "]";
	}



}