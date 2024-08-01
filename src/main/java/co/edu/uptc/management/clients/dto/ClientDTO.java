package co.edu.uptc.management.clients.dto;

public class ClientDTO {

	private String id;
	private String name;
	private String subscriptionDate;
	private String phoneNumber;
	private String email;
	
	public ClientDTO(String id, String name, String subscriptionDate, String phoneNumber, String email) {
		super();
		this.id = id;
		this.name = name;
		this.subscriptionDate = subscriptionDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public ClientDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ClientDTO [id=" + id + ", name=" + name + ", subscriptionDate=" + subscriptionDate + ", phoneNumber="
				+ phoneNumber + ", email=" + email + "]";
	}
	
	
}
