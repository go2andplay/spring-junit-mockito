package com.trvajjala.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Pattern(regexp = ".+@.+\\..+", message = "EmailId.email")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface EmailId {

    String message() default "EmailId.email";

    String allowedCharacters() default "[aA-zZ][0-1][_.]";// based on this you change your regular expression pattern

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
