package org.pms.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: Cufa User.
 */
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("validator is executed from custom validator!!..");
    }
}
