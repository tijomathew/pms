package org.pms.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * User: Cufa User.
 */
public class ParishValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validator is executed from custom parish validator!!..");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "churchName", "parish.churchName");
    }
}
