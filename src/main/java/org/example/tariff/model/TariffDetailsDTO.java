package org.example.tariff.model;

import java.math.BigDecimal;
import java.util.Date;




public class TariffDetailsDTO {
	
   
    private Long id;
    private Long tariffId;
    private BigDecimal pricePerUnit;
    private Date dateLastModified;
    private Long nomenclatureId;

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }
    public Long getNomenclatureId() {
	return nomenclatureId;
    }
	public void setNomenclatureId(Long nomenclatureId) {
		this.nomenclatureId = nomenclatureId;
	}
	public Long getId() {
		return id;
	}



	
	public void setId(Long id) {
		this.id = id;
	}



	
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}



	
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}




	public Date getDateLastModified() {
		return dateLastModified;
	}



	
	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}





}
