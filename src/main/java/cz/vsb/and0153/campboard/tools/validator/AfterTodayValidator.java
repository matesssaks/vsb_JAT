package cz.vsb.and0153.campboard.tools.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class AfterTodayValidator implements ConstraintValidator<AfterToday, LocalDateTime> {
	@Override
	public boolean isValid(LocalDateTime dateTime, ConstraintValidatorContext context) {
		if (dateTime == null) return true;

		return dateTime.isAfter(LocalDateTime.now());
	}
}
