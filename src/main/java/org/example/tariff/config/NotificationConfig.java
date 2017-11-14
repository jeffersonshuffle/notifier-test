/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 *
 * @author root
 */
@Configuration
@Component
@ConfigurationProperties
@PropertySource("classpath:notification")
public class NotificationConfig {
    @Value("${notification.subject}")
    String subject;
    @Value("${notification.body}")
     String body;
    @Value("${notification.tariffName}")
     String tariffName;
    @Value("${notification.nomenclature}")
    String nomenclature;
    @Value("${notification.oldPrice}")
    String oldPrice;
    @Value("${notification.currency}")
    String currency;
    @Value("${notification.newPrice}")
    String newPrice;
      
    //getters setters    
        
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

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
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
    
}

