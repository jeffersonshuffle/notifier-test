/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.model;

import java.util.Date;

/**
 *
 * @author root
 */
public class NotifyRequest {
    
    UserDTO user;
    TariffDTO tariff;
    Date startOfPeriod;
    Date endOfPeriod;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TariffDTO getTariff() {
        return tariff;
    }

    public void setTariff(TariffDTO tariff) {
        this.tariff = tariff;
    }

    public Date getStartOfPeriod() {
        return startOfPeriod;
    }

    public void setStartOfPeriod(Date startOfPeriod) {
        this.startOfPeriod = startOfPeriod;
    }

    public Date getEndOfPeriod() {
        return endOfPeriod;
    }

    public void setEndOfPeriod(Date endOfPeriod) {
        this.endOfPeriod = endOfPeriod;
    }
    
}
