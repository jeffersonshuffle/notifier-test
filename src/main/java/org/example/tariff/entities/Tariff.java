
package org.example.tariff.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "tariffs", catalog = "postgres", schema = "public")

public class Tariff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tariffId", fetch = FetchType.EAGER)
    private Collection<TariffDetails> tariffDetailsCollection;
  
    public Tariff() {
    }

    

    public Tariff(Long id, String name, Date dateCreated, boolean isActive) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.isActive = isActive;
    }

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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }


    public Collection<TariffDetails> getTariffDetailsCollection() {
        return tariffDetailsCollection;
    }

    public void setTariffDetailsCollection(Collection<TariffDetails> tariffDetailsCollection) {
        this.tariffDetailsCollection = tariffDetailsCollection;
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
        if (!(object instanceof Tariff)) {
            return false;
        }
        Tariff other = (Tariff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.example.tariff.entities.Tariff[ id=" + id + " ]";
    }
    
}
