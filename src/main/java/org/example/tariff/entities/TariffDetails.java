/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="tariff_details", catalog = "postgres", schema = "public")

public class TariffDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    

    @Basic(optional = false)
    @NotNull
    private BigDecimal pricePerUnit;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateLastModified;
    @Basic(optional = false)
    @NotNull
    private Long nomenclatureId;
    @JoinColumn(name = "tariff_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tariff tariffId;
  
    
    public void updateFrom(TariffDetails details){
        pricePerUnit=details.getPricePerUnit();
        dateLastModified=new Date();
    }
	public Long getNomenclatureId() {
		return nomenclatureId;
	}


	public void setNomenclatureId(Long nomenclatureId) {
		this.nomenclatureId = nomenclatureId;
	}



    public TariffDetails() {
    }

    public TariffDetails(Long id) {
        this.id = id;
    }

    public TariffDetails(Long id, BigDecimal pricePerUnit, Date dateLastModified) {
        this.id = id;
        this.pricePerUnit = pricePerUnit;
        this.dateLastModified = dateLastModified;
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

   

    public Tariff getTariffId() {
        return tariffId;
    }

    public void setTariffId(Tariff tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if(null==object)return false;
        if (this == object) {
        	return true;
        	}
        if (!(object instanceof TariffDetails)) {
            return false;
        }
        TariffDetails other = (TariffDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.example.tariff.entities.TariffDetails[ id=" + id + " ]";
    }
    
}
