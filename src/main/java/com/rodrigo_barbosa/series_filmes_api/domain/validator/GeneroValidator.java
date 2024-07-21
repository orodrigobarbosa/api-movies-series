package com.rodrigo_barbosa.series_filmes_api.domain.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GeneroValidator implements ConstraintValidator<ValidGenero, String> {
 /*isto é um validador específico para o parâmetro gênero
    usado tanto para filmes quanto para séries*/

   @Override
    public void initialize(ValidGenero constraintAnnotation) {
    }

    @Override
    public boolean isValid(String genero, ConstraintValidatorContext context) {
        if (genero== null) {
            return false;
        }
        try {
            GeneroEnum.valueOf(genero.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}