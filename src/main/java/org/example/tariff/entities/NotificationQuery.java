/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author root
 */
@Entity
@Table(name = "notification_query")

public class NotificationQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tariff_id")
    private long tariffId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_end")
    @Temporal(TemporalType.DATE)
    private Date end;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_start")
    @Temporal(TemporalType.DATE)
    private Date start;

    public NotificationQuery() {
    }

    public NotificationQuery(Long id) {
        this.id = id;
    }

    public NotificationQuery(Long id, long tariffId, long userId, Date dateEnd, Date dateStart) {
        this.id = id;
        this.tariffId = tariffId;
        this.userId = userId;
        this.end = dateEnd;
        this.start = dateStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTariffId() {
        return tariffId;
    }

    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date dateEnd) {
        this.end = dateEnd;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date dateStart) {
        this.start = dateStart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if(object==null)return false;
        if (!(object instanceof NotificationQuery)) {
            return false;
        }
        NotificationQuery other = (NotificationQuery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.example.tariff.entities.NotificationQuery[ id=" + id + " ]";
    }
    
}
