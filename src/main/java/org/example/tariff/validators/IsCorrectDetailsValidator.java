
package org.example.tariff.validators;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.example.tariff.model.TariffDetailsDTO;
import org.springframework.stereotype.Component;

/**
 * Validator for TariffDetailsDTO object
 * 
 */
@Component
public class IsCorrectDetailsValidator implements ConstraintValidator<IsValidDetails, TariffDetailsDTO> {

    @Override
    public void initialize(IsValidDetails a) {
        
    }

    @Override
    public boolean isValid(TariffDetailsDTO value, ConstraintValidatorContext context) {
        if (value == null) return false;

        if (value.getId()==null||value.getId()<=0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("details id must be >0")
                    .addPropertyNode("id").addConstraintViolation();
            return false;
        }
        if (value.getTariffId()== null || value.getTariffId()<=0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tariff id must be >0")
                    .addPropertyNode("tariffId").addConstraintViolation();
            return false;
        }
        if(value.getPricePerUnit()== null || value.getPricePerUnit().compareTo(BigDecimal.ZERO)<=0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("price must be positive decimal")
                    .addPropertyNode("pricePerUnit").addConstraintViolation();
            return false;
        }
        return true;
    }
    
}
