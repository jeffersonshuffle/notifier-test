package org.example.tariff.model;



public class UserDTO {
	private Long id;
   
    private String firstName;
    private String lastName;
    private Long tariffId;
    private String tariffName;
	public Long getTariffId() {
		return tariffId;
	}

	/**
	 * @param tariffId the tariffId to set
	 */
	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}

	
	

    
    
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	

}
