package cz.vsb.and0153.campboard.auth.validator;

import cz.vsb.and0153.campboard.auth.dto.SignupForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignupForm> {
	@Override
	public boolean isValid(SignupForm form, ConstraintValidatorContext context) {
		if (form.getPassword() == null) return form.getPassword2() == null;

		boolean isValid = form.getPassword().equals(form.getPassword2());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
				.addPropertyNode("password2")
				.addConstraintViolation();
		}

		return isValid;
	}
}
