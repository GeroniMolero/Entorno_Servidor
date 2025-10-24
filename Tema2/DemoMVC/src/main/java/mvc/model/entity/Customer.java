package mvc.model.entity;

public class Customer {
	private Integer customerId;
	private String name;
	private String address;
	private String website;
	private Double creditLimit;
	
	
	
	public Customer(Integer customerId, String name, String address, String website, Double creditLimit) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.website = website;
		this.creditLimit = creditLimit;
	}

	

	public Integer getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getWebsite() {
		return website;
	}



	public void setWebsite(String website) {
		this.website = website;
	}



	public Double getCreditLimit() {
		return creditLimit;
	}



	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}



	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", address=" + address + ", website=" + website
				+ ", creditLimit=" + creditLimit + "]";
	}
}
