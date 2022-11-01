package com.luxoft.springadvanced.beanvalidation.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CountryValidator implements ConstraintValidator<Country, String> {

    List<String> countries = Arrays.asList("Australia", "United Kingdom", "United States");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return countries.contains(value);

    }
}
