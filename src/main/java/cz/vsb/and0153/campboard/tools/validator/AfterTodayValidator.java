package cz.vsb.and0153.campboard.tools.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AfterTodayValidator implements ConstraintValidator<AfterToday, Object> {
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		return switch (obj) {
			case null -> true;
			case LocalDateTime dateTime -> dateTime.isAfter(LocalDateTime.now());
			case LocalDate date -> date.isAfter(LocalDate.now());
			default -> false;
		};
	}
}
