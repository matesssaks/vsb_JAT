package cz.vsb.and0153.campboard.auth.validator;

import cz.vsb.and0153.campboard.user.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	private final UserService userService;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if (username == null || username.isEmpty()) {
			return true;
		}
		return !userService.existsByUsername(username);
	}
}
