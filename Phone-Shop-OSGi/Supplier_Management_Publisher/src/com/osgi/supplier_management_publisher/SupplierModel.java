package com.osgi.supplier_management_publisher;

public class SupplierModel {

	private int supp_id;
	private String firstName;
	private String lastName;
	private String email;
	private String nic;
	private String phone;
	private String companyName;
	private boolean isActive;
	
	public SupplierModel() {
		super();
	}

	public SupplierModel(int supp_id, String firstName, String lastName, String email, String nic, String phone,
			String companyName, boolean isActive) {
		super();
		this.supp_id = supp_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.nic = nic;
		this.phone = phone;
		this.companyName = companyName;
		this.isActive = isActive;
	}

	public int getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(int supp_id) {
		this.supp_id = supp_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
}
