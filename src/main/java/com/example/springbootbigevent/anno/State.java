package com.example.springbootbigevent.anno;

import com.auth0.jwt.interfaces.Payload;
import com.example.springbootbigevent.validation.StateValidation;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "{state should be \"已发布\" or \"草稿\"}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
