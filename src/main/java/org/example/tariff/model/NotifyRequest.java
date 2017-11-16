
package org.example.tariff.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.example.tariff.validators.IsCorrectNotification;



@IsCorrectNotification
public class NotifyRequest {
    public static NotifyRequest getEmpty(){
        NotifyRequest request=new NotifyRequest();
        request.setUser(UserDTO.getEmpty());
        request.setTariff(TariffDTO.getEmpty());
        request.setStartOfPeriod(new Date());
        request.setEndOfPeriod(new Date());
        return request;
    }
    @NotNull
    UserDTO user;
    @NotNull
    TariffDTO tariff;
    @NotNull
    Date startOfPeriod;
    @NotNull
    Date endOfPeriod;
    
    public NotifyRequest(){
    }
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
