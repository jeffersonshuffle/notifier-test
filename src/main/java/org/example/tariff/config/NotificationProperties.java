/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.config;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


/**
 *
 * @author root
 */
@Validated
@Component
@ConfigurationProperties
@PropertySource("classpath:notification.properties")
public class NotificationProperties {
    @NotNull
    @Value("${notification.subject}")
    String subject;
    @NotNull
    @Value("${notification.body}")
     String body;
    @NotNull
    @Value("${notification.tariffName}")
     String tariffName;
    @Value("${notification.nomenclature}")
    String nomenclature;
    @NotNull
    @Value("${notification.oldPrice}")
    String oldPrice;
    @NotNull
    @Value("${notification.currency}")
    String currency;
    @NotNull
    @Value("${notification.newPrice}")
    String newPrice;
    @NotNull
    @Value("${notification.currencyValue}")
    String currencyValue;  
    //getters setters    

    public String getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
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

