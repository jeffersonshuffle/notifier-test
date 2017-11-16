
package org.example.tariff.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.example.tariff.model.NotifyRequest;
import org.springframework.stereotype.Component;

/**
 * Validator for NotifyRequest object
 * 
 */
@Component
public class IsCorrectNotificationValidator implements ConstraintValidator<IsCorrectNotification, NotifyRequest> {

    @Override
    public void initialize(IsCorrectNotification a) {
        
    }

    @Override
    public boolean isValid(NotifyRequest value, ConstraintValidatorContext context) {
        if (value == null) return false;

        if (value.getUser()== null || value.getUser().getId()==0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("User object is required")
                    .addPropertyNode("user").addConstraintViolation();
            return false;
        }
        if (value.getTariff()== null || value.getTariff().getId()==0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tariff object is required")
                    .addPropertyNode("tariff").addConstraintViolation();
            return false;
        }
        if(value.getStartOfPeriod().compareTo(value.getEndOfPeriod())>0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("start of period must be less then end of period")
                    .addPropertyNode("startOfPeriod").addConstraintViolation();
            return false;
        }
        return true;
    }
    
}
