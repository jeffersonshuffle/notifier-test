package org.example.tariff.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;





public class TariffDTO {

    public static TariffDTO getEmpty(){
        TariffDTO result=new TariffDTO();
        result.setId(0L);
        result.setActive(false);
        result.setDateCreated(new Date());
        result.setName("empty template");
        result.setTariffDetailsCollection(new ArrayList());
        result.getTariffDetailsCollection().add(TariffDetailsDTO.getEmpty());
        result.getTariffDetailsCollection().add(TariffDetailsDTO.getEmpty());
        return result;
    }

    private Long id;
    private String name;
    private Date dateCreated;		   
    private boolean isActive;
    private Collection<TariffDetailsDTO> tariffDetailsCollection;

//Getters Setters    
    public Long getId() {
			return id;
		}
    public void setId(Long id) {
			this.id = id;
		}
    public String getName() {
			return name;
		}
    public void setName(String name) {
			this.name = name;
		}
    public Date getDateCreated() {
			return dateCreated;
		}
    public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}
    public boolean isActive() {
			return isActive;
		}
    public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
    public Collection<TariffDetailsDTO> getTariffDetailsCollection() {
			return tariffDetailsCollection;
		}
    public void setTariffDetailsCollection(Collection<TariffDetailsDTO> tariffDetailsCollection) {
			this.tariffDetailsCollection = tariffDetailsCollection;
		}
	

}
