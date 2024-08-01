package co.edu.uptc.management.clients.dto;

public class ServiceDTO {
 
	private String idClient;
	private String code;
	private String name;
	private String price;
	
	public ServiceDTO(String idClient, String code, String name, String price) {
		super();
		this.idClient = idClient;
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public ServiceDTO() {
		super();
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ServiceDTO [idClient=" + idClient + ", code=" + code + ", name=" + name + ", price=" + price + "]";
	}
	
}
