/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.services;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.example.tariff.config.NotificationProperties;
import org.example.tariff.entities.PriceChangeReport;
import org.example.tariff.model.Notification;
import org.example.tariff.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */
@Component
public class NotificationBuilder {
    
     NotificationProperties notificationConfig;
     
    @Autowired
    public void setNotificationProperties(NotificationProperties notificationConfig){
        this.notificationConfig=notificationConfig;
        
    }
    
    
    void generateSubject(Notification n){
        Map map = new HashMap();
        map.put(notificationConfig.getTariffName(),n.getTariffName());
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext(map);
        
  
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "Error processing subject ", notificationConfig.getSubject());
  
        n.setSubject( writer.toString() );
        
    }
    
    
    void generateBody(Notification n){
        Map map = new HashMap();
        map.put(notificationConfig.getTariffName(), n.getTariffName());
        map.put(notificationConfig.getNomenclature(), n.getNomenclature());
        map.put(notificationConfig.getOldPrice(), n.getOldPrice());
        map.put(notificationConfig.getCurrency(), notificationConfig.getCurrencyValue());
        map.put(notificationConfig.getNewPrice(), n.getNewPrice());
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext(map);
        
      
        StringWriter writer = new StringWriter();
     
        Velocity.evaluate(context, writer, "Error processing body ", notificationConfig.getBody());
        n.setBody( writer.toString() );
    }
    
    public Notification generateNotificationTemplate(PriceChangeReport report) {
        Notification n= new Notification();
    	n.setSubject(notificationConfig.getSubject());
        n.setBody(notificationConfig.getBody());
        	
        return n;
    }
    public Notification generateNotification(PriceChangeReport report) {
         Notification n= BeanCopyUtil.toNotification(report);
         
    	generateSubject(n);
        generateBody(n);
        	
        return n;
    }

}
