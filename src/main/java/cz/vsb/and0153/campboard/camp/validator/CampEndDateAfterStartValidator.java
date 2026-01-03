package cz.vsb.and0153.campboard.camp.validator;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CampEndDateAfterStartValidator implements ConstraintValidator<CampEndDateAfterStart, CampDto> {
	@Override
	public boolean isValid(CampDto camp, ConstraintValidatorContext context) {
		if (camp.getStartDate() == null || camp.getEndDate() == null) return true;

		boolean isValid = camp.getEndDate().isAfter(camp.getStartDate());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
				.addPropertyNode("endDate")
				.addConstraintViolation();
		}

		return isValid;
	}
}
