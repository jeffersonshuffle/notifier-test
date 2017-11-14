package org.example.tariff.model;



import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import org.example.tariff.config.NotificationConfig;







public class NotificationDTO {

    
    NotificationConfig notificationConfig;
	
    
    private Long tariffId;
    private String tariffName;
    private String nomenclature;
    private Long oldPrice;
    private Long newPrice;
    private Date date;
    private String subject;
    private String body;
    
   
    public NotificationDTO initNotificationTemplate(NotificationConfig notificationConfig){
        this.notificationConfig=notificationConfig;
        return this;
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

        void generateSubject(){
        Map map = new HashMap();
        map.put("tariffName", tariffName);
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext(map);
        
  
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "Error processing subject ", notificationConfig.getSubject());
  
        setSubject( writer.toString() );
        
    }
    void generateBody(){
        Map map = new HashMap();
        map.put(notificationConfig.getTariffName(), tariffName);
        map.put(notificationConfig.getNomenclature(), nomenclature);
        map.put(notificationConfig.getOldPrice(), oldPrice);
        map.put(notificationConfig.getCurrency(), notificationConfig.getCurrencyValue());
        map.put(notificationConfig.getNewPrice(), newPrice);
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext(map);
        
      
        StringWriter writer = new StringWriter();
     
        Velocity.evaluate(context, writer, "Error processing body ", notificationConfig.getBody());
        setBody( writer.toString() );
    }
    
    public NotificationDTO generateNotificationTemplate() {
    	this.setSubject(notificationConfig.getSubject());
        this.setBody(notificationConfig.getBody());
        	
        return this;
    }
    public NotificationDTO generateNotification() {
    	generateSubject();
        generateBody();
        	
        return this;
    }

}
