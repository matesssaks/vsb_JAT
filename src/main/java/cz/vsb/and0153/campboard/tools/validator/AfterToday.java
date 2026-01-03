package cz.vsb.and0153.campboard.tools.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AfterTodayValidator.class)
public @interface AfterToday {
	String message();
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
