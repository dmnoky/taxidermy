package com.dmnoky.taxidermy.validator.other;

import com.dmnoky.taxidermy.model.other.Address;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddressValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        this.validate(target, errors, "");
    }

    public void validate(Object target, Errors errors, String property) {
        Address address = (Address) target;
        if (property.length()>0) property = property + ".";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, property+"address", "Field.notEmpty");
    }
}
