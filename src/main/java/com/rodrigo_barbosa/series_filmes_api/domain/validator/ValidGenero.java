package com.rodrigo_barbosa.series_filmes_api.domain.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GeneroValidator.class)
public @interface ValidGenero {
//isto é uma validação personalizada
    String message() default "Gênero inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
