package cz.vsb.and0153.campboard.auth.validator;

import cz.vsb.and0153.campboard.user.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private final UserService userService;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null || email.isEmpty()) {
			return true;
		}
		return !userService.existsByEmail(email);
	}
}
