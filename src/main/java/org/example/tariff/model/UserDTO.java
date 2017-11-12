package org.example.tariff.model;



public class UserDTO {
    
    public static UserDTO getEmpty(){
        UserDTO result=new UserDTO();
        result.setId(0L);
        result.setFirstName("specify user name");
        result.setLastName("specify last name");
        result.setTariffId(0L);
        result.setTariffName("empty");
        return result;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private Long tariffId;
    private String tariffName;
/** Getters setters
  *
 */
    public Long getTariffId() {
	return tariffId;
    }
    public void setTariffId(Long tariffId) {
	this.tariffId = tariffId;
    }
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
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

    public String getTariffName() {
	return tariffName;
    }

    public void setTariffName(String tariffName) {
	this.tariffName = tariffName;
    }
}
