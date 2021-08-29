package es.local.msorq.model;


public class Dispositivo {

	private String deviceBrand;

	private String deviceModel;

	private Boolean marketing;

	private String osName;

	private String osVersion;

	private String platform;

	public Dispositivo() {
		super();
	}

	public Dispositivo(String deviceBrand, String deviceModel, Boolean marketing, String osName, String osVersion,
			String platform) {
		super();
		this.deviceBrand = deviceBrand;
		this.deviceModel = deviceModel;
		this.marketing = marketing;
		this.osName = osName;
		this.osVersion = osVersion;
		this.platform = platform;
	}

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public Boolean getMarketing() {
		return marketing;
	}

	public void setMarketing(Boolean marketing) {
		this.marketing = marketing;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
}
