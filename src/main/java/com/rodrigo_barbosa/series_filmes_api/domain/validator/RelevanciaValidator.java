package com.rodrigo_barbosa.series_filmes_api.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RelevanciaValidator implements ConstraintValidator<ValidRelevancia, String> {
    @Override
    public void initialize(ValidRelevancia constraintAnnotation) {}

    @Override
    public boolean isValid(String relevancia, ConstraintValidatorContext context) {
        if (relevancia == null) {
            return false;
        }
        try {
            RelevanciaEnum.valueOf(relevancia.toUpperCase());
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
