
package org.example.tariff.entities;

import java.io.Serializable;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.Immutable;


@Entity
@Table(name = "price_change_report", catalog = "postgres", schema = "public")
@Immutable
public class PriceChangeReport implements Serializable {
	@Id
    @Basic(optional = false)
    @NotNull
   
    @Column(name = "tariff_id")
    private Long tariffId;
    @Column(name = "tariff")
    private String tariffName;

    @Size(max = 100)
    @Column(name = "nomenclature")
    private String nomenclature;
    @Column(name = "old_price")
    private Long oldPrice;
    @Column(name = "new_price")
    private Long newPrice;
    @Column(name = "newdate")
    @Temporal(TemporalType.DATE)
    private Date date;

    private static final long serialVersionUID = 1L;

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Long getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Long oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Long getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Long newPrice) {
        this.newPrice = newPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
  
   

    public PriceChangeReport() {
    }



     
}
