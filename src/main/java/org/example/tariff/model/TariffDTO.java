package org.example.tariff.model;


import java.util.Collection;
import java.util.Date;





public class TariffDTO {
	 
	   
	    private Long id;
            
	    private String name;
	    private Date dateCreated;
		   
	    private boolean isActive;
	    
	    private Collection<TariffDetailsDTO> tariffDetailsCollection;
	    
	  
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
