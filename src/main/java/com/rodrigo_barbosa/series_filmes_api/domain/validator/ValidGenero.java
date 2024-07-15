package com.rodrigo_barbosa.series_filmes_api.domain.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = GeneroValidator.class)
public @interface ValidGenero {
//isto é uma validação personalizada
    String message() default "Gênero inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
