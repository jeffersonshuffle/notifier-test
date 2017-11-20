package org.example.tariff.model;



import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import org.example.tariff.config.NotificationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;






public class Notification {

    
    
   
	
    
    private Long tariffId;
    private String tariffName;
    private String nomenclature;
    private Long oldPrice;
    private Long newPrice;
    private Date date;
    private String subject;
    private String body;
    
   public static Notification getEmty(){
       return new Notification();
   }
 

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }
   

	public Long getTariffId() {
		return tariffId;
	}

	
	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
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


}
